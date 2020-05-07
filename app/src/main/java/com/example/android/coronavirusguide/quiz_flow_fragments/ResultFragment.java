package com.example.android.coronavirusguide.quiz_flow_fragments;

import android.annotation.SuppressLint;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import com.airbnb.lottie.LottieAnimationView;
import com.example.android.coronavirusguide.R;

/**
 * ResultFragment subclass displays the final result of the Quiz that the user has just undertaken including the score, the number of correct
 * and incorrect answers in addition to intriguing the user to check out our prevention guide.
 */
public class ResultFragment extends Fragment {

    public ResultFragment() {
        // Required empty public constructor
    }

    @SuppressLint("SetTextI18n")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment_result, container, false);

        //Declaring and initializing all object variables
        LottieAnimationView resultAnimation = rootView.findViewById(R.id.result_animation);

        TextView resultTitle = rootView.findViewById(R.id.result_title);

        TextView resultScoreView = rootView.findViewById(R.id.result_score_value);

        TextView correctAnswersView = rootView.findViewById(R.id.result_correct_answers);

        TextView inCorrectAnswersView = rootView.findViewById(R.id.result_incorrect_answers);

        Button preventionGuideButton = rootView.findViewById(R.id.result_prevetion_guide_button);

        //Declaring and initializing the score and totalQuestions variables
        int score = 0;

        int totalQuestions = 0;

        //If there are arguments supplied when the fragment was instantiated, then update the score and totalQuestions variables values
        if (getArguments() != null) {

            score = getArguments().getInt("score");

            totalQuestions = getArguments().getInt("total questions");
        }

        //Declaring and initializing the correctAnswers and inCorrectAnswers variables
        int correctAnswers = score;

        int inCorrectAnswers = totalQuestions - score;

        //Display different animation and text in the resultAnimation and resultTitle views based on the comparison of the
        //correct answers to the incorrect ones.
        if (correctAnswers > inCorrectAnswers) {

            resultAnimation.setAnimation(R.raw.corona_super_fighter);

            resultAnimation.playAnimation();

            resultTitle.setText(getString(R.string.super_fighter_badge_text));

        } else {

            //Note that since the totalQuestions number is odd number then there will be no tie case and that's why there's only two situations and not three
            resultAnimation.setAnimation(R.raw.corona_read_more);

            resultAnimation.playAnimation();

            resultTitle.setText(getString(R.string.result_read_more_text));
        }

        resultScoreView.setText(getString(R.string.question_score_title) + " " + score + "/" + totalQuestions);

        correctAnswersView.setText(getString(R.string.result_correct_answers_title) + " " + correctAnswers);

        inCorrectAnswersView.setText(getString(R.string.result_incorrect_answers_title) + " " + inCorrectAnswers);

        //Attaching an OnClickListener to the preventionGuideButton that determines the behavior that will happen when the user
        //clicks on that button
        preventionGuideButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //Navigate to the PreventionGuideActivity
                Navigation.findNavController(v).navigate(R.id.action_resultFragment_to_nav_prevention_guide);
            }
        });

        return rootView;
    }
}
