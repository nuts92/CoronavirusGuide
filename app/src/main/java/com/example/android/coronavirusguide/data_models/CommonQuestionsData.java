package com.example.android.coronavirusguide.data_models;

/**
 * This class is a Data Model Class that stores the Common Questions Data and it follows the JavaBean naming pattern which allows Firestore
 * to automatically map values when getting and setting data. The rules that the pattern follows are that:
 * Getter and Setter methods are defined which are strictly named to what variable they correspond to (for example: getName() provides the name field).
 * An extra empty constructor is also defined which lets Firestore do the automatic data mapping.
 */
public class CommonQuestionsData {

    private String question;

    private String answer;

    private int index;

    public CommonQuestionsData() {
        //empty constructor is required for Firestore's automatic data mapping.
    }

    public CommonQuestionsData(String question, String answer, int index) {
        this.question = question;
        this.answer = answer;
        this.index = index;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }
}
