package com.example.calculator.flashcards.game;

import android.content.Context;
import android.util.Log;
import android.view.View;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;
import androidx.databinding.library.baseAdapters.BR;


public class GameHandler extends BaseObservable {

    private static final String TAG = "Calculator FlashCardHandlers";

    Context mContext;
    FlashCardsGameViewModel mViewModel;


    public GameHandler(Context mContext, FlashCardsGameViewModel viewModel) {
        this.mContext = mContext;
        this.mViewModel = viewModel;
    }

    public void setNumber(View view, String number) {
        Log.d(TAG, "setNumber: " + number);
        mViewModel.setUserAnswer(mViewModel.getUserAnswer() + number);

        notifyPropertyChanged(BR.number);
    }

    @Bindable
    public String getNumber() {
        Log.d(TAG, "getNumber: " + mViewModel.getUserAnswer());
        return mViewModel.getUserAnswer();
    }

    public void deleteLastNumber() {
        String number = mViewModel.getUserAnswer();
        int stringLength = number.length();

        if(stringLength != 0) {
            mViewModel.setUserAnswer(number.substring(0, stringLength-1));
        }
        notifyPropertyChanged(BR.number);
    }

    public void compareAnswer() {
        String userAnswer = mViewModel.getUserAnswer();
    }
}
