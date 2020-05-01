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


    public void endGame(View view) {
        Log.d(TAG, "startGame: ");
        Navigation.findNavController(view).navigate(R.id.action_flashCardGameFragment_to_flashCardResultsFragment);
    }
}
