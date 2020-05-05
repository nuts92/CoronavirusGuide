package com.example.android.coronavirusguide.prevention_guide_fragments;


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
public class PreventionFourFragment extends Fragment {


    public PreventionFourFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment_prevention_guide, container, false);

        ImageView preventionImage = rootView.findViewById(R.id.prevention_guide_image);

        preventionImage.setImageResource(R.drawable.prevention_guide_slide_four);

        return rootView;
    }

}
