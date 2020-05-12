package com.example.calculator.flashCards;


import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.TextView;

import androidx.test.espresso.ViewInteraction;
import androidx.test.filters.LargeTest;
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner;
import androidx.test.rule.ActivityTestRule;
import androidx.test.runner.AndroidJUnit4;
import androidx.test.runner.AndroidJUnitRunner;


import com.example.calculator.MainActivity;
import com.example.calculator.R;


import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.Matchers;
import org.hamcrest.TypeSafeMatcher;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;


import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;

import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withContentDescription;

import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withParent;
import static androidx.test.espresso.matcher.ViewMatchers.withResourceName;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.instanceOf;
import static org.hamcrest.core.AllOf.allOf;

@LargeTest
@RunWith(AndroidJUnit4ClassRunner.class)
public class NavDrawer {

    // Launch Activity
    @Rule
    public ActivityTestRule<MainActivity> mActivityTestRule = new ActivityTestRule<>(MainActivity.class);


    @Before
    public void startTesting() {

        onView(withContentDescription("Open navigation drawer")).perform(click());
    }

    @Test
    public void TestNavDrawerContents() {
        ViewInteraction calculatorOption = onView(
                Matchers.allOf(withId(R.id.design_menu_item_text),
                        childAtPosition(childAtPosition(withId(R.id.design_navigation_view),
                                        1), 0), isDisplayed()));

        ViewInteraction flashCardOption = onView(
                Matchers.allOf(withId(R.id.design_menu_item_text),
                        childAtPosition(childAtPosition(withId(R.id.design_navigation_view),
                                2), 0), isDisplayed()));


        calculatorOption.check(matches(isDisplayed()));
        flashCardOption.check(matches(isDisplayed()));
    }

    @Test
    public void TestNavDrawerCalculatorClick() {
        ViewInteraction calculatorOption = onView(
                Matchers.allOf(withId(R.id.design_menu_item_text),
                        childAtPosition(childAtPosition(withId(R.id.design_navigation_view),
                                1), 0), isDisplayed()));

        calculatorOption.perform(click());

        // Checks the title in the action bar to make sure the correct fragment loaded.
        onView(allOf(instanceOf(TextView.class),
                withParent(withResourceName("action_bar"))))
                .check(matches(withText("Calculator")));

    }

    @Test
    public void TestNavDrawerFlashCardsClick() {
        onView(Matchers.allOf(withId(R.id.design_menu_item_text),
                childAtPosition(childAtPosition(withId(R.id.design_navigation_view),
                        2), 0), isDisplayed())).perform(click());

        onView(allOf(instanceOf(TextView.class),
                withParent(withResourceName("action_bar")))).check(matches(withText("FC Settings")));
    }


    @Test
    public void TestNavigationBackButton() {
        onView(Matchers.allOf(withId(R.id.design_menu_item_text),
                childAtPosition(childAtPosition(withId(R.id.design_navigation_view),
                        2), 0), isDisplayed())).perform(click());

        onView(Matchers.allOf(withContentDescription("Navigate up"),
                childAtPosition(allOf(withId(R.id.action_bar), childAtPosition(withId(R.id.action_bar_container),
                        0)), 1 ), isDisplayed())).perform(click());

        onView(allOf(instanceOf(TextView.class), withParent(withId(R.id.action_bar)))).check(matches(withText("Calculator")));
    }



    private static Matcher<View> childAtPosition(
            final Matcher<View> parentMatcher, final int position) {

        return new TypeSafeMatcher<View>() {
            @Override
            public void describeTo(Description description) {
                description.appendText("Child at position " + position + " in parent ");
                parentMatcher.describeTo(description);
            }

            @Override
            public boolean matchesSafely(View view) {
                ViewParent parent = view.getParent();
                return parent instanceof ViewGroup && parentMatcher.matches(parent)
                        && view.equals(((ViewGroup) parent).getChildAt(position));
            }
        };
    }
}
