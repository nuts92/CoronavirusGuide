package com.example.android.coronavirusguide.data_models;

public class CommonQuestionsData {

    private String question;

    private String answer;

    private int index;

    public  CommonQuestionsData() {
        //empty constructor needed
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
