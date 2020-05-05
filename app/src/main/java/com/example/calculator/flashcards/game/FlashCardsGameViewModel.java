package com.example.calculator.flashcards.game;

import androidx.lifecycle.ViewModel;

public class FlashCardsGameViewModel extends ViewModel {

    private static final String TAG = "FlashCardsGameViewModel";

    private int mCurrentQuestion;
    private String mUserAnswer = "";



    public FlashCardsGameViewModel() {
    }

    public int getCurrentQuestion() {
        return mCurrentQuestion;
    }

    public void setCurrentQuestion(int mCurrentQuestion) {
        this.mCurrentQuestion = mCurrentQuestion;
    }

    public String getUserAnswer() {
        return mUserAnswer;
    }

    public void setUserAnswer(String mUserAnswer) {
        this.mUserAnswer = mUserAnswer;
    }
}
