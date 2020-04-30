package com.example.calculator.ui;


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

public class MinusTest {

    @Rule public ActivityTestRule<MainActivity> activityRule =
            new ActivityTestRule<>(MainActivity.class);

    @Test
    public void testMinus() {
        firstMinus();

        onView(withId(R.id.formula)).check(matches(withText("5-3")));
        onView(withId(R.id.solution)).check(matches(withText("2.0")));
    }

    @Test
    public void testEqualsThenMinus() {
        firstMinus();

        onView(withId(R.id.btn_minus)).perform(click());
        onView(withId(R.id.btn_one)).perform(click());
        onView(withId(R.id.btn_equals)).perform(click());

        onView(withId(R.id.formula)).check(matches(withText("2.0-1")));
        onView(withId(R.id.solution)).check(matches(withText("1.0")));

    }

    private void firstMinus() {
        onView(withId(R.id.btn_five)).perform(click());
        onView(withId(R.id.btn_minus)).perform(click());
        onView(withId(R.id.btn_three)).perform(click());
        onView(withId(R.id.btn_equals)).perform(click());
    }


    @Test
    public void testNegativeNumber() {
        onView(withId(R.id.btn_five)).perform(click());
        onView(withId(R.id.btn_minus)).perform(click());
        onView(withId(R.id.btn_seven)).perform(click());
        onView(withId(R.id.btn_equals)).perform(click());

        onView(withId(R.id.formula)).check(matches(withText("5-7")));
        onView(withId(R.id.solution)).check(matches(withText("-2.0")));
    }
}
