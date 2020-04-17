package com.example.calculator.Math;

import android.widget.Toast;

import com.example.calculator.CalculatorFragment;

import static com.example.calculator.Constants.ADD;
import static com.example.calculator.Constants.DIVIDE;
import static com.example.calculator.Constants.MINUS;
import static com.example.calculator.Constants.MULTIPLE;

public class SelectFormula {
    private static final String TAG = "Calculate SelectFormula";

    // TODO solve the problem as entered on the screen.
    public static Double formula(String symbol, Double numberOne, Double numberTwo) {

        switch(symbol) {
            case ADD:
                return Addition.formula(numberOne, numberTwo);
            case MINUS:
                return Minus.formula(numberOne, numberTwo);
            case MULTIPLE:
                return Multiple.formula(numberOne, numberTwo);
            case DIVIDE:
                return Divide.formula(numberOne, numberTwo);

        }

        return null;
    }
}
