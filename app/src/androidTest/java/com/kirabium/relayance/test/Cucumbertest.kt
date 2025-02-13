package com.kirabium.relayance.test


import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.closeSoftKeyboard
import androidx.test.espresso.action.ViewActions.typeText
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.rules.activityScenarioRule
import com.kirabium.relayance.R
import com.kirabium.relayance.ui.MainActivity
import com.kirabium.relayance.util.RecyclerViewItemCountAssertion
import dagger.hilt.android.testing.HiltAndroidTest
import io.cucumber.java.en.And
import io.cucumber.java.en.Given
import io.cucumber.java.en.Then
import io.cucumber.java.en.When
import io.cucumber.junit.WithJunitRule
import org.junit.Rule
import org.junit.runner.RunWith

@WithJunitRule
@HiltAndroidTest
class Cucumbertest {


    @get:Rule
    var activityScenarioRule = activityScenarioRule<MainActivity>()


    @Given("un commercial utilise le crm")
    fun a_commercial_is_using_the_application() {
        onView(withId(R.id.customerRecyclerView)).check(matches(isDisplayed()))
        onView(withId(R.id.customerRecyclerView)).check(
            RecyclerViewItemCountAssertion.withItemCount(
                5
            )
        )
    }

    @When("Le commercial appuie sur le bouton ajout client")
    fun the_commercial_presses_the_button() {
        onView(withId(R.id.addCustomerFab)).check(matches(isDisplayed()))

        onView(withId(R.id.addCustomerFab)).perform(click())
    }

    @And("Le commercial ajoute {string} comme nom")
    fun the_commercial_enters_the_name(name: String) {
        onView(withId(R.id.nameEditText)).check(matches(isDisplayed()))
        onView(withId(R.id.nameEditText)).perform(click())
        onView(withId(R.id.nameEditText)).perform(
            typeText(name), closeSoftKeyboard()
        )
    }

    @And("Le commercial ajoute {string} comme email")
    fun the_commercial_enters_the_email(email: String) {
        onView(withId(R.id.emailEditText)).check(matches(isDisplayed()))
        onView(withId(R.id.emailEditText)).perform(click())
        onView(withId(R.id.emailEditText)).perform(
            typeText(email), closeSoftKeyboard()
        )
    }

    @And("Le commercial appuie sur le bouton sauvegarder")
    fun the_commercial_presses_the_save_button() {
        onView(withId(R.id.saveFab)).check(matches(isDisplayed()))
        onView(withId(R.id.saveFab)).perform(click())
    }

    @Then("Le nouveau client est dans la liste des clients")
    fun the_new_customer_is_in_the_list() {
        onView(withId(R.id.customerRecyclerView)).check(matches(isDisplayed()))


        onView(withId(R.id.customerRecyclerView)).check(
            RecyclerViewItemCountAssertion.withItemCount(
                6
            )
        )
    }
}