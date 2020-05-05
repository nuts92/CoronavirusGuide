package com.example.android.coronavirusguide;


import android.app.Activity;
import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.navigation.Navigation;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.android.coronavirusguide.data_models.QuizData;
import com.example.android.coronavirusguide.interfaces.QuestionResponse;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;

import org.w3c.dom.Text;

import java.lang.reflect.Field;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class QuestionsFragment extends Fragment {

    private int score;

    private int totalQuestions;

    private int currentQuestion;

    private int state;

    private FirebaseFirestore db;

    private String question;

    private String optionOne;

    private String optionTwo;

    private String optionThree;

    private String optionFour;

    private String answer;

    private String multipleChoiceAnswerOne;

    private String multipleChoiceAnswerTwo;

    private String category;

    private String explanation;

    private String userAnswer;

    private QuestionResponse questionResponse;



    public QuestionsFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        score = 0;
        currentQuestion= 1;
        state = 0;
//        numberOfChoices = 0;

        db = FirebaseFirestore.getInstance();


        db.collection("Quiz").addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@Nullable QuerySnapshot queryDocumentSnapshots, @Nullable FirebaseFirestoreException e) {

                if(e != null){
                    Log.e("QuestionsFragment", e.toString());
                    return;
                }

                if(queryDocumentSnapshots != null){

                    totalQuestions = queryDocumentSnapshots.getDocuments().size();
                }
            }
        });

        setRetainInstance(true);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             final Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment_questions, container, false);

        final TextView scoreView = rootView.findViewById(R.id.score_view);

        final TextView questionsCountView = rootView.findViewById(R.id.questions_counter);

        Button explanationButton = rootView.findViewById(R.id.explanation_button);

        final Button confirmButton = rootView.findViewById(R.id.confirm_button);

        final TextView userAnswerConfirmation = rootView.findViewById(R.id.user_answer_check);

        final TextView correctAnswerView = rootView.findViewById(R.id.correct_answer);



        explanationButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                displayExplanation();
            }
        });

        //start with displaying the first question in the firestoredatabase


        db.collection("Quiz").document("" + currentQuestion).addSnapshotListener(new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot documentSnapshot, @Nullable FirebaseFirestoreException e) {

                if (documentSnapshot != null) {

                    QuizData quizData = documentSnapshot.toObject(QuizData.class);

                    if (quizData != null) {

                        scoreView.setText("Score:" + score);

                        questionsCountView.setText("Question: " + currentQuestion + "/" + totalQuestions);

                        setUpQuestion(quizData);

                    }
                }
            }
        });

        confirmButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final View view = v;

                //first time click on the confirm button/ check user answer and show the correct answer and change the confirm button text to Next Question
                if (state == 0) {


                    Result result = questionResponse.checkAnswer();

                    Animation animation = AnimationUtils.loadAnimation(getActivity(), R.anim.fragment_open_enter);


                    if(!result.isAnswered()) {
                        userAnswerConfirmation.setVisibility(View.INVISIBLE);
                        userAnswerConfirmation.setAnimation(animation);
                        userAnswerConfirmation.setVisibility(View.VISIBLE);
                        userAnswerConfirmation.setText(result.getUserAnswerConfirmation());
                        return;
                    }


                    if(result.isCorrect()){
                        userAnswerConfirmation.setAnimation(animation);
                        userAnswerConfirmation.setVisibility(View.VISIBLE);
                        userAnswerConfirmation.setText(result.getUserAnswerConfirmation());
                        confirmButton.setText("Next Question");
                        score++;
                        scoreView.setText("Score: " + score);

                    } else {
                        userAnswerConfirmation.setAnimation(animation);
                        userAnswerConfirmation.setVisibility(View.VISIBLE);
                        correctAnswerView.setAnimation(animation);
                        correctAnswerView.setVisibility(View.VISIBLE);
                        userAnswerConfirmation.setText(result.getUserAnswerConfirmation());
                        correctAnswerView.setText(result.getCorrectAnswerMessage());
                        confirmButton.setText("Next Question");
                    }

                    state = 1; // increase state to 1 so that when the user clicks on the button second time
                     // second time click/ it displays the next question

                } else { //second time click/ displays the next questions and resets the state back to zero

                    currentQuestion++;


                    //will take the user to the next question

                    questionsCountView.setText("Question: " + currentQuestion + "/" + totalQuestions);

                    db.collection("Quiz").document("" + currentQuestion).get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                        @Override
                        public void onSuccess(DocumentSnapshot documentSnapshot) {

                            if (documentSnapshot != null) {

                                QuizData quizData = documentSnapshot.toObject(QuizData.class);

                                if (quizData != null) {

                                    //reset the confirm button/ correctAnswerView/ userAnswerView/ state

                                    confirmButton.setText("Confirm");
                                    userAnswerConfirmation.setVisibility(View.INVISIBLE);
                                    correctAnswerView.setVisibility(View.INVISIBLE);

                                    setUpQuestion(quizData);
                                    state = 0;// reset to zero which is the state that checks the user answer and displays the correct answer

                                } else {

                                    //db put result in database

                                  Bundle args = new Bundle();
                                  args.putInt("score", score);
                                  args.putInt("total questions", totalQuestions);

                                    Toast.makeText(getActivity(), "Your Final Score " + score, Toast.LENGTH_SHORT).show();

                                    //In case of no more questions/final page
                                    Navigation.findNavController(view).navigate(R.id.action_questionsFragment_to_resultFragment, args);

                                }
                            }
                        }
                    });

                }
            }
        });



        return rootView;
    }

    private void setUpQuestion(QuizData quizData) {

        question = quizData.getQuestion();
        optionOne = quizData.getOptionOne();
        optionTwo = quizData.getOptionTwo();
        optionThree = quizData.getOptionThree();
        optionFour = quizData.getOptionFour();

        answer = quizData.getAnswer();

        explanation = quizData.getExplanation();

        category = quizData.getCategory();

        //Category 1 refers to the single choice type of questions


        if (category.equals("singleChoice")) {


            SingleChoiceFragment singleChoiceFragment = new SingleChoiceFragment();

            questionResponse = singleChoiceFragment;

            Bundle args = new Bundle();
            args.putString("question", question);
            args.putString("optionOne", optionOne);
            args.putString("optionTwo", optionTwo);
            args.putString("optionThree", optionThree);
            args.putString("optionFour", optionFour);
            args.putString("answer", answer);

            singleChoiceFragment.setArguments(args);


            getChildFragmentManager().beginTransaction().replace(R.id.question_container, singleChoiceFragment).commit();


        } else if (category.equals("multipleChoice")) {

            multipleChoiceAnswerOne = quizData.getMultipleChoiceAnswerOne();

            multipleChoiceAnswerTwo = quizData.getMultipleChoiceAnswerTwo();

            MultipleChoiceFragment multipleChoiceFragment = new MultipleChoiceFragment();

            questionResponse = multipleChoiceFragment;

            Bundle args = new Bundle();
            args.putString("question", question);
            args.putString("optionOne", optionOne);
            args.putString("optionTwo", optionTwo);
            args.putString("optionThree", optionThree);
            args.putString("optionFour", optionFour);
            args.putString("multipleChoiceAnswerOne", multipleChoiceAnswerOne);
            args.putString("multipleChoiceAnswerTwo", multipleChoiceAnswerTwo);


            multipleChoiceFragment.setArguments(args);


                getChildFragmentManager().beginTransaction().replace(R.id.question_container, multipleChoiceFragment).commit();

        } else {

            FreeInputFragment freeInputFragment = new FreeInputFragment();

            questionResponse = freeInputFragment;

            Bundle args = new Bundle();
            args.putString("question", question);
            args.putString("answer", answer);

            freeInputFragment.setArguments(args);

                getChildFragmentManager().beginTransaction().replace(R.id.question_container, freeInputFragment).commit();

        }
    }

    private void displayExplanation() {

                if(getActivity() != null) {

                    final Dialog dialog = new Dialog(getActivity());

                    dialog.setContentView(R.layout.explanation_card);

                    TextView explanationQuestion = dialog.findViewById(R.id.explanation_card_question);

                    TextView explanationAnswer = dialog.findViewById(R.id.explanation_card_answer);

                    explanationQuestion.setText(question);

                    explanationAnswer.setText(explanation);

                    ImageView closeButton = dialog.findViewById(R.id.explanation_close_button);

                    //setting the background of the dialog window to transparent
                    if (dialog.getWindow() != null) {

                        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                    }

                    dialog.show();

                    closeButton.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            dialog.dismiss();
                        }
                    });
                }
            }

}
