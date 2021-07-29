package com.example.roomavaliabilityapp.presentation.rooms

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.roomavaliabilityapp.data.pojo.RoomObject
import com.example.roomavaliabilityapp.data.usecase.RoomsUsecase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RoomViewmodel @Inject constructor(
    private val roomsUsecase: RoomsUsecase,
    private val defaultDispatcher: CoroutineDispatcher
) : ViewModel() {
    private val mutableViewModelState = MutableLiveData<State>(State.FullScreenLoading)
    val viewModelState: LiveData<State> = mutableViewModelState

    fun getRooms() {
        mutableViewModelState.postValue(State.FullScreenLoading)
        GlobalScope.launch(defaultDispatcher) {
            roomsUsecase.roomsList.collect {
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
            val rooms: List<RoomObject>,
        ) : State()
    }
}