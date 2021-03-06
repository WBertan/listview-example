package com.bertan.listviewexample

import android.support.test.espresso.Espresso.onData
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.matcher.ViewMatchers.*
import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import org.hamcrest.Matchers.*
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class OrderActivityKtSpec {
    @get:Rule
    val rule = ActivityTestRule(OrderActivity::class.java, true, true)

    @Test
    fun when_order_does_not_have_items_it_should_show_zero_items_and_zero_total() {
        val order = OrderActivity.Order(emptyList())

        val expectedItemsTotal = "0"
        val expectedTotal = "0.0"

        rule.activity.updateListView(listOf(order))

        onData(`is`(instanceOf(OrderActivity.Order::class.java)))
            .inAdapterView(withId(R.id.listView))
            .atPosition(0)
            .check(matches(hasDescendant(allOf(withId(R.id.textViewTotalItems), withText(expectedItemsTotal)))))
            .check(matches(hasDescendant(allOf(withId(R.id.textViewTotal), withText(expectedTotal)))))
    }

    @Test
    fun when_order_does_have_1_item_it_should_show_1_item_and_item_value_as_total() {
        val item01 = OrderActivity.Item("dummyItem01", 1.0)
        val order = OrderActivity.Order(listOf(item01))

        val expectedItemsTotal = "1"
        val expectedTotal = "1.0"

        rule.activity.updateListView(listOf(order))

        onData(`is`(instanceOf(OrderActivity.Order::class.java)))
            .inAdapterView(withId(R.id.listView))
            .atPosition(0)
            .check(matches(hasDescendant(allOf(withId(R.id.textViewTotalItems), withText(expectedItemsTotal)))))
            .check(matches(hasDescendant(allOf(withId(R.id.textViewTotal), withText(expectedTotal)))))
    }

    @Test
    fun when_order_does_have_multiple_items_it_should_show_quantity_of_items_and_sum_of_items_values_as_total() {
        val item01 = OrderActivity.Item("dummyItem01", 1.0)
        val item02 = OrderActivity.Item("dummyItem02", 2.0)
        val order = OrderActivity.Order(listOf(item01, item02))

        val expectedItemsTotal = "2"
        val expectedTotal = "3.0"

        rule.activity.updateListView(listOf(order))

        onData(`is`(instanceOf(OrderActivity.Order::class.java)))
            .inAdapterView(withId(R.id.listView))
            .atPosition(0)
            .check(matches(hasDescendant(allOf(withId(R.id.textViewTotalItems), withText(expectedItemsTotal)))))
            .check(matches(hasDescendant(allOf(withId(R.id.textViewTotal), withText(expectedTotal)))))
    }

    @Test
    fun multiple_failing_tests_on_one_go() {
        val case01 = {
            val item01 = OrderActivity.Item("dummyItem01", 1.0)
            val item02 = OrderActivity.Item("dummyItem02", 2.0)
            val order = OrderActivity.Order(listOf(item01, item02))

            val expectedItemsTotal = "9999"
            val expectedTotal = "9999.0"

            rule.activity.updateListView(listOf(order))

            onData(`is`(instanceOf(OrderActivity.Order::class.java)))
                .inAdapterView(withId(R.id.listView))
                .atPosition(0)
                .check(matches(hasDescendant(allOf(withId(R.id.textViewTotalItems), withText(expectedItemsTotal)))))
                .check(matches(hasDescendant(allOf(withId(R.id.textViewTotal), withText(expectedTotal)))))
        }
        val case02 = {
            val item01 = OrderActivity.Item("dummyItem01", 1.0)
            val item02 = OrderActivity.Item("dummyItem02", 2.0)
            val order = OrderActivity.Order(listOf(item01, item02))

            val expectedItemsTotal = "2"
            val expectedTotal = "3.0"

            rule.activity.updateListView(listOf(order))

            onData(`is`(instanceOf(OrderActivity.Order::class.java)))
                .inAdapterView(withId(R.id.listView))
                .atPosition(0)
                .check(matches(hasDescendant(allOf(withId(R.id.textViewTotalItems), withText(expectedItemsTotal)))))
                .check(matches(hasDescendant(allOf(withId(R.id.textViewTotal), withText(expectedTotal)))))
        }
        val case03 = {
            val item01 = OrderActivity.Item("dummyItem01", 1.0)
            val item02 = OrderActivity.Item("dummyItem02", 2.0)
            val order = OrderActivity.Order(listOf(item01, item02))

            val expectedItemsTotal = "8888"
            val expectedTotal = "8888.0"

            rule.activity.updateListView(listOf(order))

            onData(`is`(instanceOf(OrderActivity.Order::class.java)))
                .inAdapterView(withId(R.id.listView))
                .atPosition(0)
                .check(matches(hasDescendant(allOf(withId(R.id.textViewTotalItems), withText(expectedItemsTotal)))))
                .check(matches(hasDescendant(allOf(withId(R.id.textViewTotal), withText(expectedTotal)))))
        }
        //Expect Case01/Case03 fail and Case02 pass
        val expectedErrors = listOf(
            "case01",
            "case03"
        )
        val listErrors = mutableListOf<String>()
        try {
            case01()
        } catch (exception: AssertionError) { listErrors.add("case01") }
        try {
            case02()
        } catch (exception: AssertionError) { listErrors.add("case02") }
        try {
            case03()
        } catch (exception: AssertionError) { listErrors.add("case03") }

        assertEquals(expectedErrors, listErrors)
    }
}