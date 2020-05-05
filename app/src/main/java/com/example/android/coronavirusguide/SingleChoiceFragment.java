package com.example.android.coronavirusguide;


import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.example.android.coronavirusguide.interfaces.QuestionResponse;


/**
 * A simple {@link Fragment} subclass.
 */
public class SingleChoiceFragment extends Fragment implements QuestionResponse {

    private String question;

    private String optionOne;

    private String optionTwo;

    private String optionThree;

    private String optionFour;

    private String answer;

    private RadioGroup radioGroup;

    private RadioButton checkedRadioButton;


    public SingleChoiceFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment_single_choice, container, false);

        TextView questionView = rootView.findViewById(R.id.single_choice_question);

        radioGroup = rootView.findViewById(R.id.radio_group);

        RadioButton radioButtonOne = rootView.findViewById(R.id.radio_button_one);

        RadioButton radioButtonTwo = rootView.findViewById(R.id.radio_button_two);

        RadioButton radioButtonThree = rootView.findViewById(R.id.radio_button_three);

        RadioButton radioButtonFour = rootView.findViewById(R.id.radio_button_four);


        if(getArguments() != null){

            question = getArguments().getString("question");
            optionOne = getArguments().getString("optionOne");
            optionTwo = getArguments().getString("optionTwo");
            optionThree = getArguments().getString("optionThree");
            optionFour = getArguments().getString("optionFour");
            answer = getArguments().getString("answer");

        }

        questionView.setText(question);

        radioButtonOne.setText(optionOne);

        radioButtonTwo.setText(optionTwo);


        if (optionThree == null) {

            radioButtonThree.setVisibility(View.INVISIBLE);

        } else {

            radioButtonThree.setText(optionThree);
        }

        if (optionFour == null) {

            radioButtonFour.setVisibility(View.INVISIBLE);

        } else {

           radioButtonFour.setText(optionFour);
        }


        return rootView;
    }

    @Override
    public Result checkAnswer() {


//        RadioButton checkedRadioButton = null;

        if (getView() != null) {

            checkedRadioButton = getView().findViewById(radioGroup.getCheckedRadioButtonId());

        }

        Result result = new Result();


        if (checkedRadioButton != null) {

            String userAnswer = checkedRadioButton.getText().toString();

            if (userAnswer.equals(answer)) {

                result.setAnswered(true);
                result.setCorrect(true);
                result.setUserAnswerConfirmation(("Your Answer is correct"));

            } else {

                result.setAnswered(true);
                result.setCorrect(false);
                result.setUserAnswerConfirmation(("Your Answer is incorrect"));
                result.setCorrectAnswerMessage(("Correct Answer: " + answer));
            }

        } else {

            result.setAnswered(false);
            result.setUserAnswerConfirmation("Please Select One of the choices");
        }

        return result;

    }

}
