package com.kirabium.relayance.feature

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.closeSoftKeyboard
import androidx.test.espresso.action.ViewActions.typeText
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.rules.activityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.kirabium.relayance.R
import com.kirabium.relayance.ui.MainActivity
import com.kirabium.relayance.util.RecyclerViewItemCountAssertion
import io.cucumber.java.en.And
import io.cucumber.java.en.Given
import io.cucumber.java.en.Then
import io.cucumber.java.en.When
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith


/**
 * Same that cucumber test to view it in the test report
 */
@RunWith(AndroidJUnit4::class)
class InstrumentedAddCustomerTest {
    @get:Rule
    var activityScenarioRule = activityScenarioRule<MainActivity>()


    @Test
    fun add_Customer() {
        onView(withId(R.id.customerRecyclerView)).check(matches(isDisplayed()))
        onView(withId(R.id.customerRecyclerView)).check(
            RecyclerViewItemCountAssertion.withItemCount(
                5
            )
        )
        onView(withId(R.id.addCustomerFab)).check(matches(isDisplayed()))

        onView(withId(R.id.addCustomerFab)).perform(click())

        onView(withId(R.id.nameEditText)).check(matches(isDisplayed()))
        onView(withId(R.id.nameEditText)).perform(click())
        onView(withId(R.id.nameEditText)).perform(
            typeText("John Doe"), closeSoftKeyboard()
        )

        onView(withId(R.id.emailEditText)).check(matches(isDisplayed()))
        onView(withId(R.id.emailEditText)).perform(click())
        onView(withId(R.id.emailEditText)).perform(
            typeText("test@truc.com"), closeSoftKeyboard()
        )

        onView(withId(R.id.saveFab)).check(matches(isDisplayed()))
        onView(withId(R.id.saveFab)).perform(click())

        onView(withId(R.id.customerRecyclerView)).check(matches(isDisplayed()))


        onView(withId(R.id.customerRecyclerView)).check(
            RecyclerViewItemCountAssertion.withItemCount(
                6
            )
        )
    }
}