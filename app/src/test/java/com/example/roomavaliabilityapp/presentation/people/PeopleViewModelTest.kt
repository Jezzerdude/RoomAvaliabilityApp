package com.example.roomavaliabilityapp.presentation.people

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.roomavaliabilityapp.data.pojo.PeopleObject
import com.example.roomavaliabilityapp.data.usecase.PeopleUseCase
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

class PeopleViewModelTest {
    lateinit var classUnderTest: PeopleViewmodel

    @Rule
    @JvmField
    val instantExecutorRule = InstantTaskExecutorRule()

    @MockK
    lateinit var peopleUsecase: PeopleUseCase

    @ExperimentalCoroutinesApi
    private val testDispatcher = TestCoroutineDispatcher()

    private val people = MutableStateFlow(Result.success(emptyList<PeopleObject>()))

    @ExperimentalCoroutinesApi
    @Before
    fun setUp() {
        MockKAnnotations.init(this, relaxUnitFun = true)
        coEvery { peopleUsecase.peopleList } answers { people }

        classUnderTest = PeopleViewmodel(peopleUsecase, testDispatcher)
    }

    @Test
    fun shouldReturnContentWhenDataIsReturned() {
        // given
        val testPeopleList = listOf(
            PeopleObject(
                "Test",
                "12345",
                "Mr.testing",
                4,
                "Test@test.com",
                "Professional Tester",
                "Tester"
            )
        )
        people.value = Result.success(testPeopleList)

        // when
        classUnderTest.getPeople()

        // then
        coVerify { peopleUsecase.peopleList }
        val expected = PeopleViewmodel.State.Content(testPeopleList)
        assertEquals(expected, classUnderTest.viewModelState.value)
    }

    @Test
    fun shouldGoToErrorStateWhenDataErrors() {
        // given
        people.value = Result.failure(Exception())

        // when
        classUnderTest.getPeople()

        // then
        coVerify { peopleUsecase.peopleList }
        val expected = PeopleViewmodel.State.FullScreenError
        assertEquals(expected, classUnderTest.viewModelState.value)
    }
}