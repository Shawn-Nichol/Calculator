package com.example.calculator.calculator;

import android.content.Context;
import android.util.Log;
import android.view.View;

public class CalculatorHandler {

    private static final String TAG = "Calculator Handler";

    Context context;

    public CalculatorHandler(Context context) {
        this.context = context;
    }


    public void btnNumber(View view, String number) {
        Log.d(TAG, "btnNumber: " + number);
    }

    public void btnPlus() {
        Log.d(TAG, "btnPlus: ");
    }

    public void btnMinus() {
        Log.d(TAG, "btnMinus: ");
    }

    public void btnMultiple() {
        Log.d(TAG, "btnMultiple: ");

    }

    public void btnDivide(View) {
        Log.d(TAG, "btnDivide: ");
    }

    public void btnEquals(View view) {
        Log.d(TAG, "btnEquals: ");
    }

    public void btnClear(View view) {
        Log.d(TAG, "btnClear: ");
    }

}
