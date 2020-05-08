package com.example.android.coronavirusguide.quiz_flow_fragments;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import android.text.method.ScrollingMovementMethod;
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
import com.example.android.coronavirusguide.R;
import com.example.android.coronavirusguide.data_models.QuizData;
import com.example.android.coronavirusguide.data_models.Result;
import com.example.android.coronavirusguide.interfaces.QuestionResponse;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;

/**
 * QuestionsFragment subclass displays the Quiz Questions along with the explanation button, the confirm button, the score, the question counter,
 * and whether the user answer is correct or not and why. This fragment acts as a container for three questions' types fragments which are
 * the SingleChoiceFragment, MultipleChoiceFragment, and FreeInputFragment.
 */
public class QuestionsFragment extends Fragment {

    //Declaring all Object Variables
    private int score;

    private int totalQuestions;

    private int inCorrectAnswers;

    private int currentQuestion;

    private int state;

    private TextView mScoreView;

    private TextView mQuestionsCountView;

    private TextView mUserAnswerConfirmation;

    private TextView mCorrectAnswerView;

    private Button mConfirmButton;

    private FirebaseFirestore db;

    private String question;

    private String explanation;

    private QuestionResponse questionResponse;

    private Result result;

    public QuestionsFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Initializing the score variable which represents the correct answers to a value of 0
        score = 0;

        //Initializing the inCorrectAnswers variable to a value of 0
        inCorrectAnswers = 0;

        //Initializing the currentQuestion variable to a value of 1
        currentQuestion = 1;

        //Initializing the state variable to a value of 0, the state represents the state of the confirm button
        //state 0 represents the user's first time click on the confirm button, where we check the user answer and show the
        //correct answer message while changing the confirm button text to "Next Question" and setting the state to 1.
        //State 1 represents the second time click on this button, where we display the next question and resets the state back to zero
        state = 0;

        //Initializing an instance of FirebaseFirestore database
        db = FirebaseFirestore.getInstance();

