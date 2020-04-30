package com.example.calculator.ui;

import androidx.test.rule.ActivityTestRule;

import com.example.calculator.MainActivity;
import com.example.calculator.R;

import org.junit.Rule;
import org.junit.Test;

import java.text.Normalizer;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

public class DivideTest {

    @Rule
    public ActivityTestRule<MainActivity> activityRule =
            new ActivityTestRule<>(MainActivity.class);

    @Test
    public void testDivide() {
        firstDivision();
        solution("8/2", "4.0");
    }

    @Test
    public void testEqualsThenDivide() {
        firstDivision();
        onView(withId(R.id.btn_equals)).perform(click());

        onView(withId(R.id.btn_divide)).perform(click());
        onView(withId(R.id.btn_two)).perform(click());

        solution("4.0/2", "2.0");
    }

    private void firstDivision(){
        onView(withId(R.id.btn_eight)).perform(click());
        onView(withId(R.id.btn_divide)).perform(click());
        onView(withId(R.id.btn_two)).perform(click());

    }

    @Test
    public void testDivideByZero() {
        onView(withId(R.id.btn_eight)).perform(click());
        onView(withId(R.id.btn_divide)).perform(click());
        onView(withId(R.id.btn_zero)).perform(click());

        solution("8/0", "0.0");
    }


    private void solution(String formula, String solution) {
        onView(withId(R.id.btn_equals)).perform(click());

        onView(withId(R.id.formula)).check(matches(withText(formula)));
        onView(withId(R.id.solution)).check(matches(withText(solution)));
    }


}
