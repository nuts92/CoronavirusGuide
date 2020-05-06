package com.example.android.coronavirusguide.interfaces;

import com.example.android.coronavirusguide.data_models.Result;

/**
 * This QuestionResponse interface purpose is to be implemented by the three Question's Types Fragments which are SingleChoiceFragment,
 * MultipleChoiceFragment, and FreeInputFragment so that each fragment implement its own logic of the checkAnswer() method and return a
 * result that is going to be displayed in the QuestionsFragment.
 */
public interface QuestionResponse {

    Result checkAnswer();
}
