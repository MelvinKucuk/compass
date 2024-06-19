package com.melvin.compass

import com.melvin.compass.main.domain.WordCount
import com.melvin.compass.main.domain.countWordOccurrences
import com.melvin.compass.main.domain.generateTenthString
import org.junit.Assert.assertEquals
import org.junit.Test

class StringManipulationTest {

    @Test
    fun testGenerateTenthString() {
        val input = "abcdefghijklmnopqrstuvwxyzabcdefgh"
        val expectedOutput = "jtd"
        val actualOutput = input.generateTenthString()
        assertEquals(expectedOutput, actualOutput)
    }

    @Test
    fun testCountWordOccurrences() {
        val input = "This is a sample text. This is just a sample."
        val expectedOutput = listOf(
            WordCount("This", 2),
            WordCount("is", 2),
            WordCount("a", 2),
            WordCount("sample", 1),
            WordCount("text.", 1),
            WordCount("just", 1),
            WordCount("sample.", 1)
        )
        val actualOutput = input.countWordOccurrences()
        assertEquals(expectedOutput, actualOutput)
    }
}