package com.example.android.coronavirusguide;

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
