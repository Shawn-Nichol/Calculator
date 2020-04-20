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

    String mFormula;
    double answer;

    private static final String TAG = "CalculatorWorkerThread";

    public CalculatorWorkerThread(@NonNull Context context, @NonNull WorkerParameters workerParams) {
        super(context, workerParams);
    }

    @NonNull
    @Override
    public Result doWork() {
        Log.d(TAG, "doWork: ");

        mFormula = getInputData().getString(WORKER_NUMBER);
        answer = NaN;


        for(int i = 0; i < mFormula.length(); i++) {
            String number = String.valueOf(mFormula.charAt(i));
            switch(number) {
                case "+":
                    solve(i, "plus");
                    break;
                case "-":
                    solve(i, "minus");
                    break;
                case "*":
                    solve(i, "multiple");
                    break;
                case "/":
                    solve(i, "divide");
                    break;
            }
        }

        Data output = new Data.Builder()
                .putDouble(WORKER_ANSWER, answer)
                .build();

        return Result.success(output);
    }


    public void solve(int position, String arithmetic) {
        double numOne = Double.parseDouble(mFormula.substring(0, position));
        double numTwo = Double.parseDouble(mFormula.substring(position+1));

        switch(arithmetic) {
            case "plus":
                answer = numOne + numTwo;
                break;
            case "minus":
                answer = numOne - numTwo;
                break;
            case "multiple":
                answer = numOne * numTwo;
                break;
            case "divide":
                answer = numOne / numTwo;
                break;

        }
        Log.d(TAG, "btnEquals: " + numOne + " + " + numTwo + " = " + answer);

    }



}
