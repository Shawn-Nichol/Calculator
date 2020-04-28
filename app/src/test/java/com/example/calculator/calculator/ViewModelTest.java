package com.example.calculator.calculator;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;


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

        assertEquals(ANSWER, mVM.getAnswer());
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
