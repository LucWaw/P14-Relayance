package com.kirabium.relayance.feature

import android.util.Log
import com.kirabium.relayance.domain.model.Customer
import com.kirabium.relayance.extension.StringExt.Companion.isValidEmail
import org.junit.jupiter.api.Assertions.assertFalse
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import java.util.Calendar
import java.util.Date
import kotlin.properties.Delegates


class AddCustomerTest {

    private var id by Delegates.notNull<Int>()
    private lateinit var name : String
    private lateinit var email : String

    @BeforeEach
    fun setUp(){
        id = 1
        name = "John Doe"
        email = "mail@test.fr"
    }

   @Test
    fun isValidEmail(){
        //Arrange
        val email = "test@try.mail"

        //Act
        val result = email.isValidEmail()

        assertTrue(result)
    }

    @Test
    fun isValidEmail2(){
        //Arrange
        val email = "test.truc@try.mail"

        //Act
        val result = email.isValidEmail()

        assertTrue(result)
    }

    @Test
    fun isNotValidEmail(){
        //Arrange
        val email = "testry.mail"

        //Act
        val result = email.isValidEmail()

        assertFalse(result)
    }

    @Test
    fun isNotValidEmail2(){
        //Arrange
        val email = "test@ma@il.d"

        //Act
        val result = email.isValidEmail()

        assertFalse(result)
    }

    @Test
    fun isNewCustomerTest(){
        //Arrange
        val date = Date()
        val customer = Customer(id, name, email, date)

        //Act
        val result = customer.isNewCustomer()

        //Assert
        assertTrue(result)
    }

    @Test
    fun isNewCustomerTest2(){
        //Arrange
        val date = Calendar.getInstance().apply {
            add(Calendar.MONTH, -2)
        }.time
        val customer = Customer(id, name, email, date)

        //Act
        val result = customer.isNewCustomer()
        println("isNewCustomerTest2 $result")
        //Assert
        assertTrue(result)
    }

    @Test
    fun notNewCustomerTest2(){
        //Arrange
        val date = Calendar.getInstance().apply {
            add(Calendar.MONTH, -3)
            add(Calendar.DAY_OF_MONTH, -3)//to avoid conflict with equals date (it could be anything else than 3)
        }.time
        val customer = Customer(id, name, email, date)

        //Act
        val result = customer.isNewCustomer()

        //Assert
        assertFalse(result)
    }

    @Test
    fun notNewCustomerTest(){
        //Arrange
        val date = Calendar.getInstance().apply {
            add(Calendar.MONTH, -4)
        }.time
        val customer = Customer(id, name, email, date)

        //Act
        val result = customer.isNewCustomer()
        println("notNewCustomerTest $result")

        //Assert
        assertFalse(result)
    }

    @Test
    fun edgeCaseExactThreeMonthsAgo() {
        val calendar = Calendar.getInstance()
        val date = calendar.apply {
            add(Calendar.MONTH, -3)
        }.time
        val customer = Customer(id, name, email, date)

        val result = customer.isNewCustomer(calendar)

        assertTrue(result)
    }


}