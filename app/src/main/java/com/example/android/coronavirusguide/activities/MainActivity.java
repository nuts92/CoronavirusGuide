package com.example.android.coronavirusguide.activities;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.android.coronavirusguide.R;
import com.example.android.coronavirusguide.data_models.UserData;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.navigation.NavigationView;
import com.google.common.collect.Sets;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;

/**
 * This class displays the MainActivity Screen that appears after the user has signed in successfully to the app where the user can
 * navigate between different sections including Test Your Knowledge, COVID-19 Symptoms, Prevention Guide, Manage Stress,
 * Common Questions, and Profile.
 */
public class MainActivity extends AppCompatActivity {

    //Declaring all object variables
    private AppBarConfiguration mAppBarConfiguration;

    private NavController mNavController;

    private ImageView mUserDisplayedPhoto;

    private TextView mUserDisplayedName;

    private TextView mUserDisplayedEmail;

    private String userId;

    private FirebaseUser mCurrentUser;

    private DocumentReference userDocumentReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Declaring and initializing an instance of Firebase Auth
        FirebaseAuth auth = FirebaseAuth.getInstance();

        //Declaring and initializing the drawerLayout object variable
        DrawerLayout drawerLayout = findViewById(R.id.drawer_layout);

        //Declaring and initializing the toolBar, the navigationView, and the navigationHeader object variables
        Toolbar toolbar = findViewById(R.id.toolbar);

        NavigationView navigationView = findViewById(R.id.navigation_view);

        //Calling getHeaderView method on the navigationView gets the navigation header view at the specified position.
        View navigationHeader = navigationView.getHeaderView(0);

        //Set the toolBar to act as the ActionBar for this Activity window.
        setSupportActionBar(toolbar);

        // Initializing the mAppBarConfiguration and passing each menu ID as a set of Ids because each menu should be considered as top level destinations.
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_start_quiz, R.id.nav_prevention_guide, R.id.nav_profile)
                .setOpenableLayout(drawerLayout)
                .build();

        //Initializing the mNavController object
        mNavController = Navigation.findNavController(this, R.id.fragment_container);

        //Set up the ActionBar returned by getSupportActionBar() for use with the mNavController.
        NavigationUI.setupActionBarWithNavController(this, mNavController, mAppBarConfiguration);

        // Sets up the navigationView for use with the mNavController. This will call onNavDestinationSelected(MenuItem, NavController) when a menu item is selected.
        // The selected item in the navigationView will automatically be updated when the destination changes.
        NavigationUI.setupWithNavController(navigationView, mNavController);

        //Declaring and initializing the ImageView and the two TextViews found in the navigationHeader
        mUserDisplayedPhoto = navigationHeader.findViewById(R.id.navigation_user_image);

        mUserDisplayedName = navigationHeader.findViewById(R.id.navigation_user_name);

        mUserDisplayedEmail = navigationHeader.findViewById(R.id.navigation_user_email);

        //Declaring and initializing an instance of FirebaseUser then check if the user is already signed in and not null
        mCurrentUser = auth.getCurrentUser();

        if (mCurrentUser != null) {

            //Get the userId from the currentUser and this Id is unique for every user and will be used to store data in FirebaseFirestore database
            userId = mCurrentUser.getUid();
        }

        //Declaring and initializing an instance of FirebaseFirestore database
        FirebaseFirestore db = FirebaseFirestore.getInstance();

        //Check if the user has data stored in Firestore database by initializing a userDocumentReference based on the unique userId,
        // the user data is stored in a document and the name of the document is the unique userId in a collection called "Users"
        userDocumentReference = db.collection("Users").document(userId);

        //Get the user document which contains information including updated name, email, photo, etc.
        userDocumentReference.addSnapshotListener(new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot documentSnapshot, @Nullable FirebaseFirestoreException e) {

                if (e != null) {
                    Log.e("MainActivity", e.toString());
                    return;
                }

                //Retrieve the user data by creating a retrievedData object from the documentSnapshot which is basically a snapshot of the user document
                if (documentSnapshot != null) {

                    UserData retrievedData = documentSnapshot.toObject(UserData.class);

                    //check if there is data stored and not null, this will be null if the user is new
                    //if there is data stored then display it, otherwise create a user document in Firestore so that it will be used to display data
                    if (retrievedData != null) {

                        displayDatabaseInfo(retrievedData);

                    } else {

                        createUserProfile();
                    }
                }
            }
        });
    }

    /**
     * This method displays the name, email, and photo of the user stored in the user document in FirebaseFirestore database
     *
     * @param retrievedData UserData: the user data retrieved from the document with userId as its name inside Users collection in FirebaseFirestore database
     */
    private void displayDatabaseInfo(UserData retrievedData) {

        String retrievedName = retrievedData.getUserDisplayName();

        String retrievedEmail = retrievedData.getUserEmail();

        mUserDisplayedName.setText(retrievedName);

        mUserDisplayedEmail.setText(retrievedEmail);

        //Check if there is a photo because if the user signed up with the email option which doesn't require a photo and then did not update his/her profile photo
        // the retrievedData.getUserPhoto() will return null and we will leave the default avatar to be the one displayed
        if (retrievedData.getUserPhoto() != null) {

            String retrievedPhoto = retrievedData.getUserPhoto();

            Glide.with(this).load(retrievedPhoto).into(mUserDisplayedPhoto);
        }
    }

    /**
     * This method creates a user document in the FirebaseFirestore database in case the user is a new one. In this case
     * we will display the data that is obtained from the FirebaseAuth instance instead.
     */
    private void createUserProfile() {

        String userName = mCurrentUser.getDisplayName();

        String userEmail = mCurrentUser.getEmail();

        //check if the user has a photo because if the user has signed up with the email option, he/she won't have a photo to be displayed
        String userProfilePhoto = null;

        if (mCurrentUser.getPhotoUrl() != null) {

            userProfilePhoto = mCurrentUser.getPhotoUrl().toString();
        }

        //Declaring and initializing a userData Object Variable that stores the user name, email, and photo if it exists to be passed to the database
        UserData userData = new UserData(userName, userEmail, userProfilePhoto);

        //Storing UserData by creating a User Document in Firestore database that will include the user name, the email, and the profile photo if it exists.
        //The user data will be stored in a document and the name of the document is the unique userId in a collection called "Users
        userDocumentReference.set(userData).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                Log.d("MainActivity", "User data is saved in Firestore successfully");
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {

                Log.e("MainActivity", e.toString());
            }
        });
    }

    /**
     * This method is called whenever the user chooses to navigate Up within your application's activity hierarchy from the action bar.
     *
     * @return boolean: true if Up navigation completed successfully and this Activity was finished, false otherwise.
     */
    @Override
    public boolean onSupportNavigateUp() {
        //can be global variable
        return NavigationUI.navigateUp(mNavController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }
}
