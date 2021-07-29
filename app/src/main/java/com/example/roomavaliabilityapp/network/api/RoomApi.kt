package com.example.roomavaliabilityapp.network.api

import com.example.roomavaliabilityapp.network.networkobjects.RoomResponseObject
import retrofit2.http.GET

interface RoomApi {
    @GET("rooms")
    suspend fun getRooms(
    ): List<RoomResponseObject>
}