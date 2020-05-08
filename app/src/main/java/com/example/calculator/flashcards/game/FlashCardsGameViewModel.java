package com.example.calculator.flashcards.game;

import androidx.lifecycle.ViewModel;

import java.util.ArrayList;
import java.util.List;

public class FlashCardsGameViewModel extends ViewModel {

    private static final String TAG = "FlashCardsGameViewModel";

    private int mCurrentQuestion;
    private String mUserAnswer = "";
    int getmCurrentQuestion = 0;
    int questionCorrect = 0;

    private List<Integer> answer = new ArrayList<>();
    private List<String> formula = new ArrayList<>();
    private List<String> arithmeticType = new ArrayList<>();
    private List<String> arithmeticSelected = new ArrayList<>();

    int numberOfQuestions;

    public FlashCardsGameViewModel() {
    }

    public int getCurrentQuestion() {
        return mCurrentQuestion;
    }

    public void setCurrentQuestion(int mCurrentQuestion) {
        this.mCurrentQuestion += mCurrentQuestion;
    }

    String getUserAnswer() {
        return mUserAnswer;
    }

    void setUserAnswer(String mUserAnswer) {
        this.mUserAnswer = mUserAnswer;
    }

    void addArithmeticType(String arithmetic) {
        arithmeticType.add(arithmetic);
    }

    List<String> getArithmeticType() {
        return arithmeticType;
    }

    int getNumberOfQuestions() {
        return numberOfQuestions;
    }

    void setNumberOfQuestions(int numberOfQuestions) {
        this.numberOfQuestions = numberOfQuestions;
    }

    public void addArithmeticSelected(String arithmetic) {
        arithmeticSelected.add(arithmetic);
    }

    List<String> getArithmeticSelected() {
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


}
