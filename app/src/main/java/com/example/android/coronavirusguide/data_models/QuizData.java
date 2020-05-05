package com.example.android.coronavirusguide.data_models;

public class QuizData {

    private String question;

    private String optionOne;

    private String optionTwo;

    private String optionThree;

    private String optionFour;

    private String answer;

    private String multipleChoiceAnswerOne;

    private String multipleChoiceAnswerTwo;

    private String explanation;

    private String category;

    public QuizData() {

    }

    public QuizData(String question, String optionOne, String optionTwo, String optionThree, String optionFour, String answer, String multipleChoiceAnswerOne, String multipleChoiceAnswerTwo, String explanation, String category) {
        this.question = question;
        this.optionOne = optionOne;
        this.optionTwo = optionTwo;
        this.optionThree = optionThree;
        this.optionFour = optionFour;
        this.answer = answer;
        this.multipleChoiceAnswerOne = multipleChoiceAnswerOne;
        this.multipleChoiceAnswerTwo = multipleChoiceAnswerTwo;
        this.explanation = explanation;
        this.category = category;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getOptionOne() {
        return optionOne;
    }

    public void setOptionOne(String optionOne) {
        this.optionOne = optionOne;
    }

    public String getOptionTwo() {
        return optionTwo;
    }

    public void setOptionTwo(String optionTwo) {
        this.optionTwo = optionTwo;
    }

    public String getOptionThree() {
        return optionThree;
    }

    public void setOptionThree(String optionThree) {
        this.optionThree = optionThree;
    }

    public String getOptionFour() {
        return optionFour;
    }

    public void setOptionFour(String optionFour) {
        this.optionFour = optionFour;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public String getMultipleChoiceAnswerOne() {
        return multipleChoiceAnswerOne;
    }

    public void setMultipleChoiceAnswerOne(String multipleChoiceAnswerOne) {
        this.multipleChoiceAnswerOne = multipleChoiceAnswerOne;
    }

    public String getMultipleChoiceAnswerTwo() {
        return multipleChoiceAnswerTwo;
    }

    public void setMultipleChoiceAnswerTwo(String multipleChoiceAnswerTwo) {
        this.multipleChoiceAnswerTwo = multipleChoiceAnswerTwo;
    }

    public String getExplanation() {
        return explanation;
    }

    public void setExplanation(String explanation) {
        this.explanation = explanation;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
}
