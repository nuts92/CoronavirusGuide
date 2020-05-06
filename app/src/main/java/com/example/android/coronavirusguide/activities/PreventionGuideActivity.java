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
import com.example.android.coronavirusguide.prevention_guide_fragments.PreventionEightFragment;
import com.example.android.coronavirusguide.prevention_guide_fragments.PreventionFiveFragment;
import com.example.android.coronavirusguide.prevention_guide_fragments.PreventionFourFragment;
import com.example.android.coronavirusguide.prevention_guide_fragments.PreventionOneFragment;
import com.example.android.coronavirusguide.prevention_guide_fragments.PreventionSevenFragment;
import com.example.android.coronavirusguide.prevention_guide_fragments.PreventionSixFragment;
import com.example.android.coronavirusguide.prevention_guide_fragments.PreventionThreeFragment;
import com.example.android.coronavirusguide.prevention_guide_fragments.PreventionTwoFragment;
import com.google.android.material.tabs.TabLayout;

/**
 * This class displays the Prevention Guide Activity Screen where the user can learn more about the necessary steps that should be taken to
 * protect himself/herself and their loved ones from the danger of the COVID-19 Virus. The Prevention Guide Steps are displayed in the form
 * of eight slides/fragments that the user can scroll and swipe horizontally.
 */
public class PreventionGuideActivity extends AppCompatActivity {

    //Declaring and initialing a constant that represents the number of Prevention Guide's pages/fragments to be displayed.
    private static final int NUM_PAGES = 8;

    //Declaring the ViewPager widget, which handles animation and allows swiping horizontally to access previous
    //and next prevention guide's pages/fragments.
    private ViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prevention_guide);

        //Initializing the mViewPager Object Variable
        mViewPager = findViewById(R.id.prevention_guide_pager);

        //Declaring and initializing the pagerAdapter, which provides the pages to the ViewPager widget.
        PreventionAdapter pagerAdapter = new PreventionAdapter(getSupportFragmentManager());

        //Declaring and initializing the tabLayout and the finishButton object variables.
        TabLayout tabLayout = findViewById(R.id.prevention_guide_indicator);

        Button finishButton = findViewById(R.id.prevention_guide_button);

        //Setting the Adapter to the ViewPager through the setAdapter method.
        mViewPager.setAdapter(pagerAdapter);

        //Setting up the TabLayout with the ViewPager.
        tabLayout.setupWithViewPager(mViewPager);

        //Attaching an OnClickListener to the finishButton that determines the behavior that will happen
        //when the user clicks on that button
        finishButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //Finish the Current Activity
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
     * The pages represent 8 Fragment objects, in sequence.
     */
    private class PreventionAdapter extends FragmentStatePagerAdapter {

        public PreventionAdapter(FragmentManager fm) {
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
                case 0: // Fragment number 1 - This will show PreventionOneFragment
                    return new PreventionOneFragment();
                case 1: // Fragment number 2 - This will show PreventionTwoFragment
                    return new PreventionTwoFragment();
                case 2: // Fragment number 3 - This will show PreventionThreeFragment
                    return new PreventionThreeFragment();
                case 3: // Fragment number 4 - This will show PreventionFourFragment
                    return new PreventionFourFragment();
                case 4: // Fragment number 5 - This will show PreventionFiveFragment
                    return new PreventionFiveFragment();
                case 5: // Fragment number 6 - This will show PreventionSixFragment
                    return new PreventionSixFragment();
                case 6: // Fragment number 7 - This will show PreventionSevenFragment
                    return new PreventionSevenFragment();
                case 7: // Fragment number 8 - This will show PreventionEightFragment
                    return new PreventionEightFragment();
                default:
                    return null;
            }
        }

        /**
         * This method counts the number of views/fragments that are in the data set represented by this Adapter.
         *
         * @return int the number of views available, in this case 8 Fragment objects
         */
        @Override
        public int getCount() {
            return NUM_PAGES;
        }
    }
}
