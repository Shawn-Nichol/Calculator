package com.example.calculator.flashcards.settings;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.widget.CompoundButton;

import androidx.appcompat.widget.SwitchCompat;
import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.FragmentManager;
import androidx.navigation.Navigation;


import com.example.calculator.BR;
import com.example.calculator.R;

public class SettingHandler extends BaseObservable implements NumberPickerFragment.NumberPickerListener {

    private static final String TAG = "Calculator Handler";

    Context mContext;
    FragmentManager fragmentManager;
    FlashCardsSettingViewModel mVviewModel;

    public SettingHandler(Context mContext, FragmentManager fragmentManager, FlashCardsSettingViewModel viewModel) {
        this.mContext = mContext;
        this.fragmentManager = fragmentManager;
        this.mVviewModel = viewModel;
    }

    public void startGame(View view) {
        Log.d(TAG, "startGame: number of question " + mVviewModel.getNumberOfQuestions());
        Navigation.findNavController(view).navigate(R.id.action_flashCardsSettingFragment_to_flashCardGameFragment);
    }



    public void setAdditionOn(View view) {
        SwitchCompat mySwitch = view.findViewById(R.id.switch_addition);
        mySwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked) {
                    Log.d(TAG, "Addition ON");
                } else {
                    Log.d(TAG, "Addition OFF");
                }
            }
        });
    }

    public void setMinus(View view) {
        SwitchCompat mySwitch = view.findViewById(R.id.switch_minus);
        mySwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked) {
                    Log.d(TAG, "Addition ON");
                } else {
                    Log.d(TAG, "Addition OFF");
                }
            }
        });
    }

    public void setMultiplication(View view) {
        SwitchCompat mySwitch = view.findViewById(R.id.switch_multiple);
        mySwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked) {
                    Log.d(TAG, "Addition ON");
                } else {
                    Log.d(TAG, "Addition OFF");
                }
            }
        });
    }

    public void setDivision(View view) {
        SwitchCompat mySwitch = view.findViewById(R.id.switch_divide);
        mySwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked) {
                    Log.d(TAG, "Addition ON");
                } else {
                    Log.d(TAG, "Addition OFF");
                }
            }
        });
    }

    public void setQuestions(View view) {
        Log.d(TAG, "setQuestions: ");
        DialogFragment fragment = new NumberPickerFragment(mVviewModel);
        fragment.show(fragmentManager, "number picker");

    }

    @Bindable
    public String getQuestions() {
        return "Number Of Questions " + mVviewModel.getNumberOfQuestions();

    }


    @Override
    public void onDialogPositiveClick(DialogFragment dialog) {
        Log.d(TAG, "onDialogPositiveClick: ");

    }
}
