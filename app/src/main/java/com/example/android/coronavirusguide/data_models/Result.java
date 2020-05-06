package com.example.android.coronavirusguide.data_models;

/**
 * This class is a Data Model Class that stores the Result Data and it stores whether the question is answered or not and whether the result is
 * correct or incorrect. Additionally, based on the result the userAnswerConfirmation and correctAnswerMessage store different values that are
 * going to be displayed when the user clicks on the confirm button.
 */
public class Result {

    private boolean isCorrect;

    private boolean isAnswered;

    private String userAnswerConfirmation;

    private String correctAnswerMessage;

    public boolean isCorrect() {
        return isCorrect;
    }

    public void setCorrect(boolean correct) {
        isCorrect = correct;
    }

    public boolean isAnswered() {
        return isAnswered;
    }

    public void setAnswered(boolean answered) {
        isAnswered = answered;
    }

    public String getUserAnswerConfirmation() {
        return userAnswerConfirmation;
    }

    public void setUserAnswerConfirmation(String userAnswerConfirmation) {
        this.userAnswerConfirmation = userAnswerConfirmation;
    }

    public String getCorrectAnswerMessage() {
        return correctAnswerMessage;
    }

    public void setCorrectAnswerMessage(String correctAnswerMessage) {
        this.correctAnswerMessage = correctAnswerMessage;
    }
}
