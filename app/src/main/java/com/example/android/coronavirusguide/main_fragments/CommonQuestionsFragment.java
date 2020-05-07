package com.example.android.coronavirusguide.main_fragments;

import android.content.Intent;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.example.android.coronavirusguide.R;
import com.example.android.coronavirusguide.adapters.CardAdapter;
import com.example.android.coronavirusguide.data_models.CommonQuestionsData;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;

/**
 * CommonQuestionsFragment subclass displays a list of all the Common Questions regarding the COVID-19 Virus in the form of cards
 * ordered by the index where the user can share the information of any of these cards.
 */
public class CommonQuestionsFragment extends Fragment {

    //Declaring an instance of the CardAdapter
    private CardAdapter cardAdapter;

    public CommonQuestionsFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment_common_questions, container, false);

        //Declaring and initializing the recyclerView Object Variable.
        RecyclerView recyclerView = rootView.findViewById(R.id.common_questions_recycler_view);

        //Declaring and initializing an instance of Firestore database.
        FirebaseFirestore db = FirebaseFirestore.getInstance();

        //Declaring and initializing an instance of a CollectionReference called questionsCollectionRef which references a collection called
        //"Common Questions" that stores all the common questions and their answers.
        CollectionReference questionsCollectionRef = db.collection("Common Questions");

        //Declaring and initializing a query object that displays the data from Firestore ordered by index where index represents the question number
        //so question one has an index of 1 and question two has an index of 2, etc.
        Query query = questionsCollectionRef.orderBy("index", Query.Direction.ASCENDING);

        //configure the adapter by building FirestoreRecyclerOptions, Configure recycler adapter options:
        // query is the Query object defined above.
        // CommonQuestionsData.class instructs the adapter to convert each DocumentSnapshot to a CommonQuestionsData object
        FirestoreRecyclerOptions<CommonQuestionsData> options = new FirestoreRecyclerOptions.Builder<CommonQuestionsData>()
                .setQuery(query, CommonQuestionsData.class)
                .build();

        // Initializing the carAdapter Object Variable
        cardAdapter = new CardAdapter(options);

        //This setting improves performance if the changes in content do not change the layout size of the RecyclerView
        recyclerView.setHasFixedSize(true);

        //Set the layoutManager that the recyclerView will use
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        //Attaching the cardAdapter to the recyclerView with the setAdapter() method.
        recyclerView.setAdapter(cardAdapter);

        //Setting an OnItemClickListener to the cardAdapter that determines the behavior that will happen when the user
        //clicks on the shareButton
        cardAdapter.setOnItemClickListener(new CardAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(DocumentSnapshot documentSnapshot, int position) {

                //Declaring and initializing a CommonQuestionsData object that is created from the documentSnapshot
                CommonQuestionsData questionsData = documentSnapshot.toObject(CommonQuestionsData.class);

                //Declaring and initializing a message that will be shared when the user clicks on the shareButton
                String shareMessage = null;

                if (questionsData != null) {

                    String question = questionsData.getQuestion();

                    String answer = questionsData.getAnswer();

                    shareMessage = "Hey, Check out this question and answer about COVID-19: " + question + "\n" + answer;

                }

                //Use Android Sharesheet to send text content outside the app and/or directly to another user via email or social networking.
                //Create an intent and set its action to Intent.ACTION_SEND.
                Intent sendIntent = new Intent();

                sendIntent.setAction(Intent.ACTION_SEND);

                sendIntent.putExtra(Intent.EXTRA_TEXT, shareMessage);

                sendIntent.setType("text/plain");

                //In order to display the Android Sharesheet, call Intent.createChooser(), passing it the sendIntent object.
                // It returns a version of the intent that will always display the Android Sharesheet.
                Intent shareIntent = Intent.createChooser(sendIntent, null);

                startActivity(shareIntent);
            }
        });

        return rootView;
    }

    /**
     * This method is called when the Fragment is visible to the user.
     */
    @Override
    public void onStart() {

        super.onStart();

        //The FirestoreRecyclerAdapter uses a snapshot listener to monitor changes to the Firestore query.
        // To begin listening for data, we call the startListening() method on the cardAdapter.
        cardAdapter.startListening();
    }

    /**
     * This method is called when the Fragment is no longer started.
     */
    @Override
    public void onStop() {

        super.onStop();

        //The stopListening() call removes the snapshot listener and all data in the adapter. We call this method
        // when the containing Fragment stops.
        cardAdapter.stopListening();
    }
}
