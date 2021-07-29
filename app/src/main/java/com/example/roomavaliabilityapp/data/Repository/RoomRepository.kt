package com.example.roomavaliabilityapp.data.Repository

import com.example.roomavaliabilityapp.network.datasource.RoomRemoteDataSource
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class RoomRepository @Inject constructor(
    private val api: RoomRemoteDataSource
) {

    val roomList = flow {
        emit(api.getRooms())
    }
}