package com.bertan.listviewexample

import android.support.test.espresso.Espresso.onData
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.matcher.ViewMatchers.*
import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import org.hamcrest.Matchers.*
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class MainActivityKtSpec {
    @get:Rule
    val rule = ActivityTestRule(MainActivity::class.java, true, true)

    @Test
    fun testPosition0() {
        onData(`is`(instanceOf(MainActivity.Person::class.java)))
            .inAdapterView(withId(R.id.listView))
            .atPosition(0)
            .check(matches(hasDescendant(allOf(withId(R.id.textViewName), withText("Amelie Parker")))))
            .check(matches(hasDescendant(allOf(withId(R.id.textViewAge), withText("25")))))
    }
}