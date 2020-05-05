package com.example.android.coronavirusguide.manage_stress_fragments;


import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.android.coronavirusguide.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class ManageStressOneFragment extends Fragment {


    public ManageStressOneFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment_manage_stress, container, false);

        ImageView manageStressImage = rootView.findViewById(R.id.manage_stress_image);

        manageStressImage.setImageResource(R.drawable.manage_stress_slide_one);

        return rootView;
    }

}
