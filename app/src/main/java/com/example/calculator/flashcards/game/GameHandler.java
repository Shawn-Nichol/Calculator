package com.example.calculator.flashcards.game;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;
import androidx.databinding.library.baseAdapters.BR;
import androidx.navigation.Navigation;


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
        String answer = mViewModel.getUserAnswer();
        Log.d(TAG, "getNumber: " + answer);
        return answer;
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

    /**
     * Get the formula for the current question.
     */
    @Bindable
    public String getQuestion() {
        return mViewModel.getFormula().get(mViewModel.getCurrentQuestion());
    }

    @Bindable
    public String getCurrentQuestion() {
        int question = mViewModel.getCurrentQuestion();
        Log.d(TAG, "getCurrentQuestion: " + question);
        return String.valueOf(question);
    }

    /**
     * Compare the user answer to the correct answer, and display the next questions. If there are no
     * more questions move onto the results fragment.
     * @param view
     */
    public void Enter(View view) {
        int correctAnswer = mViewModel.getAnswer().get(mViewModel.getCurrentQuestion());
        int userAnswer = Integer.valueOf(mViewModel.getUserAnswer());


        // If User doesn't enter a value, the will be notified.
        if(mViewModel.getUserAnswer() == "") {
            Toast.makeText(mContext, "Please enter a number", Toast.LENGTH_SHORT).show();
            return;
        }

        // If user enters the correct number the correct counter will go up by one.
        if(userAnswer == correctAnswer) {
            mViewModel.setQuestionCorrect(1);
            Log.d(TAG, "Enter: Correct, " );
        }

        Log.d(TAG, "Enter: number of correct answers " + mViewModel.getCorrectQuestions());

        // After all questions have been answer go to the next fragment
        if(mViewModel.getNumberOfQuestions() -1 > mViewModel.getCurrentQuestion()) {
            Log.d(TAG, "Enter: next question");
            mViewModel.setCurrentQuestion(1);
            mViewModel.setUserAnswer("");
            notifyPropertyChanged(BR.number);
            notifyPropertyChanged(BR.question);
            notifyPropertyChanged(BR.currentQuestion);
        } else {
            endOfGame(view);
        }
    }

    private void endOfGame(View view) {
//        Log.d(TAG, "Enter: no more questions");
//        Navigation.findNavController(view).navigate(R.id.action_flashCardGameFragment_to_flashCardResultsFragment);
        FlashCardGameFragmentDirections.ActionFlashCardGameFragmentToFlashCardResultsFragment action =
                FlashCardGameFragmentDirections.actionFlashCardGameFragmentToFlashCardResultsFragment();

        action.setCorrecQuestions(mViewModel.getCorrectQuestions());
        action.setNumberOfQuestions(mViewModel.getNumberOfQuestions());

        Navigation.findNavController(view).navigate(action);
    }
}
