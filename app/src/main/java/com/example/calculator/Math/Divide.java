package com.example.calculator.Math;

import android.util.Log;

public class Divide {

    private static final String TAG = "Calculate Divide";

    public static Double formula(Double numberOne, Double numberTwo) {
        double answer;

        if(numberOne == 0 || numberTwo == 0) {
            answer = 0;
        } else {
            answer = numberOne / numberTwo;
        }

        Log.d(TAG, "formula: " + numberOne + " % " + numberTwo + " = " + answer);

        return answer;
    }
}
