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
import android.widget.TextView;

import com.example.calculator.Math.Addition;
import com.example.calculator.Math.Divide;
import com.example.calculator.Math.Minus;
import com.example.calculator.Math.Multiple;

import static com.example.calculator.Constants.ADD;
import static com.example.calculator.Constants.ADD_TO_NUMBER_TWO;
import static com.example.calculator.Constants.DIVIDE;
import static com.example.calculator.Constants.ADD_TO_NUMBER_ONE;
import static com.example.calculator.Constants.MINUS;
import static com.example.calculator.Constants.MULTIPLE;
import static com.example.calculator.Constants.SET_NUMBER_ONE;
import static com.example.calculator.Constants.SET_SECOND_NUMBER;


public class CalculatorFragment extends Fragment implements View.OnClickListener{

    private static final String TAG = "CalculatorFragment";

    // UI
    Button btnZero, btnOne, btnTwo, btnThree, btnFour, btnFive, btnSix, btnSeven, btnEight, btnNine, btnDecimal;
    Button btnPlus, btnMinus, btnMultiple, btnDivide, btnEquals, btnClear;

    TextView formula, solution;


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
        initTextView(view);

        if(viewModel.getFormulaState() > SET_NUMBER_ONE) {
            formula.setVisibility(View.VISIBLE);
            solution.setVisibility(View.INVISIBLE);
        }
    }


    private void initViewModel(){
        // Creates viewModel class, only done once.
        viewModel = new ViewModelProvider(this).get(CalculatorViewModel.class);


    }

    private void initTextView(View v) {
        formula = v.findViewById(R.id.formula);
        solution = v.findViewById(R.id.solution);
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
                btnSymbols(ADD);
                break;
            case R.id.btn_minus:
                btnSymbols(MINUS);
                break;
            case R.id.btn_multiple:
                btnSymbols(MULTIPLE);
                break;
            case R.id.btn_divide:
                btnSymbols(DIVIDE);
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


        switch (viewModel.getFormulaState()) {
            case SET_NUMBER_ONE:
                viewModel.setNumberOne(number);
                viewModel.setFormulaState(ADD_TO_NUMBER_ONE);
                solution.setVisibility(View.VISIBLE);
                solution.setText(viewModel.getNumberOne());
                Log.d(TAG, "btnNumbers: " + viewModel.getNumberOne());
                break;
            case ADD_TO_NUMBER_ONE:
                viewModel.setNumberOne(viewModel.getNumberOne() + number);
                viewModel.setFormula(viewModel.getDisplayFormula() + viewModel.getNumberTwo());
                solution.setText(viewModel.getNumberOne());

                Log.d(TAG, "btnNumbers: " + viewModel.getNumberOne());
                break;
            case SET_SECOND_NUMBER:
                viewModel.setNumberTwo(number);
                viewModel.setFormula(viewModel.getDisplayFormula() + " " + viewModel.getNumberTwo());

                solution.setText(viewModel.getDisplayFormula());
                viewModel.setFormulaState(ADD_TO_NUMBER_TWO);
                Log.d(TAG, "btnNumbers: " + viewModel.getNumberTwo());
                break;
            case ADD_TO_NUMBER_TWO:
                viewModel.setNumberTwo(viewModel.getNumberTwo() + number);

                solution.setText(viewModel.getDisplayFormula() + " " + viewModel.getNumberTwo());
                Log.d(TAG, "btnNumbers: " + viewModel.getNumberTwo());

        }
    }


    private void btnSymbols(String symbol) {

        viewModel.setSavedSymbol(symbol);
        viewModel.setFormulaState(SET_SECOND_NUMBER);

        viewModel.setFormula(viewModel.getNumberOne() + " " + symbol);

        solution.setText(viewModel.getDisplayFormula());

    }

    /**
     * Does basic math.
     */
    private void equals() {


        // Converts string into a double
        double numberOne = Double.parseDouble(viewModel.getNumberOne());
        double numberTwo = Double.parseDouble(viewModel.getNumberTwo());



        switch(viewModel.getSavedSymbol()) {
            case ADD:
                viewModel.setEquals(Addition.formula(numberOne, numberTwo));
                break;
            case MINUS:
                viewModel.setEquals(Minus.formula(numberOne, numberTwo));
                break;
            case MULTIPLE:
                viewModel.setEquals(Multiple.formula(numberOne, numberTwo));
                break;
            case DIVIDE:
                viewModel.setEquals(Divide.formula(numberOne, numberTwo));
                break;
        }



        formula.setVisibility(View.VISIBLE);
        formula.setText(viewModel.getDisplayFormula() + " = " + viewModel.getEquals());
        solution.setText(String.valueOf(viewModel.getEquals()));


    }

    /**
     * Removes all saved values.
     */
    private void clear() {
        viewModel.setFormulaState(SET_NUMBER_ONE);

        formula.setVisibility(View.INVISIBLE);
        solution.setVisibility(View.INVISIBLE);

        Log.d(TAG, "clear: NumberOne: " + viewModel.getNumberOne() + ", Number Two: " + viewModel.getNumberTwo() +
                " SavedSymbol: " + viewModel.getSavedSymbol());

    }


}
