package com.example.calculator.flashCards;

import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;

import androidx.test.filters.LargeTest;
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner;
import androidx.test.rule.ActivityTestRule;

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
import static androidx.test.espresso.matcher.ViewMatchers.hasChildCount;
import static androidx.test.espresso.matcher.ViewMatchers.isChecked;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withContentDescription;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static org.hamcrest.core.AllOf.allOf;

@LargeTest
@RunWith(AndroidJUnit4ClassRunner.class)
public class Settings {

    @Rule
    public ActivityTestRule<MainActivity> mActivityTestRule = new ActivityTestRule<>(MainActivity.class);


    @Before
    public void setUp() {
        // Open Nav Drawer
        onView(withContentDescription("Open navigationdrawer")).perform(click());

        // Select FC setting in Nav Drawer.
        onView(Matchers.allOf(withId(R.id.design_menu_item_text),
                childAtPosition(childAtPosition(withId(R.id.design_navigation_view),
                        2), 0), isDisplayed())).perform(click());

    }

    @Test
    public void testAdditionToTrue() {
        onView(allOf(withId(R.id.switch_addition),
                childAtPosition(childAtPosition(withId(R.id.nav_host_fragment),
                        0), 1), isDisplayed())).perform(click());

//        onView(withId(R.id.switch_addition)).check(true(isChecked());
    }


    @Test


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


