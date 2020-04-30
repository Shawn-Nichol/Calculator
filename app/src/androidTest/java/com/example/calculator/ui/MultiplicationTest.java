package com.example.calculator.ui;

import android.app.Activity;

import androidx.test.rule.ActivityTestRule;

import com.example.calculator.MainActivity;
import com.example.calculator.R;

import org.junit.Rule;
import org.junit.Test;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

public class MultiplicationTest {

    @Rule
    public ActivityTestRule<MainActivity> activityRule =
            new ActivityTestRule<>(MainActivity.class);

    @Test
    public void testMultiplication() {
        firstMultiplication();

        onView(withId(R.id.formula)).check(matches(withText("3*4")));
        onView(withId(R.id.solution)).check(matches(withText("12.0")));
    }

    @Test
    public void testEqualsThenMultiplication() {
        firstMultiplication();

        onView(withId(R.id.btn_multiple)).perform(click());
        onView(withId(R.id.btn_two)).perform(click());
        onView(withId(R.id.btn_equals)).perform(click());

        onView(withId(R.id.formula)).check(matches(withText("12.0*2")));
        onView(withId(R.id.solution)).check(matches(withText("24.0")));
    }

    private void firstMultiplication()  {
        onView(withId(R.id.btn_three)).perform(click());
        onView(withId(R.id.btn_multiple)).perform(click());
        onView(withId(R.id.btn_four)).perform(click());
        onView(withId(R.id.btn_equals)).perform(click());
    }

    @Test
    public void testMultipleZero() {
        onView(withId(R.id.btn_zero)).perform(click());
        onView(withId(R.id.btn_multiple)).perform(click());
        onView(withId(R.id.btn_three)).perform(click());
        onView(withId(R.id.btn_equals)).perform(click());

        onView(withId(R.id.formula)).check(matches(withText("0*3")));
        onView(withId(R.id.solution)).check(matches(withText("0.0")));
    }

}
