package com.example.calculator.calculator;

import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.work.Data;
import androidx.work.Worker;
import androidx.work.WorkerParameters;

import static java.lang.Double.NaN;

/**
 * CalculatorWorkerThread solves the formula entered by the user.
 */
public class CalculatorWorkerThread extends Worker {

    public static final String WORKER_NUMBER = "number";

    // Result KEY
    public static final String WORKER_ANSWER = "answer";

    private static final String TAG = "CalculatorWorkerThread";

    public CalculatorWorkerThread(@NonNull Context context, @NonNull WorkerParameters workerParams) {
        super(context, workerParams);
    }

    @NonNull
    @Override
    public Result doWork() {
        Log.d(TAG, "doWork: ");

        String formula = getInputData().getString(WORKER_NUMBER);
        double answer = NaN;


        for(int i = 0; i < formula.length(); i++) {
            String number = String.valueOf(formula.charAt(i));
            if(number.equals("+"))  {
                double numOne = Double.parseDouble(formula.substring(0, i));
                double numTwo = Double.parseDouble(formula.substring(i+1));
                Log.d(TAG, "btnEquals: " + numOne + " + " + numTwo);

                answer = numOne + numTwo;
            }
        }

        Data output = new Data.Builder()
                .putDouble(WORKER_ANSWER, answer)
                .build();

        return Result.success(output);
    }
}
