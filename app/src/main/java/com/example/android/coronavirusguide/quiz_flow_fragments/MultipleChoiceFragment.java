package com.example.android.coronavirusguide.quiz_flow_fragments;

import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;
import com.example.android.coronavirusguide.R;
import com.example.android.coronavirusguide.data_models.Result;
import com.example.android.coronavirusguide.interfaces.QuestionResponse;

/**
 * MultipleChoiceFragment subclass displays the Quiz Question which is of category multipleChoice
 */
public class MultipleChoiceFragment extends Fragment implements QuestionResponse {

    //Declaring all Object Variables
    private String question;

    private String optionOne;

    private String optionTwo;

    private String optionThree;

    private String optionFour;

    private String answerOne;

    private String answerTwo;

    private CheckBox checkBoxOne;

    private CheckBox checkBoxTwo;

    private CheckBox checkBoxThree;

    private CheckBox checkBoxFour;

    private Result result;

    public MultipleChoiceFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Control whether a fragment instance is retained across Activity re-creation (such as from a configuration change).
        setRetainInstance(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment_multiple_choice, container, false);

        //Declaring and Initializing the questionView object variable
        TextView questionView = rootView.findViewById(R.id.multiple_choice_question);

        //Initializing all object variables
        checkBoxOne = rootView.findViewById(R.id.check_box_one);

        checkBoxTwo = rootView.findViewById(R.id.check_box_two);

        checkBoxThree = rootView.findViewById(R.id.check_box_three);

        checkBoxFour = rootView.findViewById(R.id.check_box_four);

        result = new Result();

        //If there are arguments supplied when the fragment was instantiated, then initialize all these object variables
        if (getArguments() != null) {

            question = getArguments().getString("question");

            optionOne = getArguments().getString("optionOne");

            optionTwo = getArguments().getString("optionTwo");

            optionThree = getArguments().getString("optionThree");

            optionFour = getArguments().getString("optionFour");

            answerOne = getArguments().getString("multipleChoiceAnswerOne");

            answerTwo = getArguments().getString("multipleChoiceAnswerTwo");
        }

        //Setting the right text on these object variables
        questionView.setText(question);

        checkBoxOne.setText(optionOne);

        checkBoxTwo.setText(optionTwo);

        checkBoxThree.setText(optionThree);

        checkBoxFour.setText(optionFour);

        return rootView;
    }

    /**
     * This method checks the user answer and compare it to the correct answer then returns the result
     *
     * @return Result: returns a result object which includes the status of the userAnswer whether its correct or not and even whether it's answered or
     * left empty and additionally it contains the message to be displayed with every different status
     */
    @Override
    public Result checkAnswer() {

        //Declaring and initializing these object variables based on what checkBoxes the user has checked
        boolean choiceOne = checkBoxOne.isChecked();

        boolean choiceTwo = checkBoxTwo.isChecked();

        boolean choiceThree = checkBoxThree.isChecked();

        boolean choiceFour = checkBoxFour.isChecked();

        //Declaring and initializing userChoices and userAnswer variables, everyt ime the user clicks on the confirm button,
        //the userChoices and userAnswer values are reset
        int userChoices = 0;

        String userAnswer = "";

        //When the user checks one of the checkBoxes, the userAnswer value is updated and the userChoices increase by 1
        if (choiceOne) {

            userAnswer = optionOne;
            userChoices++;
        }

        if (choiceTwo) {

            userAnswer = userAnswer + optionTwo;
            userChoices++;
        }

        if (choiceThree) {

            userAnswer = userAnswer + optionThree;
            userChoices++;
        }

        if (choiceFour) {

            userAnswer = userAnswer + optionFour;
            userChoices++;
        }

        //Declaring and initializing the correctAnswer object variable which is a String of both answerOne and answerTwo which are the answers
        // stored in the Firestore database and since this is a multipleChoice question there are two answers in our case that are correct
        String correctAnswer = answerOne + answerTwo;

        //If the user didn't choose 2 choices, the question will be considered not answered. However, if the user did choose 2 choices,
        //then the user answer will be compared to the correctAnswer and the result will include either correct or incorrect answer messages.
        if (userChoices == 2) {

            if (userAnswer.equals(correctAnswer)) {

                result.setAnswered(true);

                result.setCorrect(true);

                result.setUserAnswerConfirmation((getString(R.string.answer_correct)));

            } else {

                result.setAnswered(true);

                result.setCorrect(false);

                result.setUserAnswerConfirmation((getString(R.string.answer_incorrect)));

                result.setCorrectAnswerMessage((getString(R.string.correct_answer_title) + " " + answerOne + " and " + answerTwo + "."));
            }

        } else {

            result.setAnswered(false);

            result.setUserAnswerConfirmation(getString(R.string.select_two_choices));
        }

        return result;
    }
}
