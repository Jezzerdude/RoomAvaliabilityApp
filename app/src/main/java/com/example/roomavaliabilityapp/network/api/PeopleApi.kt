package com.example.roomavaliabilityapp.network.api

import com.example.roomavaliabilityapp.network.networkobjects.PeopleResponseObject
import retrofit2.http.GET

interface PeopleApi {
    @GET("people")
    suspend fun getPeople(
    ): List<PeopleResponseObject>
}