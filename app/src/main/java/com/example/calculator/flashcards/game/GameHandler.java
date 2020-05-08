package com.example.calculator.flashcards.game;

import android.content.Context;
import android.util.Log;
import android.view.View;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;
import androidx.databinding.library.baseAdapters.BR;
import androidx.navigation.Navigation;

import com.example.calculator.R;


public class GameHandler extends BaseObservable {

    private static final String TAG = "Calculator FlashCardHandlers";

    Context mContext;
    FlashCardsGameViewModel mViewModel;

    /**
     * Constructor.
     * @param mContext passed from FlashCardGameFragment.
     * @param viewModel passed from FlashCardGameFragment.
     */
    public GameHandler(Context mContext, FlashCardsGameViewModel viewModel) {
        this.mContext = mContext;
        this.mViewModel = viewModel;
    }

    /**
     * Sets the number entered by the user.
     */
    public void setNumber(View view, String number) {
        Log.d(TAG, "setNumber: " + number);
        mViewModel.setUserAnswer(mViewModel.getUserAnswer() + number);

        notifyPropertyChanged(BR.number);
    }

    /**
     * Gets the number entered by the user and binds it the view on the screen.
     * @return
     */
    @Bindable
    public String getNumber() {
        Log.d(TAG, "getNumber: " + mViewModel.getUserAnswer());
        return mViewModel.getUserAnswer();
    }

    /**
     * Deletes the last number entered by the user.
     */
    public void deleteLastNumber() {
        String number = mViewModel.getUserAnswer();
        int stringLength = number.length();

        if(stringLength != 0) {
            mViewModel.setUserAnswer(number.substring(0, stringLength-1));
        }
        notifyPropertyChanged(BR.number);
    }


    @Bindable
    public String getQuestion() {
        return mViewModel.getFormula().get(mViewModel.getCurrentQuestion());
    }

    public void Enter(View view) {


        if(mViewModel.getNumberOfQuestions() -1 > mViewModel.getCurrentQuestion()) {
            Log.d(TAG, "Enter: next question");
            mViewModel.setCurrentQuestion(1);
            notifyPropertyChanged(BR.question);
        } else {
            Log.d(TAG, "Enter: no more questions");
            Navigation.findNavController(view).navigate(R.id.action_flashCardGameFragment_to_flashCardResultsFragment);


        }
    }


}
