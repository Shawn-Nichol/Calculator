package com.example.calculator.calculator.flashcards.settings;


import android.content.Context;

import com.example.calculator.calculator.CalculatorViewModel;
import com.example.calculator.flashcards.settings.FlashCardsSettingViewModel;
import com.example.calculator.flashcards.settings.SettingHandler;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class SettingsHandlerTest {

    @Mock
    Context mContext;

    SettingHandler mHandler;
    FlashCardsSettingViewModel viewModel;
    CalculatorViewModel calculatorViewModel;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        viewModel = new FlashCardsSettingViewModel();
        calculatorViewModel = new CalculatorViewModel();
        mHandler = new SettingHandler();
    }

    @Test
    public void testAdditionOn() {

    }



}
