package com.example.calculator.flashcards.game;

import android.content.Context;
import android.util.Log;
import android.view.View;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;
import androidx.navigation.Navigation;

import com.example.calculator.R;

public class GameHandler extends BaseObservable {

    private static final String TAG = "Calculator FlashCardHandlers";

    Context mContext;

    public GameHandler(Context mContext) {
        this.mContext = mContext;
    }



}
