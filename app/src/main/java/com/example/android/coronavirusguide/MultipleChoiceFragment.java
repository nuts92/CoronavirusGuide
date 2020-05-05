package com.example.android.coronavirusguide;


import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import com.example.android.coronavirusguide.interfaces.QuestionResponse;


/**
 * A simple {@link Fragment} subclass.
 */
public class MultipleChoiceFragment extends Fragment implements QuestionResponse {

    private String question;

    private String optionOne;

    private String optionTwo;

    private String optionThree;

    private String optionFour;

    private String answerOne;

    private String answerTwo;

    private int userChoices;

    private CheckBox checkBoxOne;

    private CheckBox checkBoxTwo;

    private CheckBox checkBoxThree;

    private CheckBox checkBoxFour;

    private Result result;

    private String userAnswer;

    public MultipleChoiceFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment_multiple_choice, container, false);

        TextView questionView = rootView.findViewById(R.id.multiple_choice_question);

        checkBoxOne = rootView.findViewById(R.id.check_box_one);

        checkBoxTwo = rootView.findViewById(R.id.check_box_two);

        checkBoxThree = rootView.findViewById(R.id.check_box_three);

        checkBoxFour = rootView.findViewById(R.id.check_box_four);

        result = new Result();


        if (getArguments() != null) {
            question = getArguments().getString("question");
            optionOne = getArguments().getString("optionOne");
            optionTwo = getArguments().getString("optionTwo");
            optionThree = getArguments().getString("optionThree");
            optionFour = getArguments().getString("optionFour");
            answerOne = getArguments().getString("multipleChoiceAnswerOne");
            answerTwo = getArguments().getString("multipleChoiceAnswerTwo");
        }

        questionView.setText(question);

        checkBoxOne.setText(optionOne);
        checkBoxTwo.setText(optionTwo);
        checkBoxThree.setText(optionThree);
        checkBoxFour.setText(optionFour);


        return rootView;
    }

    @Override
    public Result checkAnswer() {

        //getting the user answer and comparing it to the correct answer

        boolean choiceOne = checkBoxOne.isChecked();
        boolean choiceTwo = checkBoxTwo.isChecked();
        boolean choiceThree = checkBoxThree.isChecked();
        boolean choiceFour = checkBoxFour.isChecked();

        //evert time the user clicks on the confirm button, we want to reset the values of
        // no of choices and userAnswer

        userChoices = 0;
        userAnswer = "";


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

        String correctAnswer = answerOne + answerTwo;

            if (userChoices == 2) {

                if (userAnswer.equals(correctAnswer)) {

                    result.setAnswered(true);
                    result.setCorrect(true);
                    result.setUserAnswerConfirmation(("Your Answer is correct"));

                } else {

                    result.setAnswered(true);
                    result.setCorrect(false);
                    result.setUserAnswerConfirmation(("Your Answer is incorrect"));
                    result.setCorrectAnswerMessage(("Correct Answer: " + answerOne + " and " + answerTwo + "."));

                }

            } else {
                result.setAnswered(false);
                result.setUserAnswerConfirmation("Please Select Two of the choices");
            }

        return result;
    }
}
