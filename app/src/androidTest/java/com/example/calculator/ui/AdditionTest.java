package com.example.calculator.ui;

import androidx.test.filters.LargeTest;
import androidx.test.rule.ActivityTestRule;

import com.example.calculator.MainActivity;
import com.example.calculator.R;

import org.junit.Rule;
import org.junit.Test;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.swipeRight;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isClickable;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

@LargeTest
public class AdditionTest {

    @Rule
    public ActivityTestRule<MainActivity> activityRule =
            new ActivityTestRule<>(MainActivity.class);


    @Test
    public void testAddition() {
        firstAddition();

        onView(withId(R.id.formula)).check(matches(withText("1+2")));
        onView(withId(R.id.solution)).check(matches(withText("3.0")));
    }

    @Test
    public void testEqualsThenAddition() {
        firstAddition();

        onView(withId(R.id.btn_plus)).perform(click());
        onView(withId(R.id.btn_three)).perform(click());
        onView(withId(R.id.btn_equals)).perform(click());

        onView(withId(R.id.formula)).check(matches(withText("3.0+3")));
        onView(withId(R.id.solution)).check(matches(withText("6.0")));
    }

    private void firstAddition() {
        onView(withId(R.id.btn_one)).perform(click());
        onView(withId(R.id.btn_plus)).perform(click());
        onView(withId(R.id.btn_two)).perform(click());
        onView(withId(R.id.btn_equals)).perform(click());
    }
}
