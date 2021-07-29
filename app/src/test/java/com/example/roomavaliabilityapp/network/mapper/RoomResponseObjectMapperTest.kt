package com.example.roomavaliabilityapp.network.mapper

import com.example.roomavaliabilityapp.data.pojo.RoomObject
import com.example.roomavaliabilityapp.network.networkobjects.RoomResponseObject
import junit.framework.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class RoomResponseObjectMapperTest {
    lateinit var classUnderTest: RoomResponseObjectMapper

    @Before
    fun setUp() {
        classUnderTest = RoomResponseObjectMapper()
    }

    @Test
    fun mapResultIsAsExpected() {
        // given
        val testList = listOf(
            RoomResponseObject(
                "1",
                "2020-12-14T12:12:29.677Z",
                "Test",
                5,
                true
            ),
            RoomResponseObject(
                "2",
                "2020-06-15T12:09:29.677Z",
                "Test2",
                3,
                false
            )
        )

        val expectedOutput = listOf(
            RoomObject(
                "1",
                "2020-12-14T12:12:29.677Z",
                "Test",
                5,
                true
            ),
            RoomObject(
                "2",
                "2020-06-15T12:09:29.677Z",
                "Test2",
                3,
                false
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
        val testList = RoomResponseObject(
            "1",
            "2020-12-14T12:12:29.677Z",
            "Test",
            5,
            true
        )

        val expectedOutput = RoomObject(
            "1",
            "2020-12-14T12:12:29.677Z",
            "Test",
            5,
            true
        )

        // when
        val actualOutput = classUnderTest.mapIndividual(testList)

        // then
        assertEquals(expectedOutput, actualOutput)
    }
}