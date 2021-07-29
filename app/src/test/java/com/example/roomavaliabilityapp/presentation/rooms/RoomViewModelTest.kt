package com.example.roomavaliabilityapp.presentation.rooms

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.roomavaliabilityapp.data.pojo.RoomObject
import com.example.roomavaliabilityapp.data.usecase.RoomsUsecase
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.MockK
import junit.framework.Assert.assertEquals
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.test.TestCoroutineDispatcher
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class RoomViewModelTest {
    lateinit var classUnderTest: RoomViewmodel

    @Rule
    @JvmField
    val instantExecutorRule = InstantTaskExecutorRule()

    @MockK
    lateinit var roomsUsecase: RoomsUsecase

    @ExperimentalCoroutinesApi
    private val testDispatcher = TestCoroutineDispatcher()

    private val rooms = MutableStateFlow(Result.success(emptyList<RoomObject>()))

    @ExperimentalCoroutinesApi
    @Before
    fun setUp() {
        MockKAnnotations.init(this, relaxUnitFun = true)
        coEvery { roomsUsecase.roomsList } answers { rooms }

        classUnderTest = RoomViewmodel(roomsUsecase, testDispatcher)
    }

    @Test
    fun shouldReturnContentWhenDataIsReturned() {
        // given
        val testRoomList = listOf(
            RoomObject(
                "1",
                "2020-12-14T12:12:29.677Z",
                "Test",
                5,
                true
            )
        )
        rooms.value = Result.success(testRoomList)

        // when
        classUnderTest.getRooms()

        // then
        coVerify { roomsUsecase.roomsList }
        val expected = RoomViewmodel.State.Content(testRoomList)
        assertEquals(expected, classUnderTest.viewModelState.value)
    }

    @Test
    fun shouldGoToErrorStateWhenDataErrors() {
        // given
        rooms.value = Result.failure(Exception())

        // when
        classUnderTest.getRooms()

        // then
        coVerify { roomsUsecase.roomsList }
        val expected = RoomViewmodel.State.FullScreenError
        assertEquals(expected, classUnderTest.viewModelState.value)
    }
}