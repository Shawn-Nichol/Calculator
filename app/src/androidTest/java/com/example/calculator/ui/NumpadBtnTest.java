package com.example.calculator.ui;


import android.view.View;
import android.widget.Button;

import androidx.test.espresso.IdlingPolicies;
import androidx.test.espresso.ViewAction;
import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.filters.LargeTest;
import androidx.test.rule.ActivityTestRule;

import com.example.calculator.MainActivity;
import com.example.calculator.R;

import org.junit.Rule;
import org.junit.Test;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isClickable;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;


@LargeTest
public class NumpadBtnTest {

    @Rule
    public ActivityTestRule<MainActivity> activityRule =
            new ActivityTestRule<>(MainActivity.class);

    @Test
    public void testBtnZero() {
        View view = activityRule.getActivity().findViewById(R.id.btn_zero);
        myTest(view, "0");
    }

    @Test
    public void testBtnOne() {
        View view = activityRule.getActivity().findViewById(R.id.btn_one);
        myTest(view, "1");
    }

    @Test
    public void testBtnTwo() {
        View view = activityRule.getActivity().findViewById(R.id.btn_two);
        myTest(view, "2");
    }

    @Test
    public void testBtnThree() {
        View view = activityRule.getActivity().findViewById(R.id.btn_three);
        myTest(view, "3");
    }

    @Test
    public void testBtnFour() {
        View view = activityRule.getActivity().findViewById(R.id.btn_four);
        myTest(view, "4");
    }

    @Test
    public void testBtnFive() {
        View view = activityRule.getActivity().findViewById(R.id.btn_five);
        myTest(view, "5");
    }

    @Test
    public void testBtnSix() {
        View view = activityRule.getActivity().findViewById(R.id.btn_six);
        myTest(view, "6");
    }

    @Test
    public void testBtnSeven() {
        View view = activityRule.getActivity().findViewById(R.id.btn_seven);
        myTest(view, "7");
    }

    @Test
    public void testBtnEight() {
        View view = activityRule.getActivity().findViewById(R.id.btn_eight);
        myTest(view, "8");
    }

    @Test
    public void testBtnNine() {
        View view = activityRule.getActivity().findViewById(R.id.btn_nine);
        myTest(view, "9");
    }

    @Test
    public void testBtnPlus() {
        View view = activityRule.getActivity().findViewById(R.id.btn_plus);
        myTest(view, "+");
    }

    @Test
    public void testBtnMinus() {
        View view = activityRule.getActivity().findViewById(R.id.btn_minus);
        myTest(view, "-");
    }

    @Test
    public void testBtnMultiple() {
        View view = activityRule.getActivity().findViewById(R.id.btn_multiple);
        myTest(view, "X");
    }

    @Test
    public void testBtnDivide() {
        View view = activityRule.getActivity().findViewById(R.id.btn_divide);
        myTest(view, "%");
    }

    @Test
    public void testBtnEquals() {
        View view = activityRule.getActivity().findViewById(R.id.btn_equals);
        myTest(view, "=");
    }

    @Test
    public void testBtnClear() {
        View view = activityRule.getActivity().findViewById(R.id.btn_clear);
        myTest(view, "C");
    }


    public void myTest(View view, String btnPressed) {
        int convert = view.getId();
        onView(allOf(
                withId(convert),
                isClickable(),
                isDisplayed()))
                .check(matches(withText(btnPressed)))
                .perform(click());
    }


}
