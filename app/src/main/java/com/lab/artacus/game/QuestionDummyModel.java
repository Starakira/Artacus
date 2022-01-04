package com.lab.artacus.game;

public class QuestionDummyModel {
    String question, rightAnswer;
    String[] answersArray;

    public QuestionDummyModel(String question, String[] answersArray, String rightAnswer) {
        this.question = question;
        this.answersArray = answersArray;
        this.rightAnswer = rightAnswer;
    }
}
