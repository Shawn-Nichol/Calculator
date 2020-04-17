package com.example.calculator;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.work.Data;
import androidx.work.OneTimeWorkRequest;
import androidx.work.WorkInfo;
import androidx.work.WorkManager;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import com.example.calculator.Math.MathWorker;
import com.example.calculator.databinding.FragmentCalculatorBinding;
import com.example.calculator.databinding.NumpadBinding;

import static com.example.calculator.Constants.ADD;
import static com.example.calculator.Constants.ADD_TO_NUMBER_TWO;
import static com.example.calculator.Constants.DIVIDE;
import static com.example.calculator.Constants.ADD_TO_NUMBER_ONE;
import static com.example.calculator.Constants.MINUS;
import static com.example.calculator.Constants.MULTIPLE;
import static com.example.calculator.Constants.ON;
import static com.example.calculator.Constants.SET_NUMBER_ONE;
import static com.example.calculator.Constants.SET_SECOND_NUMBER;
import static com.example.calculator.Math.MathWorker.KEY_NUMBER_ONE;
import static com.example.calculator.Math.MathWorker.KEY_NUMBER_TWO;
import static com.example.calculator.Math.MathWorker.KEY_RESULT;
import static com.example.calculator.Math.MathWorker.KEY_SYMBOL;
import static java.lang.Double.NaN;


public class CalculatorFragment extends Fragment implements View.OnClickListener{

    private static final String TAG = "CalculatorFragment";

    // UI
    private TextView formula, solution;
    // ViewBindings
    FragmentCalculatorBinding binding;
    NumpadBinding numpadBinding;

    private CalculatorViewModel viewModel;

    // Saved State
    static final String STATE_FORMULA = "savedFormula";

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Log.d(TAG, "onCreateView: ");

        binding = FragmentCalculatorBinding.inflate(inflater, container, false);

        numpadBinding = binding.numpad;
        View view = binding.getRoot();

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Log.d(TAG, "onViewCreated: ");

        initTextView(view);
        initButtons(view);
        initViewModel();

