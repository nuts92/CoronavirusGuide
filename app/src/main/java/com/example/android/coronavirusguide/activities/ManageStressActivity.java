package com.example.android.coronavirusguide.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.android.coronavirusguide.R;
import com.example.android.coronavirusguide.manage_stress_fragments.ManageStressFiveFragment;
import com.example.android.coronavirusguide.manage_stress_fragments.ManageStressFourFragment;
import com.example.android.coronavirusguide.manage_stress_fragments.ManageStressOneFragment;
import com.example.android.coronavirusguide.manage_stress_fragments.ManageStressSixFragment;
import com.example.android.coronavirusguide.manage_stress_fragments.ManageStressThreeFragment;
import com.example.android.coronavirusguide.manage_stress_fragments.ManageStressTwoFragment;
import com.google.android.material.tabs.TabLayout;

public class ManageStressActivity extends AppCompatActivity {

    //Declaring and initialing a constant that represents the number of Manage Stress pages/fragments to be displayed.
    private static final int NUM_PAGES = 6;

    //Declaring the ViewPager widget, which handles animation and allows swiping horizontally to access previous
    // and next manage stress steps.
    private ViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_stress);

        //Initializing the mViewPager Object Variable
        mViewPager = findViewById(R.id.manage_stress_pager);

        //Declaring and initializing the pagerAdapter, which provides the pages to the ViewPager widget.
        ManageStressAdapter pagerAdapter = new ManageStressAdapter(getSupportFragmentManager());

        // Declaring and initializing the tabLayout and the finishButton object variables.
        TabLayout tabLayout = findViewById(R.id.manage_stress_indicator);
        Button finishButton = findViewById(R.id.manage_stress_button);

        //Setting the Adapter to the ViewPager through the setAdapter method.
        mViewPager.setAdapter(pagerAdapter);

        // Setting up the TabLayout with the ViewPager.
        tabLayout.setupWithViewPager(mViewPager);

        //Attaching an OnClickListener to the finishButton that determines the behavior that will happen
        // when the user clicks on that button
        finishButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    /**
     * OnBackPressed() is called when the activity has detected the user's press of the back key.
     */
    @Override
    public void onBackPressed() {

        // If the user is currently looking at the first step, allow the system to handle the
        // Back button. This calls finish() on this activity and pops the back stack.
        if (mViewPager.getCurrentItem() == 0) {

            super.onBackPressed();
        } else {

            // Otherwise, select the previous step.
            mViewPager.setCurrentItem(mViewPager.getCurrentItem() - 1);
        }
    }

    /**
     * This Class provides the Adapter to populate pages inside of the ViewPager
     * The pages represent 6 Fragment objects, in sequence.
     */
    private class ManageStressAdapter extends FragmentStatePagerAdapter {

        public ManageStressAdapter(FragmentManager fm) {
            super(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        }

        /**
         * This method gets the fragment at the specified position and returns it to be displayed
         *
         * @param position int: the position of the Fragment in the pager.
         * @return Fragment: returns the Fragment associated with a specified position.
         */
        @NonNull
        @Override
        public Fragment getItem(int position) {
            switch (position) {
                case 0: // Fragment number 1 - This will show First Manage Stress Fragment
                    return new ManageStressOneFragment();
                case 1: // Fragment number 2 - This will show Second Manage Stress Fragment
                    return new ManageStressTwoFragment();
                case 2: // Fragment number 2 - This will show Third Manage Stress Fragment
                    return new ManageStressThreeFragment();
                case 3: // Fragment number 2 - This will show Fourth Manage Stress Fragment
                    return new ManageStressFourFragment();
                case 4: // Fragment number 2 - This will show Fifth Manage Stress Fragment
                    return new ManageStressFiveFragment();
                case 5: // Fragment number 2 - This will show Sixth Manage Stress Fragment
                    return new ManageStressSixFragment();
                default:
                    return null;
            }
        }

        /**
         * This method counts the number of views/fragments that are in the data set represented by this Adapter.
         *
         * @return int the number of views available, in this case 6 Fragment objects
         */
        @Override
        public int getCount() {
            return NUM_PAGES;
        }
    }
}
