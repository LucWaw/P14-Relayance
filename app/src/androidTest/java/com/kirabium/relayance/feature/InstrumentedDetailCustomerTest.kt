package com.kirabium.relayance.feature

import android.content.Context
import android.content.Intent
import androidx.activity.ComponentActivity
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.AndroidComposeTestRule
import androidx.compose.ui.test.onNodeWithText
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.kirabium.relayance.R
import com.kirabium.relayance.data.DummyData
import com.kirabium.relayance.extension.DateExt.Companion.toHumanDate
import com.kirabium.relayance.ui.detail.DetailActivity
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class InstrumentedDetailCustomerTest {

    @get:Rule
    val composeTestRule = createAndroidIntentComposeRule<DetailActivity> {
        Intent(it, DetailActivity::class.java).apply {
            putExtra(DetailActivity.EXTRA_CUSTOMER_ID, 1)
        }
    }

    @Test
    fun displayDetailScreen() = runTest{
        val customer = DummyData.customers.first().get(0)

        composeTestRule.onNodeWithText(customer.name).assertIsDisplayed()
        composeTestRule.onNodeWithText(customer.email).assertIsDisplayed()
        //get string created at fro string.xml
        composeTestRule.onNodeWithText(composeTestRule.activity.getString(R.string.created_at, customer.createdAt.toHumanDate())).assertIsDisplayed()

    }

    /**
     * Factory method to provide Android specific implementation of createComposeRule, for a given
     * activity class type A that needs to be launched via an intent.
     *
     * @param intentFactory A lambda that provides a Context that can used to create an intent. A intent needs to be returned.
     */
    inline fun <A: ComponentActivity> createAndroidIntentComposeRule(intentFactory: (context: Context) -> Intent) : AndroidComposeTestRule<ActivityScenarioRule<A>, A> {
        val context = ApplicationProvider.getApplicationContext<Context>()
        val intent = intentFactory(context)

        return AndroidComposeTestRule(
            activityRule = ActivityScenarioRule(intent),
            activityProvider = { scenarioRule -> scenarioRule.getActivity() }
        )
    }

    /**
     * Gets the activity from a scenarioRule.
     *
     * https://androidx.tech/artifacts/compose.ui/ui-test-junit4/1.0.0-alpha11-source/androidx/compose/ui/test/junit4/AndroidComposeTestRule.kt.html
     */
    fun <A : ComponentActivity> ActivityScenarioRule<A>.getActivity(): A {
        var activity: A? = null

        scenario.onActivity { activity = it }

        return activity ?: throw IllegalStateException("Activity was not set in the ActivityScenarioRule!")
    }
}