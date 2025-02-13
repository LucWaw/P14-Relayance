package com.kirabium.relayance.feature

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.intent.Intents
import androidx.test.espresso.intent.Intents.intended
import androidx.test.espresso.intent.matcher.IntentMatchers.hasComponent
import androidx.test.espresso.intent.matcher.IntentMatchers.hasExtra
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.rules.activityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.kirabium.relayance.R
import com.kirabium.relayance.ui.MainActivity
import com.kirabium.relayance.ui.adapter.CustomerAdapter
import com.kirabium.relayance.ui.detail.DetailActivity
import com.kirabium.relayance.util.RecyclerViewItemCountAssertion
import org.hamcrest.Matchers.allOf
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class AInstrumentedCustomerDisplayTest {

    @get:Rule
    var activityScenarioRule = activityScenarioRule<MainActivity>()

    @Before
    fun setup() {
        Intents.init()
    }

    @After
    fun tearDown() {
        Intents.release()
    }

    @Test
    fun displayCustomers() {
        // Check that the RecyclerView is displayed
        onView(withId(R.id.customerRecyclerView)).check(matches(isDisplayed()))

        // Check that the RecyclerView has 5 items
        onView(withId(R.id.customerRecyclerView)).check(
            RecyclerViewItemCountAssertion.withItemCount(
                5
            )
        )
    }

    @Test
    fun goToDetailActivity() {
        val itemElementText = "Alice Wonderland"
        onView(withText(itemElementText)).check(matches(isDisplayed()))

        // Click on the first item of the RecyclerView
        onView(withId(R.id.customerRecyclerView))
            .perform(
                RecyclerViewActions.actionOnItemAtPosition<CustomerAdapter.CustomerViewHolder>(
                    0,
                    click()
                )
            )


        intended(
            allOf(
                hasComponent(DetailActivity::class.java.name),
                hasExtra(DetailActivity.EXTRA_CUSTOMER_ID, 1)
            )
        )
    }
}