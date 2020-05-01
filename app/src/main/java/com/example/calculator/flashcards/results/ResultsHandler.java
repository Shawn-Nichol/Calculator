package com.example.calculator.flashcards.results;

import android.content.Context;
import android.util.Log;
import android.view.View;

import androidx.databinding.BaseObservable;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.example.calculator.R;

public class ResultsHandler extends BaseObservable {

    private static final String TAG = "Calculator Handler";

    private Context context;

    public ResultsHandler(Context context) {
        this.context = context;
    }

    public void newGame(View view) {
        Log.d(TAG, "newGame: ");
        Navigation.findNavController(view).navigate(R.id.action_flashCardResultsFragment_to_flashCardsSettingFragment);
    }
}
