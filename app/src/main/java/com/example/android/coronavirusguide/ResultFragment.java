package com.example.android.coronavirusguide;


import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.airbnb.lottie.LottieAnimationView;

/**
 * A simple {@link Fragment} subclass.
 */
public class ResultFragment extends Fragment {


    public ResultFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment_result, container, false);

        LottieAnimationView resultAnimation = rootView.findViewById(R.id.result_animation);

        TextView resultTitle = rootView.findViewById(R.id.result_title);

        TextView resultScoreView = rootView.findViewById(R.id.result_score_value);

        TextView  correctAnswersView = rootView.findViewById(R.id.result_correct_answers);

        TextView inCorrectAnswersView = rootView.findViewById(R.id.result_incorrect_answers);

        Button symptomsButton = rootView.findViewById(R.id.result_symptoms_button);

        Button preventionGuideButton = rootView.findViewById(R.id.result_prevention_button);

        Button manageStressButton = rootView.findViewById(R.id.result_manage_stress_button);

        int score = 0;

        int totalQuestions = 0;

        if(getArguments() != null){

           score = getArguments().getInt("score");
           totalQuestions = getArguments().getInt("total questions");
        }

        int correctAnswers = score;

        int inCorrectAnswers = totalQuestions - score;


        if(correctAnswers > inCorrectAnswers) {

            resultAnimation.setAnimation(R.raw.corona_ninja);
            resultAnimation.playAnimation();

            resultTitle.setText("Congrats, You've earned the COVID-19 Ninja Badge");




        } else {// in this case there is no tie cause the total number if questions is odd however if its even we would have another if case

            resultAnimation.setAnimation(R.raw.corona_read_more);
            resultAnimation.playAnimation();

            resultTitle.setText("You need to read more about the COVID-19 Virus");
        }

        resultScoreView.setText("Score: " + score + "/" + totalQuestions);

        correctAnswersView.setText("Correct Answers: " + correctAnswers);

        inCorrectAnswersView.setText("Incorrect Answers: " + inCorrectAnswers);



        return rootView;

    }

}
