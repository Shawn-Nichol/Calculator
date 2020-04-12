package com.example.calculator.Math;

import android.util.Log;

public class Minus {

    private static final String TAG = "Calculate Minus";

    public static double formula(double numberOne, double numberTwo) {
        double answer = numberOne - numberTwo;

        Log.d(TAG, "formula: " + numberOne + " - " + numberTwo + " = " + answer);

        return answer;
    }
}
