package com.example.android.coronavirusguide.symptoms_fragments;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import com.example.android.coronavirusguide.R;

/**
 * SymptomsTwoFragment subclass represents the second step in the COVID-19 Symptoms Guide.
 */
public class SymptomsTwoFragment extends Fragment {

    public SymptomsTwoFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment_symptoms, container, false);

        //Declaring and initializing the symptomsImage object variable
        ImageView symptomsImage = rootView.findViewById(R.id.symptoms_image);

        //Setting the right image for the symptomsImage object variable in COVID-19 Symptoms Guide Step Two
        symptomsImage.setImageResource(R.drawable.symptoms_slide_two);

        return rootView;
    }
}
