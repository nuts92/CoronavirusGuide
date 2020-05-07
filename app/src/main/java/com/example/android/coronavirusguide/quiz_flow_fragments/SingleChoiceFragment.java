package com.example.android.coronavirusguide.quiz_flow_fragments;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import com.example.android.coronavirusguide.R;
import com.example.android.coronavirusguide.data_models.Result;
import com.example.android.coronavirusguide.interfaces.QuestionResponse;

/**
 * SingleChoiceFragment subclass displays the Quiz Question which is of category singleChoice
 */
public class SingleChoiceFragment extends Fragment implements QuestionResponse {

    //Declaring all Object Variables
    private String question;

    private String optionOne;

    private String optionTwo;

    private String optionThree;

    private String optionFour;

    private String answer;

    private RadioGroup radioGroup;

    public SingleChoiceFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment_single_choice, container, false);

        //Initializing the radioGroup object variable
        radioGroup = rootView.findViewById(R.id.radio_group);

        //Declaring and Initializing all object variables
        TextView questionView = rootView.findViewById(R.id.single_choice_question);

        RadioButton radioButtonOne = rootView.findViewById(R.id.radio_button_one);

        RadioButton radioButtonTwo = rootView.findViewById(R.id.radio_button_two);

        RadioButton radioButtonThree = rootView.findViewById(R.id.radio_button_three);

        RadioButton radioButtonFour = rootView.findViewById(R.id.radio_button_four);

        //If there are arguments supplied when the fragment was instantiated, then initialize all these object variables
        if (getArguments() != null) {

            question = getArguments().getString("question");

            optionOne = getArguments().getString("optionOne");

            optionTwo = getArguments().getString("optionTwo");

            optionThree = getArguments().getString("optionThree");

            optionFour = getArguments().getString("optionFour");

            answer = getArguments().getString("answer");
        }

        //Setting the right text on these object variables
        questionView.setText(question);

        radioButtonOne.setText(optionOne);

        radioButtonTwo.setText(optionTwo);

        //There are singleChoice questions where there are only two options and option three and four will be null
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

    /**
     * This method checks the user answer and compare it to the correct answer then returns the result
     *
     * @return Result: returns a result object which includes the status of the userAnswer whether its correct or not and even whether it's answered or
     * left empty and additionally it contains the message to be displayed with every different status
     */
    @Override
    public Result checkAnswer() {

        //Declaring and Initializing the checkedRadioButton object variable
        RadioButton checkedRadioButton = null;

        if (getView() != null) {

            checkedRadioButton = getView().findViewById(radioGroup.getCheckedRadioButtonId());
        }

        //Declaring and Initializing result object variable
        Result result = new Result();

        //Checking the user answer through three situations where the first case is that user may not have entered an answer. The second case is
        //that the user has entered an answer and it's correct. Finally, the third case is that the user has answered an incorrect answer.
        if (checkedRadioButton != null) {

            String userAnswer = checkedRadioButton.getText().toString();

            if (userAnswer.equals(answer)) {

                result.setAnswered(true);

                result.setCorrect(true);

                result.setUserAnswerConfirmation((getString(R.string.answer_correct)));

            } else {

                result.setAnswered(true);

                result.setCorrect(false);

                result.setUserAnswerConfirmation((getString(R.string.answer_incorrect)));

                result.setCorrectAnswerMessage((getString(R.string.correct_answer_title) + " " + answer));
            }

        } else {

            result.setAnswered(false);

            result.setUserAnswerConfirmation(getString(R.string.select_one_choice));
        }

        return result;
    }
}
