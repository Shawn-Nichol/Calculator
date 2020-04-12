package com.example.calculator;

import android.util.Log;

import androidx.lifecycle.ViewModel;

import static com.example.calculator.Constants.NO_VALUE;

public class CalculatorViewModel extends ViewModel {

    private static final String TAG = "CalculatorViewModel";

    private String mNumberOne;
    private String mNumberTwo;
    private String mSavedSymbol;
    private double mEquals;

    private String mDisplayFormula;
    private String mDisplaySolution;

    // Constructor.
    public CalculatorViewModel() {
        Log.d(TAG, "CalculatorViewModel: ");
        mNumberOne = NO_VALUE;
        mNumberTwo = NO_VALUE;
        mSavedSymbol = NO_VALUE;
        mEquals = 0;
    }

    public String getNumberOne() {
        return mNumberOne;
    }

    public void setNumberOne(String number) {
        Log.d(TAG, "setNumberOne: " + number);
        if(number.equals(NO_VALUE)) {
            mNumberOne = NO_VALUE;
        } else if((mNumberOne == NO_VALUE)) {
            mNumberOne = number;
        } else {
            mNumberOne += number;
        }

    }

    public String getNumberTwo() {
        return mNumberTwo;
    }

    public void setNumberTwo(String number) {
        Log.d(TAG, "setNumberTwo: " + number);
        if(number.equals(NO_VALUE)) {
            mNumberOne = NO_VALUE;
        }
        if (mNumberTwo == NO_VALUE) {
            mNumberTwo = number;
        } else {
            mNumberTwo += number;
        }
    }

    public String getSavedSymbol() {
        return mSavedSymbol;
    }

    public void setSavedSymbol(String symbol) {
        mSavedSymbol = symbol;
    }

    public double getEquals() {
        return mEquals;
    }

    /**
     * Results for the equations, sets mNumberOne to the result if the user wants to use the result.
     * Set the second number to null so the user can add a second number.
     * @param equals the results of the combined doubles.
     */
    public void setEquals(double equals) {
        Log.d(TAG, "setEquals: " + equals);
        this.mEquals = equals;
        mNumberOne = String.valueOf(mEquals);
        mNumberTwo = NO_VALUE;
        Log.d(TAG, "setEquals: numberOne " + mNumberOne + " numberTwo " + mNumberTwo);
    }


    public String getDisplayFormula() {
        return mDisplayFormula;
    }

    public void setDisplayFormula(String mDisplayFormula) {
        this.mDisplayFormula = mDisplayFormula;
    }

    public String getDisplaySolution() {
        return mDisplaySolution;
    }

    public void setDisplaySolution(String mDisplaySolution) {
        this.mDisplaySolution = mDisplaySolution;
    }
}
