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
    // Constructor.
    public CalculatorViewModel() {
        Log.d(TAG, "CalculatorViewModel: ");
        mNumber = "";
    }




}
