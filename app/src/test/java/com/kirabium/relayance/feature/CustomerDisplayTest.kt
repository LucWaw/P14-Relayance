package com.kirabium.relayance.feature

import com.kirabium.relayance.data.DummyData.generateDate
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import java.time.temporal.ChronoUnit
import java.util.Calendar
import java.util.Date
import java.util.stream.Stream

class CustomerDisplayTest {

    companion object {
        @JvmStatic
        fun dateTabProvider(): Stream<Arguments?>? {

            return Stream.of(
                Arguments.of(1, Calendar.getInstance().apply { add(Calendar.MONTH, -1) }.time),
                Arguments.of(2, Calendar.getInstance().apply { add(Calendar.MONTH, -2) }.time),
                Arguments.of(3, Calendar.getInstance().apply { add(Calendar.MONTH, -3) }.time),
                Arguments.of(4, Calendar.getInstance().apply { add(Calendar.MONTH, -4) }.time),
                Arguments.of(5, Calendar.getInstance().apply { add(Calendar.MONTH, -5) }.time),
                Arguments.of(6, Calendar.getInstance().apply { add(Calendar.MONTH, -6) }.time),
                Arguments.of(7, Calendar.getInstance().apply { add(Calendar.MONTH, -7) }.time),
                Arguments.of(8, Calendar.getInstance().apply { add(Calendar.MONTH, -8) }.time),
                Arguments.of(9, Calendar.getInstance().apply { add(Calendar.MONTH, -9) }.time),
                Arguments.of(10, Calendar.getInstance().apply { add(Calendar.MONTH, -10) }.time),
                Arguments.of(11, Calendar.getInstance().apply { add(Calendar.MONTH, -11) }.time),
                Arguments.of(12, Calendar.getInstance().apply { add(Calendar.MONTH, -12) }.time),

                )
        }
    }

    @ParameterizedTest
    @MethodSource("dateTabProvider") //une autre solution était de rajouter le calendar en parametre de generateDate plutot que de refaire le calcul
    fun generateDateTest(dt: Int, oracle: Date) {


        //Act
        val result = generateDate(dt)


        //Assert
        val resultTruncated = result.toInstant().truncatedTo(ChronoUnit.HOURS)
        val oracleTruncated = oracle.toInstant().truncatedTo(ChronoUnit.HOURS)

        assertThat(resultTruncated).isEqualTo(oracleTruncated)

    }
}