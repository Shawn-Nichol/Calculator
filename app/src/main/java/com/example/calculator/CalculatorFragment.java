package com.example.calculator;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;


public class CalculatorFragment extends Fragment implements View.OnClickListener{


    private static final String TAG = "CalculatorFragment";

    // UI
    Button btnZero, btnOne, btnTwo, btnThree, btnFour, btnFive, btnSix, btnSeven, btnEight, btnNine, btnDecimal;
    Button btnPlus, btnMinus, btnMultiple, btnDivide, btnEquals, btnClear;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Log.d(TAG, "onCreateView: ");
        return inflater.inflate(R.layout.fragment_calculator, container, false);

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Log.d(TAG, "onViewCreated: ");


        initButtons(view);



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
                btnNumbers(0);
                break;
            case R.id.btn_one:
                btnNumbers(1);
                break;
            case R.id.btn_two:
                btnNumbers(2);
                break;
            case R.id.btn_three:
                btnNumbers(3);
                break;
            case R.id.btn_four:
                btnNumbers(4);
                break;
            case R.id.btn_five:
                btnNumbers(5);
                break;
            case R.id.btn_six:
                btnNumbers(6);
                break;
            case R.id.btn_seven:
                btnNumbers(7);
                break;
            case R.id.btn_eight:
                btnNumbers(8);
                break;
            case R.id.btn_nine:
                btnNumbers(9);
                break;
            case R.id.btn_decimal:
                Log.d(TAG, "onClick: decimal");
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
                btnSymbols("equals");
                break;
            case R.id.btn_clear:
                Log.d(TAG, "onClick: clear");
                break;
        }
    }

    private void btnNumbers(int number) {
        Log.d(TAG, "btnNumbers: " + number);

    }

    private void btnSymbols(String symbol) {
        Log.d(TAG, "btnSymbols: " + symbol);
    }


}
