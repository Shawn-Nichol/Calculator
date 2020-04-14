package com.example.calculator.Math;

import android.util.Log;

public class Multiple {

    private static final String TAG = "Calculate Multiple";

    public static double formula(Double numberOne, Double numberTwo) {
        double answer;
        if(numberOne == 0 || numberTwo == 0) {
            answer = 0;
        } else {
            answer = numberOne * numberTwo;
        }

        Log.d(TAG, "formula: " + numberOne + " x " + numberTwo + " = " + answer);

        return answer;
    }
}