        //Initializing the totalQuestions variable by getting the total number of documents in the collection "Quiz" where every document
        //represents a question and in our case there are 9 documents so there are 9 questions
        db.collection("Quiz").addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@Nullable QuerySnapshot queryDocumentSnapshots, @Nullable FirebaseFirestoreException e) {

                if (e != null) {

                    Log.e("QuestionsFragment", e.toString());
                    return;
                }

                if (queryDocumentSnapshots != null) {

                    totalQuestions = queryDocumentSnapshots.getDocuments().size();

                    mQuestionsCountView.setText(getString(R.string.question_counter_title, currentQuestion, totalQuestions));
                }
            }
        });

        //Displaying the first question when the QuestionsFragment opens by retrieving the data of this question from Firestore database.
        //The collection in Firestore is called "Quiz" and each document inside it represents a question and the document is named after the
        //question number so question one document is called 1 and question two document is called 2, etc.
        db.collection("Quiz").document("" + currentQuestion).addSnapshotListener(new EventListener<DocumentSnapshot>() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onEvent(@Nullable DocumentSnapshot documentSnapshot, @Nullable FirebaseFirestoreException e) {

                if (documentSnapshot != null) {

                    QuizData quizData = documentSnapshot.toObject(QuizData.class);

                    if (quizData != null) {

                        setUpQuestion(quizData);
                    }
                }
            }
        });

        //Control whether a fragment instance is retained across Activity re-creation (such as from a configuration change).
        setRetainInstance(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             final Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment_questions, container, false);

        //Initializing all Object Variables
        mScoreView = rootView.findViewById(R.id.score_view);

        mQuestionsCountView = rootView.findViewById(R.id.questions_counter);

        mConfirmButton = rootView.findViewById(R.id.confirm_button);

        mUserAnswerConfirmation = rootView.findViewById(R.id.user_answer_check);

        mCorrectAnswerView = rootView.findViewById(R.id.correct_answer);

        //Displaying the current score and the current question in the scoreView and questionsCountView object variables
        mScoreView.setText(getString(R.string.question_score_title, score));

        mQuestionsCountView.setText(getString(R.string.question_counter_title, currentQuestion, totalQuestions));

        //if state is 0 then the confirm button text will be confirm and will display a message for the user about answering the question
        //if state is 1 then the confirm button text will be  next question and display whether the answer is correct or not
        //This code takes care of Screen Rotation scenario
        if (state == 0) {

            mConfirmButton.setText(getString(R.string.confirm_button_name));

            if (result != null && !result.isAnswered()) {

                mUserAnswerConfirmation.setVisibility(View.INVISIBLE);

                mUserAnswerConfirmation.setVisibility(View.VISIBLE);

                mUserAnswerConfirmation.setText(result.getUserAnswerConfirmation());
            }

        } else {

            mConfirmButton.setText(getString(R.string.next_question));

            if (result != null) {

                if (result.isCorrect()) {

                    mUserAnswerConfirmation.setVisibility(View.VISIBLE);

                    mUserAnswerConfirmation.setText(result.getUserAnswerConfirmation());

                    mConfirmButton.setText(getString(R.string.next_question));

                    mScoreView.setText(getString(R.string.question_score_title, score));

                } else {

                    mUserAnswerConfirmation.setVisibility(View.VISIBLE);

                    mCorrectAnswerView.setVisibility(View.VISIBLE);

                    mUserAnswerConfirmation.setText(result.getUserAnswerConfirmation());

                    mCorrectAnswerView.setText(result.getCorrectAnswerMessage());
                }
            }
        }

        //Declaring and Initializing explanationButton Object Variable
        Button explanationButton = rootView.findViewById(R.id.explanation_button);

        //Attaching an OnClickListener to the explanationButton that determines the behavior that will happen when the user
        //clicks on that button
        explanationButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                displayExplanation();
            }
        });

        //Attaching an OnClickListener to the mConfirmButton that determines the behavior that will happen when the user
        //clicks on that button
        mConfirmButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                setUpButtonLogic(v);
            }
        });

        return rootView;
    }

    /**
     * This method sets up the confirm button logic or behavior when it's being clicked on. The button behaves differently based on the current state
     * if the state is 0 then  it represents the user's first time click on the confirm button and the behavior will be to check the user answer,
     * and show the correct answer message while changing the confirm button text to "Next Question" and setting the state to 1.
     * If the state is 1 representing the second time click on this button, the behavior will be to display the next question and resets the state back to zero
     *
     * @param v View: The view or button that is being clicked on in this case the mConfirmButton
     */
    private void setUpButtonLogic(View v) {

        final View view = v;

        if (state == 0) {

            //Check the user answer by calling checkAnswer() method on questionResponse which will return a result object variable
            result = questionResponse.checkAnswer();

            //Declaring and initializing animation object variable
            Animation animation = AnimationUtils.loadAnimation(getActivity(), R.anim.fragment_open_enter);

            //if the result is not answered then the UserAnswerConfirmation message will be displayed and the content of this message
            // is determined in the child fragments
            if (!result.isAnswered()) {

                mUserAnswerConfirmation.setVisibility(View.INVISIBLE);

                mUserAnswerConfirmation.setAnimation(animation);

                mUserAnswerConfirmation.setVisibility(View.VISIBLE);

                mUserAnswerConfirmation.setText(result.getUserAnswerConfirmation());

                return;
            }

            //if the result is answered and correct then the UserAnswerConfirmation message will be displayed and the content of this message
            //is determined in the child fragments. Additionally, the score will increase by 1 and it will be displayed along with changing
            // the text of the mConfirmButton to "Next Question"
            if (result.isCorrect()) {

                mUserAnswerConfirmation.setAnimation(animation);

                mUserAnswerConfirmation.setVisibility(View.VISIBLE);

                mUserAnswerConfirmation.setText(result.getUserAnswerConfirmation());

                mConfirmButton.setText(getString(R.string.next_question));

                score++;

                mScoreView.setText(getString(R.string.question_score_title, score));

            } else {

                //if the result is answered but incorrect then the UserAnswerConfirmation and the CorrectAnswerMessage will be displayed and
                // the content of these messages are determined in the child fragments. Additionally, the text of the mConfirmButton will be changed to "Next Question"
                mUserAnswerConfirmation.setAnimation(animation);

                mUserAnswerConfirmation.setVisibility(View.VISIBLE);

                mCorrectAnswerView.setAnimation(animation);

                mCorrectAnswerView.setVisibility(View.VISIBLE);

                mUserAnswerConfirmation.setText(result.getUserAnswerConfirmation());

                mCorrectAnswerView.setText(result.getCorrectAnswerMessage());

                inCorrectAnswers++;

                mConfirmButton.setText(getString(R.string.next_question));
            }

            //Increase state to 1 so that when the user clicks on the button the second time, the next question will be displayed
            state = 1;

        } else {

            //Case two or the second time click where we display the next question and reset the state back to zero
            //Increase the currentQuestion by 1
            currentQuestion++;

            //Display the currentQuestion, however when when we reach the final question, which is 9 in our case and the user clicks on the
            //"Next Question" button, don't display the currentQuestion which will be increased by one to 10
            if (currentQuestion <= totalQuestions) {

                mQuestionsCountView.setText(getString(R.string.question_counter_title, currentQuestion, totalQuestions));
            }

            //Retrieving the data of the question to be displayed from the Firestore database
            db.collection("Quiz").document("" + currentQuestion).get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                @Override
                public void onSuccess(DocumentSnapshot documentSnapshot) {

                    if (documentSnapshot != null) {

                        QuizData quizData = documentSnapshot.toObject(QuizData.class);

                        if (quizData != null) {

                            //reset the mConfirmButton, mUserAnswerConfirmation, and mCorrectAnswerView to their original state
                            mConfirmButton.setText(getString(R.string.confirm_button_name));

                            mUserAnswerConfirmation.setVisibility(View.INVISIBLE);

                            mCorrectAnswerView.setVisibility(View.INVISIBLE);

                            //Setup and display the next question
                            setUpQuestion(quizData);

                            // reset the state to zero which is the state that checks the user answer and displays the correct answer
                            state = 0;

                        } else {

                            //In case of no more questions left, then create a bundle that stores the score and totalQuestions variables
                            //and pass it to the ResultFragment along with displaying a Toast Message of the final score in the process
                            Bundle args = new Bundle();

                            args.putInt("score", score);

                            args.putInt("total questions", totalQuestions);

                            args.putInt("inCorrectAnswers", inCorrectAnswers);

                            Toast.makeText(getActivity(), "Your Final Score is " + score, Toast.LENGTH_SHORT).show();

                            Navigation.findNavController(view).navigate(R.id.action_questionsFragment_to_resultFragment, args);
                        }
                    }
                }
            });

        }
    }

    /**
     * This method is responsible for setting up and displaying the Quiz Question by retrieving the question data from the Firestore database
     * and based on the category of the question displaying the right fragment for the question type.
     *
     * @param quizData QuizData: is the Quiz Question data stored in Firestore database
     */
    private void setUpQuestion(QuizData quizData) {

        //Retrieving this data from the Firestore Databse
        question = quizData.getQuestion();

        explanation = quizData.getExplanation();

        String optionOne = quizData.getOptionOne();

        String optionTwo = quizData.getOptionTwo();

        String optionThree = quizData.getOptionThree();

        String optionFour = quizData.getOptionFour();

        String answer = quizData.getAnswer();

        String category = quizData.getCategory();

        //There are three categories for the Quiz questions and based on each category we will display the right fragment for this question type
        //Category is a field in each question document in the collection "Quiz" in Firestore database
        if (category.equals("singleChoice")) {

            //Declaring and initializing a singleChoiceFragment because the category of the question is singleChoice and passing it
            // the retrieved information in a bundle. This fragment also implements the QuestionResponse interface.
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

            //Declaring and initializing multipleChoiceAnswerOne and multipleChoiceAnswerTwo varaibles by retrieving the multipleChoiceAnswerOne
            // and multipleChoiceAnswerTwo data from the Firestore database and these fields are only found in the documents of the
            // multipleChoice Questions
            String multipleChoiceAnswerOne = quizData.getMultipleChoiceAnswerOne();

            String multipleChoiceAnswerTwo = quizData.getMultipleChoiceAnswerTwo();

            //Declaring and initializing a multipleChoiceFragment because the category of the question is multipleChoice and passing it
            //the retrieved information in a bundle. This fragment also implements the QuestionResponse interface.
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

            //Declaring and initializing a freeInputFragment because the category of the question is freeInput and passing it
            //the retrieved information in a bundle. This fragment also implements the QuestionResponse interface.
            FreeInputFragment freeInputFragment = new FreeInputFragment();

            questionResponse = freeInputFragment;

            Bundle args = new Bundle();

            args.putString("question", question);

            args.putString("answer", answer);

            freeInputFragment.setArguments(args);

            getChildFragmentManager().beginTransaction().replace(R.id.question_container, freeInputFragment).commit();
        }
    }

    /**
     * This method displays an explanation card when the user clicks on the explanation button. The card displays the question on hand along
     * with its answer.
     */
    private void displayExplanation() {

        if (getActivity() != null) {

            //Declaring and initializing a dialog object variable
            final Dialog dialog = new Dialog(getActivity());

            dialog.setContentView(R.layout.explanation_card);

            //Declaring and initializing the explanationQuestion and explanationAnswer Object Variables
            TextView explanationQuestion = dialog.findViewById(R.id.explanation_card_question);

            TextView explanationAnswer = dialog.findViewById(R.id.explanation_card_answer);

            //This makes the explanationAnswer TextView Scrollable
            explanationAnswer.setMovementMethod(new ScrollingMovementMethod());

            //Setting the text retrieved from the Firestore Database on the explanationQuestion and explanationAnswer Views.
            explanationQuestion.setText(question);

            explanationAnswer.setText(explanation);

            //Declaring and initializing the closeButton object variable
            ImageView closeButton = dialog.findViewById(R.id.explanation_close_button);

            //setting the background of the dialog window to transparent
            if (dialog.getWindow() != null) {

                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            }

            dialog.show();

            //Attaching an OnClickListener to the closeButton that determines the behavior that will happen when the user
            //clicks on that button
            closeButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dialog.dismiss();
                }
            });
        }
    }
}
