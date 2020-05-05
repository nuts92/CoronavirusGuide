package com.example.android.coronavirusguide;


import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.example.android.coronavirusguide.interfaces.QuestionResponse;


/**
 * A simple {@link Fragment} subclass.
 */
public class FreeInputFragment extends Fragment implements QuestionResponse {

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

        TextView questionView = rootView.findViewById(R.id.free_input_question);

        freeInputEditText = rootView.findViewById(R.id.free_input_edit_text);


        result = new Result();


        if(getArguments() != null){

            question = getArguments().getString("question");
            answer = getArguments().getString("answer");

        }

        questionView.setText(question);


        return rootView;
    }

    @Override
    public Result checkAnswer() {
        //Checking the user answer and compare it to correct answer
         String userAnswer = freeInputEditText.getText().toString().trim();

         //
        char[] userAnswerCharArray = userAnswer.toCharArray();
        StringBuilder builder = new StringBuilder();
        for (char currentChar: userAnswerCharArray) {

            String currentCharString = String.valueOf(currentChar);

            try {
                int numberValue = Integer.parseInt(currentCharString);

                builder.append(numberValue);


            } catch (NumberFormatException e) {
                // Ignore
            }
        }


        if (TextUtils.isEmpty(userAnswer)) {

            result.setAnswered(false);

            freeInputEditText.setError("Answer is required");
            result.setUserAnswerConfirmation(("Please Enter Your Answer"));

        } else if (builder.toString().equals(answer)) {

            result.setAnswered(true);
            result.setCorrect(true);
            result.setUserAnswerConfirmation(("Your Answer is correct"));

        } else {

            result.setAnswered(true);
            result.setCorrect(false);
            result.setUserAnswerConfirmation(("Your Answer is incorrect"));
            result.setCorrectAnswerMessage(("Correct Answer: " + answer));

        }

        return result;
    }

}
