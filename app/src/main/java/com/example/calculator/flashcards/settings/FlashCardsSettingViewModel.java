package com.example.calculator.flashcards.settings;

import androidx.lifecycle.ViewModel;

public class FlashCardsSettingViewModel extends ViewModel {

    private static final String TAG = "FlashCardsSettingViewMo";

    int mNumberOfQuestions = 5;

    public FlashCardsSettingViewModel() {

    }


    public int getNumberOfQuestions() {
        return mNumberOfQuestions;
    }

    public void setNumberOfQuestions(int mNumberOfQuestions) {
        this.mNumberOfQuestions = mNumberOfQuestions;
    }
}
