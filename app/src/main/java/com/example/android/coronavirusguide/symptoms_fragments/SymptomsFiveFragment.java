package com.example.android.coronavirusguide.symptoms_fragments;


import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.android.coronavirusguide.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class SymptomsFiveFragment extends Fragment {


    public SymptomsFiveFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment_symptoms, container, false);

        ImageView symptomsImage = rootView.findViewById(R.id.symptoms_image);

        symptomsImage.setImageResource(R.drawable.symptoms_slide_five);

        return rootView;
    }

}
