package com.example.calculator.calculator;

import android.content.Context;
import android.view.View;

import androidx.test.platform.app.InstrumentationRegistry;
import org.junit.Before;
import org.junit.Test;


import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import static java.lang.Double.NaN;
import static org.junit.Assert.assertEquals;


@RunWith(MockitoJUnitRunner.class)
public class HandlerTest {



    @Mock
    Context mContext;

    View mView;
    Handler mHandler;

    private CalculatorViewModel viewModel;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        viewModel = new CalculatorViewModel();
        mHandler = new Handler(mContext, viewModel);



    }

    @Test
    public void testGetNumber() {
        mHandler.setNumber(mView, "22+33");

        assertEquals("22+33", mHandler.getNumber());
    }

    /*
    Set Number
     */
    @Test
    public void testSetNumber() {
        mHandler.setNumber(mView, "1");

        assertEquals("1", viewModel.getNumber());
    }

    /*
    Update number
     */
    @Test
    public void updateExistingNumber() {
        mHandler.setNumber(mView, "1");
        mHandler.setNumber(mView, "2");

        assertEquals("12", viewModel.getNumber());
    }

    @Test
    public void setAdditionSymbol() {
        mHandler.setSymbol(mView, "+");

        assertEquals("+", viewModel.getNumber());
    }

    @Test
    public void setSubtractionSymbol() {
        mHandler.setSymbol(mView, "-");

        assertEquals("-", viewModel.getNumber());
    }

    @Test
    public void setMultiplicationSymbol() {
        mHandler.setSymbol(mView, "*");

        assertEquals("*", viewModel.getNumber());
    }

    @Test
    public void setDivisionSymbol() {
        mHandler.setSymbol(mView, "/");

        assertEquals("/", viewModel.getNumber());
    }

    @Test
    public void setFormula() {
        mHandler.setNumber(mView, "22+33");
        mHandler.setFormula("22+33");

        assertEquals("22+33", viewModel.getFormula());

    }

    @Test
    public void clearButton() {
        viewModel.setNumber("22+33");
        viewModel.setFormula("22+33");
        viewModel.setAnswer(55);

        mHandler.setNumber(mView, "clear");

        assertEquals("", viewModel.getNumber());
        assertEquals("", viewModel.getFormula());
        assertEquals(NaN, viewModel.getAnswer(), 0.00003);
    }

//    @Test
//    public void testAnswer() {
//        viewModel.setNumber("22+33");
//        mHandler.setEquals(mView);
//
//        assertEquals(55.0, viewModel.getAnswer(), 0.00003);
//    }
}
