package com.example.calculator.calculator;

import android.util.Log;
import android.view.View;

import androidx.databinding.Bindable;
import androidx.lifecycle.ViewModel;

import com.example.calculator.BR;

public class CalculatorViewModel extends ViewModel {

    private static final String TAG = "CalculatorViewModel";

    private  String mNumber;
    private double mAnswer;
    private String mFormula;
    // Constructor.
    public CalculatorViewModel() {
        Log.d(TAG, "CalculatorViewModel: ");
        mNumber = "";
    }

    public String getNumber() {
        return mNumber;
    }

    public void setNumber(String mNumber) {
        this.mNumber = mNumber;
    }

    public double getAnswer() {
        return mAnswer;
    }

    public void setAnswer(double mAnswer) {
        this.mAnswer = mAnswer;
    }

    public String getFormula() {
        return mFormula;
    }

    public void setFormula(String formula) {
        this.mFormula = formula;
    }
}
