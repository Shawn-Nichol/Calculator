package com.example.calculator.flashcards.settings;

import android.content.Context;
import android.content.SharedPreferences;

import android.util.Log;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.Toast;

import androidx.appcompat.widget.SwitchCompat;
import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.FragmentManager;
import androidx.navigation.Navigation;


import com.example.calculator.BR;
import com.example.calculator.databinding.FragmentFlashCardsSettingBinding;

import org.jetbrains.annotations.NotNull;

public class SettingHandler extends BaseObservable {

    private static final String TAG = "Calculator SettingHandler";

    Context mContext;
    FragmentManager fragmentManager;
    FlashCardsSettingViewModel mViewModel;
    FragmentFlashCardsSettingBinding mBinding;
    SharedPreferences sharedPref;


    // Keys
    private static final String KEY_PREFERENCE_FILE = "com.example.calculator.flashcards.settings_929";
    private static final String KEY_NUMBER_OF_QUESTIONS = "numberOfQuestions";
    private static final String KEY_ADDITION = "addition";
    private static final String KEY_MINUS = "minus";
    private static final String KEY_MULTIPLICATION = "multiplication";
    private static final String KEY_DIVISION = "division";


    // Views
    private SwitchCompat switchAddition, switchMinus, switchMultiplication, switchDivision;


    public SettingHandler() {
    }

    /**
     * Constructor
     */
    public SettingHandler(Context mContext, FlashCardsSettingViewModel viewModel, FragmentFlashCardsSettingBinding mBinding,
                          FragmentManager fm) {
        this.mContext = mContext;
        this.mViewModel = viewModel;
        this.mBinding = mBinding;
        this.fragmentManager = fm;
        bindViews();
        loadSwitchState();
    }

    /**
     * Binds views for easy access.
     */
    private void bindViews() {
        Log.d(TAG, "bindViews: ");
        switchAddition = mBinding.switchAddition;
        switchMinus = mBinding.switchMinus;
        switchMultiplication = mBinding.switchMultiplication;
        switchDivision = mBinding.switchDivide;

    }

    /**
     * loads the state of the arithmetic switches.
     */
    public void loadSwitchState() {
        sharedPref = mContext.getSharedPreferences(KEY_PREFERENCE_FILE, Context.MODE_PRIVATE);

        int numberOfQuestions = sharedPref.getInt(KEY_NUMBER_OF_QUESTIONS, 5);
        boolean addition = sharedPref.getBoolean(KEY_ADDITION, false);
        boolean minus = sharedPref.getBoolean(KEY_MINUS, false);
        boolean multiple = sharedPref.getBoolean(KEY_MULTIPLICATION, false);
        boolean divide = sharedPref.getBoolean(KEY_DIVISION, false);

        notifyPropertyChanged(BR.numberOfQuestions);

        switchAddition.setChecked(sharedPref.getBoolean(KEY_ADDITION, addition));
        switchMinus.setChecked(sharedPref.getBoolean(KEY_MINUS, minus));
        switchMultiplication.setChecked(sharedPref.getBoolean(KEY_MULTIPLICATION, multiple));
        switchDivision.setChecked(sharedPref.getBoolean(KEY_DIVISION, divide));

        // With out the following method calls the switches won't react to the first touch.
        View view = null;
        setAdditionState(view);
        setMinusState(view);
        setMultiplicationState(view);
        setDivisionState(view);

        mViewModel.setAddition(addition);
        mViewModel.setMinus(minus);
        mViewModel.setMultiplication(multiple);
        mViewModel.setDivision(divide);

        Log.d(TAG, "loadSwitchState: " +
                "\n number of questions " + numberOfQuestions +
                "\n addition " + addition +
                "\n minus " + minus +
                "\n multiple " + multiple +
                "\n divide " + divide);

    }

    public void saveSwitchState() {

        int numberOfQuestions = 0;
        boolean addition = switchAddition.isChecked();
        boolean minus = switchMinus.isChecked();
        boolean multiple = switchMultiplication.isChecked();
        boolean divide = switchDivision.isChecked();

        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putInt(KEY_NUMBER_OF_QUESTIONS, numberOfQuestions);
        editor.putBoolean(KEY_ADDITION, addition);
        editor.putBoolean(KEY_MINUS, minus);
        editor.putBoolean(KEY_MULTIPLICATION, multiple);
        editor.putBoolean(KEY_DIVISION, divide);
        editor.apply();

        Log.d(TAG, "loadSwitchState: " +
                "\n number of questions " + numberOfQuestions +
                "\n addition " + addition +
                "\n minus " + minus +
                "\n multiple " + multiple +
                "\n divide " + divide);
    }

    public void startGame(View view) {
        Log.d(TAG, "startGame: number of question " + mViewModel.getNumberOfQuestions());

        if(!mViewModel.getAddition() && !mViewModel.getMinus() && !mViewModel.getMultiplication() && !mViewModel.getDivision()) {
            Toast.makeText(mContext, "Please select one form of arithmetic.", Toast.LENGTH_LONG).show();
            return;
        }


        FlashCardsSettingFragmentDirections.ActionFlashCardsSettingFragmentToFlashCardGameFragment action =
                FlashCardsSettingFragmentDirections.actionFlashCardsSettingFragmentToFlashCardGameFragment();

        action.setNumberOfQuestions(mViewModel.getNumberOfQuestions());
        action.setAddition(mViewModel.getAddition());
        action.setMinus(mViewModel.getMinus());
        action.setMultiple(mViewModel.getMultiplication());
        action.setDivide(mViewModel.getDivision());

        Navigation.findNavController(view).navigate(action);

    }

    public void setAdditionState(View view) {
        switchAddition.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if(isChecked) {
                Log.d(TAG, "Addition ON");
                mViewModel.setAddition(true);
            } else {
                Log.d(TAG, "Addition OFF");
                mViewModel.setAddition(false);
            }
        });
    }

    public void setMinusState(@NotNull View view) {
        switchMinus.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if(isChecked) {
                Log.d(TAG, "Addition ON");

            } else {
                Log.d(TAG, "Addition OFF");
            }
        });
    }

    public void setMultiplicationState(@NotNull View view) {
        switchMultiplication.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if(isChecked) {
                Log.d(TAG, "Addition ON");
            } else {
                Log.d(TAG, "Addition OFF");
            }
        });
    }

    public void setDivisionState(@NotNull View view) {

        switchDivision.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if(isChecked) {
                Log.d(TAG, "Addition ON");
            } else {
                Log.d(TAG, "Addition OFF");
            }
        });
    }

    @Bindable
    public String getNumberOfQuestions() {
        return "Number Of Questions " + mViewModel.getNumberOfQuestions();

    }


    public void setNumberOfQuestions(View view) {
        notifyPropertyChanged(BR.numberOfQuestions);
        DialogFragment fragment = new NumberOfQuestionsDialog(mViewModel, mBinding);
        fragment.show(fragmentManager, "number picker");
    }

}
