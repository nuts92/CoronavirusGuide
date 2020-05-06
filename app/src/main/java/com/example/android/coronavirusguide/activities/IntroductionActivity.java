package com.example.android.coronavirusguide.activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.android.coronavirusguide.R;

/**
 * This class is responsible for displaying an introduction wizard where different questions with animations are automatically
 * scrolled horizontally that intrigue the user to test his/her knowledge and know more about the COVID-19 Virus.
 */
public class IntroductionActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_introduction);

        // Declaring and initializing the the nextButton object variable.
        Button nextButton = findViewById(R.id.next_button);

        //Attaching an OnClickListener to the nextButton that determines the behavior that will happen
        // when the user clicks on that button
        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finishBoarding();
            }
        });
    }

    /**
     * finishBoarding() method saves the state of whether the user has finished the introduction wizard onBoarding or not
     * and then redirects the user to the SignUpActivity.
     */
    private void finishBoarding() {

        //Get the sharedPreferences by calling getSharedPreferences method that returns a SharedPreference instance
        // pointing to the file that contains the values of preferences.
        SharedPreferences sharedPreferences = getSharedPreferences("MyPrefs", MODE_PRIVATE);

        //Save data changes in SharedPreferences by calling edit() method of SharedPreferences class which returns Editor class object.
        SharedPreferences.Editor editor = sharedPreferences.edit();

        //Call putBoolean method to save a boolean value in a preference editor and Set onBoardingComplete to true
        editor.putBoolean("onBoardingComplete", true).apply();

        // Open the SignUpActivity by passing intent as an input argument to the startActivity method
        Intent intent = new Intent(IntroductionActivity.this, SignUpActivity.class);

        startActivity(intent);

        // Close the IntroductionActivity so that when user presses the back button, he/she won't go back to this introduction wizard
        finish();
    }
}
