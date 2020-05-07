package com.example.android.coronavirusguide.quiz_flow_fragments;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import com.example.android.coronavirusguide.R;
import com.example.android.coronavirusguide.data_models.Result;
import com.example.android.coronavirusguide.interfaces.QuestionResponse;

/**
 * FreeInputFragment subclass displays the Quiz Question which is of category freeInput
 */
public class FreeInputFragment extends Fragment implements QuestionResponse {

    //Declaring all Object Variables
    private String question;

    private String answer;

    private Result result;

    private EditText freeInputEditText;

    public FreeInputFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment_free_input, container, false);

        //Declaring and Initializing the questionView object variable
        TextView questionView = rootView.findViewById(R.id.free_input_question);

        //Initializing the freeInputEditText object variable
        freeInputEditText = rootView.findViewById(R.id.free_input_edit_text);

        //Initializing the result object variable
        result = new Result();

        //If there are arguments supplied when the fragment was instantiated, then initialize the question and answer object variables
        if (getArguments() != null) {

            question = getArguments().getString("question");

            answer = getArguments().getString("answer");
        }

        //Setting the question as text on the questionView
        questionView.setText(question);

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

        //Declaring and initializing userAnswer object variable based on the input that the user has entered in the freeInputEditText
        String userAnswer = freeInputEditText.getText().toString().trim();

        //This code extracts the numerical value in the text the user has entered so that we compare it to the correct answer stored in the database
        //The reason for this is that for example if the correct answer is 80 and the user has entered 80 percent or 80%, The answer should be considered correct
        char[] userAnswerCharArray = userAnswer.toCharArray();

        StringBuilder builder = new StringBuilder();

        for (char currentChar : userAnswerCharArray) {

            String currentCharString = String.valueOf(currentChar);

            try {
                int numberValue = Integer.parseInt(currentCharString);

                builder.append(numberValue);


            } catch (NumberFormatException e) {

                Log.e("FreeInputFragment", e.toString());
            }
        }

        //Checking the user answer through three situations where the first case is that user may not have entered an answer. The second case is
        //that the user has entered an answer and it's correct. Finally, the third case is that the user has answered an incorrect answer.
        if (TextUtils.isEmpty(userAnswer)) {

            result.setAnswered(false);

            freeInputEditText.setError(getString(R.string.answer_required));

            result.setUserAnswerConfirmation(getString(R.string.enter_your_answer));

        } else if (builder.toString().equals(answer)) {

            result.setAnswered(true);

            result.setCorrect(true);

            result.setUserAnswerConfirmation((getString(R.string.answer_correct)));

        } else {

            result.setAnswered(true);

            result.setCorrect(false);

            result.setUserAnswerConfirmation((getString(R.string.answer_incorrect)));

            result.setCorrectAnswerMessage((getString(R.string.correct_answer_title) + " " + answer));
        }

        return result;
    }
}
