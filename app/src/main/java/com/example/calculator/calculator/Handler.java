package com.example.calculator.calculator;

import android.content.Context;
import android.util.Log;
import android.view.View;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;

import com.example.calculator.BR;

public class Handler extends BaseObservable {

    private static final String TAG = "CalculatorHandler";

    CalculatorViewModel viewModel;
    private String mNumber;
    private String formula;
    private double mAnswer;

    Context context;

    public Handler(Context context, CalculatorViewModel viewModel) {
        this.context = context;
        this.viewModel = viewModel;
        mNumber = viewModel.getNumber();
        mAnswer = viewModel.getAnswer();
        formula = viewModel.getFormula();
    }

    @Bindable
    public String getNumber() {
        return viewModel.getNumber();
    }

    public void setNumber(View view, String number) {
        if(number.equals("clear")) {
            viewModel.setNumber("");
        } else {
            viewModel.setNumber(viewModel.getNumber() +number);
        }
        Log.d(TAG, "btnNumber: mNumber " + viewModel.getNumber());

        notifyPropertyChanged(BR.number);
    }

    public void setSymbol(View view, String symbol) {
        viewModel.setNumber(viewModel.getNumber() + symbol);
        Log.d(TAG, "btnSymbol: " + viewModel.getNumber());

        notifyPropertyChanged(BR.number);
    }


    public double getEquals() {
        return viewModel.getAnswer();
    }

    public void setEquals(View view) {

        for(int i = 0; i < viewModel.getNumber().length(); i++) {
            String number = String.valueOf(viewModel.getNumber().charAt(i));
            if(number.equals("+"))  {
                double numOne = Double.parseDouble(viewModel.getNumber().substring(0, i));
                double numTwo = Double.parseDouble(viewModel.getNumber().substring(i+1));
                Log.d(TAG, "btnEquals: " + numOne + " + " + numTwo);

                viewModel.setAnswer(numOne + numTwo);

            }
        }

        setFormula(viewModel.getNumber());
        viewModel.setNumber(String.valueOf(viewModel.getAnswer()));
        Log.d(TAG, "btnEquals: " + viewModel.getAnswer());
        notifyPropertyChanged(BR.number);
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
