package com.example.calculator.calculator.calculator;

import com.example.calculator.calculator.CalculatorViewModel;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;



public class ViewModelTest {

    // System under test
    private CalculatorViewModel mVM;

    String NEW_NUMBER = "33+22";
    double ANSWER = 55;

    /**
     *  This method should be executed before each method in the current test.
     */
    @BeforeEach
    public void init() {
        mVM = new CalculatorViewModel();

    }


    /*
    Set the number
     */
    @Test
    void setNumberAndReturnNumber() {
        mVM.setNumber(NEW_NUMBER);
        assertEquals(NEW_NUMBER, mVM.getNumber());
    }

    /*
    Set answer
     */
    @Test
    void setAnswer () {
        mVM.setAnswer(55);

        assertEquals(ANSWER, 55);
    }

    /*
    set formula
     */
    @Test
    void setFormula() {
        mVM.setFormula("22+33");
        assertEquals("22+33", mVM.getFormula());
    }


}
