package com.example.calculator.flashcards.results;

import android.content.Context;
import android.util.Log;
import android.view.View;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.example.calculator.R;

public class ResultsHandler extends BaseObservable {

    private static final String TAG = "Calculator Handler";

    private Context context;
    private int mCorrectAnswers;
    private int mNumberOfQuestions;

    public ResultsHandler(Context context, int mCorrectAnswers, int mNumberOfQuestions) {
        this.context = context;
        this.mCorrectAnswers = mCorrectAnswers;
        this.mNumberOfQuestions = mNumberOfQuestions;
    }

    public void newGame(View view) {
        Log.d(TAG, "newGame: ");
        Navigation.findNavController(view).navigate(R.id.action_flashCardResultsFragment_to_flashCardsSettingFragment);
    }

    @Bindable
    public String getResults() {
        return mCorrectAnswers + " of " + mNumberOfQuestions;
    }
}
