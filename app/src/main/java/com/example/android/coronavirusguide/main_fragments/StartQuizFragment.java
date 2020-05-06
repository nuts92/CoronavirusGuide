package com.example.android.coronavirusguide.main_fragments;


import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.android.coronavirusguide.R;

/**
 * StartQuizFragment subclass represents Start A New Quiz Screen which is the first fragment screen that is displayed
 * when the MainActivity opens.
 */
public class StartQuizFragment extends Fragment {

    public StartQuizFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment_start_quiz, container, false);

        //Declaring and initializing the startQuizButton Object Variable
        Button startQuizButton = rootView.findViewById(R.id.start_quiz_button);

        //Attaching an OnClickListener to the startQuizButton that determines the behavior that will happen when the user
        //clicks on that button
        startQuizButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //Navigate to the QuestionsFragment
                Navigation.findNavController(v).navigate(R.id.action_nav_start_quiz_to_questionsFragment);
            }
        });

        return rootView;
    }
}
