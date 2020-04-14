package com.example.calculator.Math;

import android.content.Context;
import android.os.SystemClock;
import android.util.Log;

import androidx.annotation.NonNull;

import androidx.work.Data;
import androidx.work.Worker;
import androidx.work.WorkerParameters;

public class MathWorker extends Worker {

    private static final String TAG = "Calculate MathWorker";

    // Pass data with key value pairs
    public static final String KEY_NUMBER_ONE = "numberOne";
    public static final String KEY_NUMBER_TWO = "numberTwo";
    public static final String KEY_SYMBOL = "symbol";


    // Result Key
    public static final String KEY_RESULT = "test";


    public MathWorker(@NonNull Context context, @NonNull WorkerParameters workerParams) {
        super(context, workerParams);
    }

    @NonNull
    @Override
    public Result doWork() {
        Double numberOne = getInputData().getDouble(KEY_NUMBER_ONE, 0);
        Double numberTwo = getInputData().getDouble(KEY_NUMBER_TWO, 0);
        String symbol = getInputData().getString(KEY_SYMBOL);

        Log.d(TAG, "doWork: numberOne " + numberOne + " " + symbol + " " + numberTwo);

        Double result = SelectFormula.formula(symbol, numberOne, numberTwo);

        Data output = new Data.Builder()
                .putDouble(KEY_RESULT, result)
                .build();
        return Result.success(output);
    }
}
