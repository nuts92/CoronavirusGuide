package com.example.android.coronavirusguide.manage_stress_fragments;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import com.example.android.coronavirusguide.R;

/**
 * ManageStressOneFragment subclass represents the first step in the Manage Stress Guide.
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

        //Declaring and initializing the manageStressImage object variable
        ImageView manageStressImage = rootView.findViewById(R.id.manage_stress_image);

        //Setting the right image for the manageStressImage object variable in Manage Stress Guide Step One
        manageStressImage.setImageResource(R.drawable.manage_stress_slide_one);

        return rootView;
    }
}
