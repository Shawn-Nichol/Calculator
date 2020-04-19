package com.example.calculator.calculator;

import android.content.Context;
import android.util.Log;
import android.view.View;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;
import androidx.lifecycle.ViewModel;

import com.example.calculator.BR;

public class Handler extends BaseObservable {

    private static final String TAG = "CalculatorHandler";

    CalculatorViewModel viewModel;
    private String mNumber;
    private String formula;
    private double mAnswer;

    Context context;

    public Handler(Context context, CalculatorViewModel viewModel) {
        this.context = context;
        this.viewModel = viewModel;
        mNumber = "";
    }

    @Bindable
    public String getNumber() {
        return mNumber;
    }

    public void setNumber(View view, String number) {
        if(number.equals("clear")) {
            mNumber = "";
        } else {
            mNumber += number;
        }
        Log.d(TAG, "btnNumber: mNumber " + mNumber);
        notifyPropertyChanged(BR.number);
    }

    public void setSymbol(View view, String symbol) {
        mNumber += symbol;
        Log.d(TAG, "btnSymbol: " + mNumber);
        notifyPropertyChanged(BR.number);
    }


    public double getEquals() {
        return mAnswer;
    }

    public void setEquals(View view) {

        for(int i = 0; i < mNumber.length(); i++) {
            String number = String.valueOf(mNumber.charAt(i));
            if(number.equals("+"))  {
                double numOne = Double.parseDouble(mNumber.substring(0, i));
                double numTwo = Double.parseDouble(mNumber.substring(i+1));
                Log.d(TAG, "btnEquals: " + numOne + " + " + numTwo);
                mAnswer = numOne + numTwo;

            }
        }

        setFormula(mNumber);
        mNumber = String.valueOf(mAnswer);
        Log.d(TAG, "btnEquals: " + mAnswer);
        notifyPropertyChanged(BR.number);
    }

    @Bindable
    public String getFormula() {
        return formula;
    }

    public void setFormula(String formula) {
        this.formula = formula;
        notifyPropertyChanged(BR.formula);
    }
}
