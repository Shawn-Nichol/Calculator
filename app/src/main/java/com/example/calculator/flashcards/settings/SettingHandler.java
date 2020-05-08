package com.example.calculator.flashcards.settings;

import android.content.Context;
import android.content.SharedPreferences;

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
import com.example.calculator.databinding.FragmentFlashCardsSettingBinding;
import com.example.calculator.flashcards.game.FlashCardGameFragmentArgs;
import com.example.calculator.flashcards.game.FlashCardGameFragmentDirections;

public class SettingHandler extends BaseObservable implements NumberPickerFragment.NumberPickerListener {

    private static final String TAG = "Calculator Handler";

    Context mContext;
    FragmentManager fragmentManager;
    FlashCardsSettingViewModel mViewModel;
    FragmentFlashCardsSettingBinding mBinding;

    SharedPreferences sharedPref;

    // Keys
    public static final String KEY_PREFERENCE_FILE = "com.example.calculator.flashcards.settings_929";
    public static final String KEY_ADDITION = "addition";
    public static final String KEY_MINUS = "minus";
    public static final String KEY_MULTIPLICATION = "multiplication";
    public static final String KEY_DIVISION = "division";


    // Views
    private SwitchCompat switchAddition, switchMinus, switchMultiplication, switchDivision;

    public SettingHandler() {
    }

    public SettingHandler(Context mContext, FlashCardsSettingViewModel viewModel, FragmentFlashCardsSettingBinding mBinding) {
        this.mContext = mContext;
        this.mViewModel = viewModel;
        this.mBinding = mBinding;
        bindViews();
        loadSwitchState();
    }

    private void bindViews() {
        Log.d(TAG, "bindViews: ");
        switchAddition = mBinding.switchAddition;
        switchMinus = mBinding.switchMinus;
        switchMultiplication = mBinding.switchMultiple;
        switchDivision = mBinding.switchDivide;

    }

    public void loadSwitchState() {
        sharedPref = mContext.getSharedPreferences(KEY_PREFERENCE_FILE, Context.MODE_PRIVATE);

        boolean addition = sharedPref.getBoolean(KEY_ADDITION, false);
        boolean minus = sharedPref.getBoolean(KEY_MINUS, false);
        boolean multiple = sharedPref.getBoolean(KEY_MULTIPLICATION, false);
        boolean divide = sharedPref.getBoolean(KEY_DIVISION, false);

        switchAddition.setChecked(sharedPref.getBoolean(KEY_ADDITION, addition));
        switchMinus.setChecked(sharedPref.getBoolean(KEY_MINUS, minus));
        switchMultiplication.setChecked(sharedPref.getBoolean(KEY_MULTIPLICATION, multiple));
        switchDivision.setChecked(sharedPref.getBoolean(KEY_DIVISION, divide));

        mViewModel.setAddition(addition);
        mViewModel.setMinus(minus);
        mViewModel.setMultiplication(multiple);
        mViewModel.setDivision(divide);

        Log.d(TAG, "loadSwitchState: " +
                "\n addition " + addition +
                "\n minus " + minus +
                "\n multiple " + multiple +
                "\n divide " + divide);

    }

    public void saveSwitchState() {

        boolean addition = switchAddition.isChecked();
        boolean minus = switchMinus.isChecked();
        boolean multiple = switchMultiplication.isChecked();
        boolean divide = switchDivision.isChecked();

        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putBoolean(KEY_ADDITION, addition);
        editor.putBoolean(KEY_MINUS, minus);
        editor.putBoolean(KEY_MULTIPLICATION, multiple);
        editor.putBoolean(KEY_DIVISION, divide);
        editor.apply();

        Log.d(TAG, "loadSwitchState: " +
                "\n addition " + addition +
                "\n minus " + minus +
                "\n multiple " + multiple +
                "\n divide " + divide);
    }



    public void startGame(View view) {
        Log.d(TAG, "startGame: number of question " + mViewModel.getNumberOfQuestions());


        boolean addition = mViewModel.getAddition();

        FlashCardsSettingFragmentDirections.ActionFlashCardsSettingFragmentToFlashCardGameFragment action =
                FlashCardsSettingFragmentDirections.actionFlashCardsSettingFragmentToFlashCardGameFragment();

        action.setAddition(mViewModel.getAddition());
        action.setMinus(mViewModel.getMinus());
        action.setMultiple(mViewModel.getMultiplication());
        action.setDivide(mViewModel.getDivision());

        Navigation.findNavController(view).navigate(action);

    }



    public void setAdditionOn(View view) {

        switchAddition.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked) {
                    Log.d(TAG, "Addition ON");
                    mViewModel.setAddition(true);
                } else {
                    Log.d(TAG, "Addition OFF");
                    mViewModel.setAddition(false);
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

    public void numberPicker(View view) {
        Log.d(TAG, "numberPicker: ");
        DialogFragment fragment = new NumberPickerFragment(mViewModel, view.getContext());

        fragment.show(fragmentManager, "number picker");
        notifyPropertyChanged(BR.questions);
    }



    @Bindable
    public String getQuestions() {
        return "Number Of Questions " + mViewModel.getNumberOfQuestions();

    }


    public void setQuestions(View view) {
        Log.d(TAG, "setQuestions: " + mViewModel.getNumberOfQuestions());
        notifyPropertyChanged(BR.questions);
    }

    @Override
    public void selectedNumber(int number) {
        Log.d(TAG, "selectedNumber: " + number);
    }
}
