package com.example.calculator.calculator.flashcards.game;

import android.content.Context;
import android.view.View;

import com.example.calculator.flashcards.game.FlashCardsGameViewModel;
import com.example.calculator.flashcards.game.GameHandler;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;

import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.jupiter.api.Assertions.assertEquals;

@RunWith(MockitoJUnitRunner.class)
public class GameHandlerTest {


    @Mock
    Context mContext;

    View mView;
    GameHandler mHandler;

    FlashCardsGameViewModel mViewModel;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        mViewModel = new FlashCardsGameViewModel();
        mHandler = new GameHandler(mContext, mViewModel);
    }

    @Test
    public void testNumbers() {
        mHandler.setNumber(mView, "1");
        mHandler.setNumber(mView, "2");
        mHandler.setNumber(mView, "3");

        assertEquals("123", mHandler.getNumber());
    }
}
