package com.github.floushee.playground

import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class CalculatorTest {

    @Test
    fun `1 plus 1 = 2`() {
        assertEquals(2.0, add(1.0, 1.0), "1 + 1 should equal 2")
    }

    @Test
    fun `0 times 10 = 0`() {
        assertEquals(0.0, multiply(10.0, 0.0),"0 * 10 should equal 0")
    }

    @Test
    fun `5 minus 3 = 2`() {
        assertEquals(2.0, subtract(5.0, 3.0), "5 - 3 should equal 2")
    }

    @Test
    fun `10 divide  2 = 5`() {
        assertEquals(5.0, divide(10.0, 2.0), "10 / 2 should equal 5")
    }
}