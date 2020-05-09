package com.example.calculator.flashcards.game;

import androidx.lifecycle.ViewModel;

import java.util.ArrayList;
import java.util.List;

public class FlashCardsGameViewModel extends ViewModel {

    private static final String TAG = "FlashCardsGameViewModel";

    private String mUserAnswer = "";
    private int mCurrentQuestion;
    private int mQuestionCorrect = 0;
    private int numberOfQuestions;

    private List<Integer> answer = new ArrayList<>();
    private List<String> formula = new ArrayList<>();
    private List<String> arithmeticType = new ArrayList<>();
    private List<String> arithmeticSelected = new ArrayList<>();


    public FlashCardsGameViewModel() {
    }

    public int getCurrentQuestion() {
        return mCurrentQuestion;
    }

    public void setCurrentQuestion(int mCurrentQuestion) {
        this.mCurrentQuestion += mCurrentQuestion;
    }

    public String getUserAnswer() {
        return mUserAnswer;
    }

    public void setUserAnswer(String mUserAnswer) {
        this.mUserAnswer = mUserAnswer;
    }

    public void addArithmeticType(String arithmetic) {
        arithmeticType.add(arithmetic);
    }

    public List<String> getArithmeticType() {
        return arithmeticType;
    }

    public int getNumberOfQuestions() {
        return numberOfQuestions;
    }

    public void setNumberOfQuestions(int numberOfQuestions) {
        this.numberOfQuestions = numberOfQuestions;
    }

    public void addArithmeticSelected(String arithmetic) {
        arithmeticSelected.add(arithmetic);
    }

    public List<String> getArithmeticSelected() {
        return arithmeticSelected;
    }

    public List<Integer> getAnswer() {
        return answer;
    }

    public void addAnswer(int answer) {
        this.answer.add(answer);
    }

    public List<String> getFormula() {
        return formula;
    }

    public void addFormula(String formula) {
        this.formula.add(formula);
    }

    public int getCorrectQuestions() {
        return mQuestionCorrect;
    }

    public void setQuestionCorrect(int mQuestionCorrect) {
        this.mQuestionCorrect += mQuestionCorrect;
    }
}
