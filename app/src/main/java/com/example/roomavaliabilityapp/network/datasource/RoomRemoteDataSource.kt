package com.example.roomavaliabilityapp.network.datasource

import com.example.roomavaliabilityapp.data.pojo.RoomObject
import com.example.roomavaliabilityapp.network.api.RoomApi
import com.example.roomavaliabilityapp.network.mapper.RoomResponseObjectMapper
import javax.inject.Inject

class RoomRemoteDataSource @Inject constructor(
    private val roomApi: RoomApi,
    private val roomResponseObjectObjectMapper: RoomResponseObjectMapper
) {
    suspend fun getRooms(): Result<List<RoomObject>> = kotlin.runCatching {
        roomResponseObjectObjectMapper.map(roomApi.getRooms())
    }
}