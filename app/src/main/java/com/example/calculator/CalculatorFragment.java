package com.example.calculator;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import static com.example.calculator.Constants.NO_VALUE;


public class CalculatorFragment extends Fragment implements View.OnClickListener{

    private static final String TAG = "CalculatorFragment";

    // UI
    Button btnZero, btnOne, btnTwo, btnThree, btnFour, btnFive, btnSix, btnSeven, btnEight, btnNine, btnDecimal;
    Button btnPlus, btnMinus, btnMultiple, btnDivide, btnEquals, btnClear;


    CalculatorViewModel viewModel;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Log.d(TAG, "onCreateView: ");

        initViewModel();


        return inflater.inflate(R.layout.fragment_calculator, container, false);

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Log.d(TAG, "onViewCreated: ");

        initButtons(view);

    }


    private void initViewModel(){
        // Creates viewModel class, only done once.
        viewModel = new ViewModelProvider(this).get(CalculatorViewModel.class);


    }

    private void initButtons(View v) {
        // Btn Numbers
        btnZero = v.findViewById(R.id.btn_zero);
        btnZero.setOnClickListener(this);
        btnOne = v.findViewById(R.id.btn_one);
        btnOne.setOnClickListener(this);
        btnTwo = v.findViewById(R.id.btn_two);
        btnTwo.setOnClickListener(this);
        btnThree = v.findViewById(R.id.btn_three);
        btnThree.setOnClickListener(this);
        btnFour = v.findViewById(R.id.btn_four);
        btnFour.setOnClickListener(this);
        btnFive = v.findViewById(R.id.btn_five);
        btnFive.setOnClickListener(this);
        btnSix = v.findViewById(R.id.btn_six);
        btnSix.setOnClickListener(this);
        btnSeven = v.findViewById(R.id.btn_seven);
        btnSeven.setOnClickListener(this);
        btnEight = v.findViewById(R.id.btn_eight);
        btnEight.setOnClickListener(this);
        btnNine = v.findViewById(R.id.btn_nine);
        btnNine.setOnClickListener(this);
        btnDecimal = v.findViewById(R.id.btn_decimal);
        btnDecimal.setOnClickListener(this);

        // Symbols
        btnPlus = v.findViewById(R.id.btn_plus);
        btnPlus.setOnClickListener(this);
        btnMinus = v.findViewById(R.id.btn_minus);
        btnMinus.setOnClickListener(this);
        btnMultiple = v.findViewById(R.id.btn_multiple);
        btnMultiple.setOnClickListener(this);
        btnDivide = v.findViewById(R.id.btn_divide);
        btnDivide.setOnClickListener(this);
        btnEquals = v.findViewById(R.id.btn_equals);
        btnEquals.setOnClickListener(this);
        btnClear = v.findViewById(R.id.btn_clear);
        btnClear.setOnClickListener(this);



    }


    @Override
    public void onClick(View v) {
        switch(v.getId()) {
            case R.id.btn_zero:
                btnNumbers("0");
                break;
            case R.id.btn_one:
                btnNumbers("1");
                break;
            case R.id.btn_two:
                btnNumbers("2");
                break;
            case R.id.btn_three:
                btnNumbers("3");
                break;
            case R.id.btn_four:
                btnNumbers("4");
                break;
            case R.id.btn_five:
                btnNumbers("5");
                break;
            case R.id.btn_six:
                btnNumbers("6");
                break;
            case R.id.btn_seven:
                btnNumbers("7");
                break;
            case R.id.btn_eight:
                btnNumbers("8");
                break;
            case R.id.btn_nine:
                btnNumbers("9");
                break;
            case R.id.btn_decimal:
                btnNumbers(".");
                break;
            case R.id.btn_plus:
                btnSymbols("plus");
                break;
            case R.id.btn_minus:
                btnSymbols("minus");
                break;
            case R.id.btn_multiple:
                btnSymbols("multiple");
                break;
            case R.id.btn_divide:
                btnSymbols("divide");
                break;
            case R.id.btn_equals:
                equals();
                break;
            case R.id.btn_clear:
                clear();
                break;
        }
    }

    /**
     * Saves user input numbers.
     * @param number
     */
    private void btnNumbers(String number) {
        if(viewModel.getSavedSymbol().equals(NO_VALUE)) {
            viewModel.setNumberOne(number);
        } else {
            viewModel.setNumberTwo(number);
        }

        Log.d(TAG, "btnNumbers: mNumberOne " + viewModel.getNumberOne() + ", mNumberTwo " + viewModel.getNumberTwo());
    }


    private void btnSymbols(String symbol) {
        viewModel.setSavedSymbol(symbol);
    }

    /**
     * Does basic math.
     */
    private void equals() {


        // Converts string into a double
        double numberOne = Double.parseDouble(viewModel.getNumberOne());
        double numberTwo = Double.parseDouble(viewModel.getNumberTwo());



        switch(viewModel.getSavedSymbol()) {
            case "plus":
                viewModel.setEquals(numberOne + numberTwo);
                Log.d(TAG, "equals: " + numberOne + " + " + numberTwo + " = " + viewModel.getEquals());
                break;
            case "minus":
                viewModel.setEquals(numberOne - numberTwo);
                Log.d(TAG, "equals: " + numberOne + " - " + numberTwo + " = " + viewModel.getEquals());
                break;
            case "multiple":
                viewModel.setEquals(numberOne * numberTwo);
                Log.d(TAG, "equals: " + numberOne + " * " + numberTwo + " = " + viewModel.getEquals());
                break;
            case "divide":
                viewModel.setEquals(numberOne % numberTwo);
                Log.d(TAG, "equals: " + numberOne + " % " + numberTwo + " = " + viewModel.getEquals());
                break;
        }


    }

    /**
     * Removes all saved values.
     */
    private void clear() {
        viewModel.setNumberOne(NO_VALUE);
        viewModel.setNumberTwo(NO_VALUE);
        viewModel.setSavedSymbol(NO_VALUE);

        Log.d(TAG, "clear: NumberOne: " + viewModel.getNumberOne() + ", Number Two: " + viewModel.getNumberTwo() +
                " SavedSymbol: " + viewModel.getSavedSymbol());

    }


}
