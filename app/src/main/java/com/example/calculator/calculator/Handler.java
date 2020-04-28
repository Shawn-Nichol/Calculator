package com.example.calculator.calculator;

import android.content.Context;
import android.util.Log;
import android.view.View;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;
import androidx.lifecycle.LifecycleOwner;
import androidx.work.Data;
import androidx.work.OneTimeWorkRequest;
import androidx.work.WorkInfo;
import androidx.work.WorkManager;

import com.example.calculator.BR;

import static com.example.calculator.calculator.CalculatorWorkerThread.WORKER_ANSWER;
import static com.example.calculator.calculator.CalculatorWorkerThread.WORKER_NUMBER;
import static java.lang.Double.NaN;

/**
 * Handles button clicks
 */
public class Handler extends BaseObservable {

    private static final String TAG = "CalculatorHandler";

    CalculatorViewModel viewModel;

    Context context;

    public Handler(Context context, CalculatorViewModel viewModel) {
        this.context = context;
        this.viewModel = viewModel;

    }

    @Bindable
    public String getNumber() {
        return viewModel.getNumber();
    }

    /**
     * The number entered by the user.
     *
     * @param view   is required for DataBinding onClick to work.
     * @param number the number entered by the user.
     */
    public void setNumber(View view, String number) {
        if (number.equals("clear")) {
            viewModel.setNumber("");
            viewModel.setAnswer(NaN);
            viewModel.setFormula("");

        } else {
            viewModel.setNumber(viewModel.getNumber() + number);
        }
        Log.d(TAG, "btnNumber: mNumber " + viewModel.getNumber());

        notifyPropertyChanged(BR.number);
    }

    /**
     * SetSymbol, adds the selected symbol from the user to the String number. .
     *
     * @param view   is required for DataBinding onclick to work.
     * @param symbol the arthemtic symbol selected by the user.
     */
    public void setSymbol(View view, String symbol) {
        viewModel.setNumber(viewModel.getNumber() + symbol);
        Log.d(TAG, "btnSymbol: " + viewModel.getNumber());

        notifyPropertyChanged(BR.number);
    }

    public double getEquals() {
        return viewModel.getAnswer();
    }

    /**
     * Takes users entered information and passes it a Worker thread to be solved.
     * @param view
     */
    public void setEquals(View view) {

        // Persistable set of key/value pairs which are used as the inputs of workers.
        Data myData = new Data.Builder()
                .putString(WORKER_NUMBER, viewModel.getNumber())
                .build();

        // Create a single Work request to be completed.
        OneTimeWorkRequest worker = new OneTimeWorkRequest.Builder(CalculatorWorkerThread.class)
                .setInputData(myData)
                .build();

        // Hand off the task to the system
        WorkManager.getInstance(context).enqueue(worker);

        // Get Results after work is finished.
        WorkManager.getInstance(context).getWorkInfoByIdLiveData(worker.getId())
                .observe((LifecycleOwner) context, workInfo -> {
                    if (workInfo.getState() == WorkInfo.State.SUCCEEDED) {
                        double result = workInfo.getOutputData().getDouble(WORKER_ANSWER, NaN);

                        setFormula(viewModel.getNumber());
                        viewModel.setAnswer(result);
                        viewModel.setNumber(String.valueOf(result));

                        Log.d(TAG, "btnEquals: " + result);
                        notifyPropertyChanged(BR.number);

                    }
                });
    }

    @Bindable
    public String getFormula() {
        return viewModel.getFormula();
    }

    public void setFormula(String formula) {
        viewModel.setFormula(formula);
        notifyPropertyChanged(BR.formula);
    }



}