        if(savedInstanceState != null) {
            Log.d(TAG, "onViewCreated: load saved instance state " + savedInstanceState.getString(STATE_FORMULA));

        }
    }

    private void initViewModel(){
        // Creates viewModel class, only done once.
        Log.d(TAG, "initViewModel: ");
        viewModel = new ViewModelProvider(this).get(CalculatorViewModel.class);

        if(viewModel.getViewModelState() == ON) {
            logViewModelInfo("initViewModel");
            showScreen();
        }
    }

    private void showScreen() {
        formula.setVisibility(View.VISIBLE);
        solution.setVisibility(View.VISIBLE);
        formula.setText(viewModel.getSavedFormula());

        if(viewModel.getSolution() == null) {
            Log.d(TAG, "showScreen: no solution saved display formula " + viewModel.getSavedFormula());
            solution.setText(String.valueOf(viewModel.getSavedFormula()));
        } else {
            solution.setText(String.valueOf(viewModel.getSolution()));
        }
    }


    private void initTextView(View v) {
        Log.d(TAG, "initTextView: ");
        formula = v.findViewById(R.id.formula);
        solution = v.findViewById(R.id.solution);
    }

    private void initButtons(View v) {
        Log.d(TAG, "initButtons: ");

        numpadBinding.btnZero.setOnClickListener(this);
        numpadBinding.btnOne.setOnClickListener(this);
        numpadBinding.btnTwo.setOnClickListener(this);
        numpadBinding.btnThree.setOnClickListener(this);
        numpadBinding.btnFour.setOnClickListener(this);
        numpadBinding.btnFive.setOnClickListener(this);
        numpadBinding.btnSix.setOnClickListener(this);
        numpadBinding.btnSeven.setOnClickListener(this);
        numpadBinding.btnEight.setOnClickListener(this);
        numpadBinding.btnNine.setOnClickListener(this);
        numpadBinding.btnDecimal.setOnClickListener(this);

        // Symbols
        numpadBinding.btnPlus.setOnClickListener(this);
        numpadBinding.btnMinus.setOnClickListener(this);
        numpadBinding.btnMultiple.setOnClickListener(this);
        numpadBinding.btnDivide.setOnClickListener(this);
        numpadBinding.btnEquals.setOnClickListener(this);
        numpadBinding.btnClear.setOnClickListener(this);

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
                viewModel.setViewModelState(ON);

                solution.setVisibility(View.VISIBLE);
                solution.setText(viewModel.getNumberOne());
                Log.d(TAG, "btnNumbers: " + viewModel.getNumberOne());
                break;
            case ADD_TO_NUMBER_ONE:
                viewModel.setNumberOne(viewModel.getNumberOne() + number);
                viewModel.setSavedFormula(viewModel.getSavedFormula() + viewModel.getNumberTwo());
                solution.setText(viewModel.getNumberOne());

                Log.d(TAG, "btnNumbers: " + viewModel.getNumberOne());
                break;
            case SET_SECOND_NUMBER:
                viewModel.setNumberTwo(number);
                viewModel.setSavedFormula(viewModel.getSavedFormula() + " " + viewModel.getNumberTwo());

                solution.setText(viewModel.getSavedFormula());
                viewModel.setFormulaState(ADD_TO_NUMBER_TWO);
                Log.d(TAG, "btnNumbers: " + viewModel.getNumberTwo());
                break;
            case ADD_TO_NUMBER_TWO:
                viewModel.setNumberTwo(viewModel.getNumberTwo() + number);

                solution.setText(viewModel.getSavedFormula() + " " + viewModel.getNumberTwo());
                Log.d(TAG, "btnNumbers: " + viewModel.getNumberTwo());

        }
    }

    private void btnSymbols(String symbol) {
        viewModel.setSavedSymbol(symbol);
        viewModel.setFormulaState(SET_SECOND_NUMBER);
        viewModel.setSavedFormula(viewModel.getNumberOne() + " " + symbol);
        solution.setText(viewModel.getSavedFormula());

    }

    /**
     * Does basic math.
     */
    private void equals() {

        // Converts string into a double
        double numberOne = Double.parseDouble(viewModel.getNumberOne());
        double numberTwo = Double.parseDouble(viewModel.getNumberTwo());
        String symbol = viewModel.getSavedSymbol();

        // Persistable set of key/value pairs which are used as the input for workers.
        Data equationData = new Data.Builder()
                .putDouble(KEY_NUMBER_ONE, numberOne)
                .putDouble(KEY_NUMBER_TWO, numberTwo)
                .putString(KEY_SYMBOL, symbol)
                .build();

        // Creates a single work request to be completed on a background thread.
        OneTimeWorkRequest mathWork = new OneTimeWorkRequest.Builder(MathWorker.class)
                .setInputData(equationData) // Adds input data to the work.
                .build(); // Builds work request

        // Enqueues deferrable work that is guaranteed to execute sometime after its constraints are met.
        WorkManager.getInstance(getActivity()).enqueue(mathWork);

        // Gets results of Work after it is finished.
        WorkManager.getInstance(getActivity()).getWorkInfoByIdLiveData(mathWork.getId())
                .observe(getActivity(), workInfo -> {
                    if(workInfo.getState() == WorkInfo.State.SUCCEEDED) {
                        Double result = workInfo.getOutputData().getDouble(KEY_RESULT, NaN);
                        Log.d(TAG, "equals: " + result);

                        viewModel.setSolution(result);

                        formula.setVisibility(View.VISIBLE);
                        formula.setText(viewModel.getSavedFormula() + " = " + viewModel.getSolution());
                        solution.setText(String.valueOf(result));
                    }
                });
    }

    /**
     * Removes all saved values.
     */
    private void clear() {
        viewModel.setClear();

        formula.setVisibility(View.INVISIBLE);
        solution.setVisibility(View.INVISIBLE);
        logViewModelInfo("clear");
    }

    private void logViewModelInfo(String method) {
        Log.d(TAG, method + " ViewModel saved data, " +
                "\n numberOne " + viewModel.getNumberOne() +
                "\n numberTwo " + viewModel.getNumberTwo() +
                "\n Formula " + viewModel.getSavedFormula() +
                "\n Solution " + viewModel.getSolution());
    }


    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        Log.d(TAG, "onSaveInstanceState: ");

        outState.putString(STATE_FORMULA, viewModel.getSavedFormula());
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
        numpadBinding = null;
    }
}
