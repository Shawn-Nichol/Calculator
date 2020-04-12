package com.example.calculator.Math;

import android.util.Log;

public class Addition {

    private static final String TAG = "Calculate Addition";




    public static Double formula(double numberOne, double numberTwo) {
        double answer = numberOne + numberTwo;
        Log.d(TAG, "formula: " + numberOne + " + " +  numberTwo + " = " + answer);

        return answer;
    }
}
