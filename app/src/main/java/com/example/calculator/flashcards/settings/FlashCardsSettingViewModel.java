package com.example.calculator.flashcards.settings;

import androidx.lifecycle.ViewModel;

public class FlashCardsSettingViewModel extends ViewModel {

    private static final String TAG = "FlashCardsSettingViewMo";

    private int mNumberOfQuestions = 5;
    private boolean mAddition;
    private boolean mMinus;
    private boolean mMultiplication;
    private boolean mDivision;

    public FlashCardsSettingViewModel() {

    }


    public int getNumberOfQuestions() {
        return mNumberOfQuestions;
    }

    public void setNumberOfQuestions(int mNumberOfQuestions) {
        this.mNumberOfQuestions = mNumberOfQuestions;
    }

    public boolean getAddition() {
        return mAddition;
    }

    public void setAddition(boolean mAddition) {
        this.mAddition = mAddition;
    }

    public boolean getMinus() {
        return mMinus;
    }

    public void setMinus(boolean mMinus) {
        this.mMinus = mMinus;
    }

    public boolean getMultiplication() {
        return mMultiplication;
    }

    public void setMultiplication(boolean mMultiplication) {
        this.mMultiplication = mMultiplication;
    }

    public boolean getDivision() {
        return mDivision;
    }

    public void setDivision(boolean mDivision) {
        this.mDivision = mDivision;
    }


}
