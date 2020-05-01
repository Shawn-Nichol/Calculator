package com.example.calculator.calculator.flashcards.game;

import com.example.calculator.flashcards.settings.FlashCardsSettingViewModel;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.jupiter.api.Assertions.assertEquals;

@RunWith(MockitoJUnitRunner.class)
public class ViewModelTest {

    FlashCardsSettingViewModel viewModel;




    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        viewModel = new FlashCardsSettingViewModel();
    }

    @Test
    public void testNumberOfQuestions() {
        assertEquals(5, viewModel.getNumberOfQuestions());

        viewModel.setNumberOfQuestions(10);
        assertEquals(10, viewModel.getNumberOfQuestions());
    }

}
