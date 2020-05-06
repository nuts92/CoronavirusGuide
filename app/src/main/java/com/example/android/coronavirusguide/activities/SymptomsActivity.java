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
import com.example.android.coronavirusguide.symptoms_fragments.SymptomsFiveFragment;
import com.example.android.coronavirusguide.symptoms_fragments.SymptomsFourFragment;
import com.example.android.coronavirusguide.symptoms_fragments.SymptomsOneFragment;
import com.example.android.coronavirusguide.symptoms_fragments.SymptomsThreeFragment;
import com.example.android.coronavirusguide.symptoms_fragments.SymptomsTwoFragment;
import com.google.android.material.tabs.TabLayout;

/**
 * This class displays the Symptoms Activity Screen where the user can learn more about the Symptoms of the COVID-19 Virus
 * and it displays the Symptoms in the form of five slides/fragments that the user can scroll and swipe horizontally.
 */
public class SymptomsActivity extends AppCompatActivity {

    //Declaring and initialing a constant that represents the number of symptoms' guide pages/fragments to be displayed.
    private static final int NUM_PAGES = 5;

    //Declaring the ViewPager widget, which handles animation and allows swiping horizontally to access previous
    //and next symptoms' pages/fragments.
    private ViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_symptoms);

        //Initializing the mViewPager Object Variable
        mViewPager = findViewById(R.id.symptoms_pager);

        //Declaring and initializing the pagerAdapter, which provides the pages to the ViewPager widget.
        SymptomsAdapter pagerAdapter = new SymptomsAdapter(getSupportFragmentManager());

        //Declaring and initializing the tabLayout and the finishButton object variables.
        TabLayout tabLayout = findViewById(R.id.symptoms_indicator);

        Button finishButton = findViewById(R.id.symptoms_button);

        //Setting the Adapter to the ViewPager through the setAdapter method.
        mViewPager.setAdapter(pagerAdapter);

        //Setting up the TabLayout with the ViewPager.
        tabLayout.setupWithViewPager(mViewPager);

        //Attaching an OnClickListener to the finishButton that determines the behavior that will happen
        // when the user clicks on that button
        finishButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //Finish the current activity
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
     * The pages represent 5 Fragment objects, in sequence.
     */
    private class SymptomsAdapter extends FragmentStatePagerAdapter {

        public SymptomsAdapter(FragmentManager fm) {
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
                case 0: // Fragment number 1 - This will show SymptomsOneFragment
                    return new SymptomsOneFragment();
                case 1: // Fragment number 2 - This will show SymptomsTwoFragment
                    return new SymptomsTwoFragment();
                case 2: // Fragment number 3 - This will show SymptomsThreeFragment
                    return new SymptomsThreeFragment();
                case 3: // Fragment number 4 - This will show SymptomsFourFragment
                    return new SymptomsFourFragment();
                case 4: // Fragment number 5 - This will show SymptomsFiveFragment
                    return new SymptomsFiveFragment();
                default:
                    return null;
            }
        }

        /**
         * This method counts the number of views/fragments that are in the data set represented by this Adapter.
         *
         * @return int the number of views available, in this case 5 Fragment objects
         */
        @Override
        public int getCount() {
            return NUM_PAGES;
        }
    }
}
