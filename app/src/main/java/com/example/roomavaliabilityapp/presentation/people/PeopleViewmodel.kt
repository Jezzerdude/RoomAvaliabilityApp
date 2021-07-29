package com.example.roomavaliabilityapp.presentation.people

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.roomavaliabilityapp.data.pojo.PeopleObject
import com.example.roomavaliabilityapp.data.usecase.PeopleUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PeopleViewmodel @Inject constructor(
    private val peopleUseCase: PeopleUseCase,
    private val defaultDispatcher: CoroutineDispatcher
) : ViewModel() {
    private val mutableViewModelState = MutableLiveData<State>(State.FullScreenLoading)
    val viewModelState: LiveData<State> = mutableViewModelState

    fun getPeople() {
        mutableViewModelState.postValue(State.FullScreenLoading)
        GlobalScope.launch(defaultDispatcher) {
            peopleUseCase.peopleList.collect {
                it.onSuccess {
                    mutableViewModelState.postValue(State.Content(it))
                }
                it.onFailure {
                    mutableViewModelState.postValue(State.FullScreenError)
                }
            }
        }
    }

    sealed class State {
        object FullScreenLoading : State()
        object FullScreenError : State()
        data class Content(
            val people: List<PeopleObject>,
        ) : State()
    }
}