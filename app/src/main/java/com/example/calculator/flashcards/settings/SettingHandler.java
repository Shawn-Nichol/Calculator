package com.example.calculator.flashcards.settings;

import android.content.Context;
import android.util.Log;
import android.view.View;

import androidx.databinding.BaseObservable;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;

import com.example.calculator.R;

public class SettingHandler extends BaseObservable {

    private static final String TAG = "Calculator Handler";

    Context mContext;

    public SettingHandler(Context mContext) {
        this.mContext = mContext;
    }

    public void startGame(View view) {
        Log.d(TAG, "startGame: ");
        Navigation.findNavController(view).navigate(R.id.action_flashCardsSettingFragment_to_flashCardGameFragment);
    }
}
