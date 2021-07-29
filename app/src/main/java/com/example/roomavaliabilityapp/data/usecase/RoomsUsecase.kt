package com.example.roomavaliabilityapp.data.usecase

import com.example.roomavaliabilityapp.data.Repository.RoomRepository
import com.example.roomavaliabilityapp.data.pojo.RoomObject
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class RoomsUsecase @Inject constructor(
    private val roomRepository: RoomRepository
) {
    val roomsList: Flow<Result<List<RoomObject>>> = roomRepository.roomList
}