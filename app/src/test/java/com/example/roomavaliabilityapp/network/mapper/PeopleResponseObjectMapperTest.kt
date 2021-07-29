package com.example.roomavaliabilityapp.network.mapper

import com.example.roomavaliabilityapp.data.pojo.PeopleObject
import com.example.roomavaliabilityapp.network.networkobjects.PeopleResponseObject
import junit.framework.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class PeopleResponseObjectMapperTest {
    lateinit var classUnderTest: PeopleResponseObjectMapper

    @Before
    fun setUp() {
        classUnderTest = PeopleResponseObjectMapper()
    }

    @Test
    fun mapResultIsAsExpected() {
        // given
        val testList = listOf(
            PeopleResponseObject(
                "TestImage",
                "12345",
                "Mr.Test",
                1,
                "Test@test.com",
                "Professional Tester",
                "Testing"
            ),
            PeopleResponseObject(
                "TestImage2",
                "54321",
                "Mrs.Test",
                2,
                "Test2@test.com",
                "Tester Professional",
                "Testing2"
            )
        )

        val expectedOutput = listOf(
            PeopleObject(
                "TestImage",
                "12345",
                "Mr.Test",
                1,
                "Test@test.com",
                "Professional Tester",
                "Testing"
            ),
            PeopleObject(
                "TestImage2",
                "54321",
                "Mrs.Test",
                2,
                "Test2@test.com",
                "Tester Professional",
                "Testing2"
            )
        )

        // when
        val actualOutput = classUnderTest.map(testList)

        // then
        assertEquals(expectedOutput, actualOutput)
    }

    @Test
    fun mapIndividualResultIsAsExpected() {
        // given
        val testList = PeopleResponseObject(
            "TestImage",
            "12345",
            "Mr.Test",
            1,
            "Test@test.com",
            "Professional Tester",
            "Testing"
        )

        val expectedOutput = PeopleObject(
            "TestImage",
            "12345",
            "Mr.Test",
            1,
            "Test@test.com",
            "Professional Tester",
            "Testing"
        )

        // when
        val actualOutput = classUnderTest.mapIndividual(testList)

        // then
        assertEquals(expectedOutput, actualOutput)
    }
}