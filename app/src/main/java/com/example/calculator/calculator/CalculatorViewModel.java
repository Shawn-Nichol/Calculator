package com.example.calculator.calculator;

import android.util.Log;

import androidx.lifecycle.ViewModel;

import static com.example.calculator.Util.Constants.SET_NUMBER_ONE;
import static com.example.calculator.Util.Constants.SET_SECOND_NUMBER;

public class CalculatorViewModel extends ViewModel {

    private static final String TAG = "CalculatorViewModel";

    private int mFormulaState;

    private String mNumberOne, mNumberTwo, mSavedSymbol;
    private Double mSolution;

    private String mSavedFormula;
    private int mViewModelState;

    // Constructor.
    public CalculatorViewModel() {
        Log.d(TAG, "CalculatorViewModel: ");
        mFormulaState = 1;

    }

    public String getNumberOne() {
        return mNumberOne;
    }

    public void setNumberOne(String number) {
        mNumberOne = number;

    }

    public String getNumberTwo() {
        return mNumberTwo;
    }

    public void setNumberTwo(String number) {
        mNumberTwo = number;
    }

    public String getSavedSymbol() {
        return mSavedSymbol;
    }

    public void setSavedSymbol(String symbol) {
        mSavedSymbol = symbol;
    }

    public Double getSolution() {
        return mSolution;
    }

    /**
     * Results for the equations, sets mNumberOne to the result if the user wants to use the result.
     * Set the second number to null so the user can add a second number.
     * @param equals the results of the combined doubles.
     */
    public void setSolution(Double equals) {
        Log.d(TAG, "setSolution: " + equals);
        this.mSolution = equals;
        mNumberOne = String.valueOf(mSolution);
        mFormulaState = SET_SECOND_NUMBER;
        Log.d(TAG, "setSolution: numberOne " + mNumberOne + " numberTwo " + mNumberTwo);
    }


    public String getSavedFormula() {
        return mSavedFormula;
    }

    public void setSavedFormula(String mDisplayFormula) {
        this.mSavedFormula = mDisplayFormula;
    }


    public int getFormulaState() {
        return mFormulaState;
    }

    public void setFormulaState(int mFormulaState) {
        this.mFormulaState = mFormulaState;
    }

    public int getViewModelState() {
        return mViewModelState;
    }

    public void setViewModelState(int mViewModelState) {
        this.mViewModelState = mViewModelState;
    }

    public void setClear() {
        mNumberOne = null;
        mNumberTwo = null;
        mSavedSymbol = null;
        mSavedFormula = null;
        mSolution = null;
        mFormulaState = SET_NUMBER_ONE;
    }
}
