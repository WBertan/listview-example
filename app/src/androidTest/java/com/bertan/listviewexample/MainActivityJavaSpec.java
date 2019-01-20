package com.bertan.listviewexample;

import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onData;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.*;
import static org.hamcrest.Matchers.*;

@RunWith(AndroidJUnit4.class)
public class MainActivityJavaSpec {
    @Rule
    public ActivityTestRule<MainActivity> rule = new ActivityTestRule<>(MainActivity.class, true, true);

    @Test
    public void testPosition0() {
        onData(is(instanceOf(MainActivity.Person.class)))
                .inAdapterView(withId(R.id.listView))
                .atPosition(0)
                .check(matches(hasDescendant(allOf(withId(R.id.textViewName), withText("Amelie Parker")))))
                .check(matches(hasDescendant(allOf(withId(R.id.textViewAge), withText("25")))));
    }
}
