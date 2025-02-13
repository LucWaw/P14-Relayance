package com.kirabium.relayance.feature

import com.kirabium.relayance.extension.DateExt.Companion.toHumanDate
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import java.util.Calendar


class DetailCustomerTest {

    private lateinit var calendar: Calendar

    @BeforeEach
    fun setUp() {
        calendar = Calendar.getInstance()
    }

    @Test
    fun toHumanDateTest() {
        //Arrange
        calendar.set(Calendar.MONTH, Calendar.JANUARY)
        calendar.set(Calendar.DAY_OF_MONTH, 10)
        calendar.set(Calendar.YEAR, 2000)
        val date = calendar.time

        //Act
        val result = date.toHumanDate()

        //Assert
        assertEquals("10/01/2000", result)
    }

    @Test
    fun toHumanDateTest2() {
        //Arrange
        calendar.set(Calendar.MONTH, Calendar.JANUARY)
        calendar.set(Calendar.DAY_OF_MONTH, 1)
        calendar.set(Calendar.YEAR, 1)
        val date = calendar.time

        //Act
        val result = date.toHumanDate()

        //Assert
        assertEquals("01/01/0001", result)
    }

    @Test
    fun toHumanDateTest3() {
        //Arrange
        calendar.set(Calendar.MONTH, Calendar.NOVEMBER)
        calendar.set(Calendar.DAY_OF_MONTH, 10)
        calendar.set(Calendar.YEAR, 5045)
        val date = calendar.time

        //Act
        val result = date.toHumanDate()

        //Assert
        assertEquals("10/11/5045", result)
    }

    @Test
    fun toHumanDateTest4() {
        //Arrange
        calendar.set(Calendar.MONTH, Calendar.FEBRUARY)
        calendar.set(Calendar.DAY_OF_MONTH, 28)
        calendar.set(Calendar.YEAR, 2004)
        val date = calendar.time

        //Act
        val result = date.toHumanDate()

        //Assert
        assertEquals("28/02/2004", result)
    }

    @Test
    fun toHumanDateTest5() {
        //Arrange
        calendar.set(Calendar.MONTH, Calendar.JULY)
        calendar.set(Calendar.DAY_OF_MONTH, 10)
        calendar.set(Calendar.YEAR, 2000)
        val date = calendar.time

        //Act
        val result = date.toHumanDate()

        //Assert
        assertEquals("10/07/2000", result)
    }
}