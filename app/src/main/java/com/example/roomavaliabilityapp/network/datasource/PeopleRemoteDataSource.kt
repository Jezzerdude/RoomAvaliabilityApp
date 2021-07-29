package com.example.roomavaliabilityapp.network.datasource

import com.example.roomavaliabilityapp.data.pojo.PeopleObject
import com.example.roomavaliabilityapp.network.api.PeopleApi
import com.example.roomavaliabilityapp.network.mapper.PeopleResponseObjectMapper
import javax.inject.Inject

class PeopleRemoteDataSource @Inject constructor(
    private val peopleApi: PeopleApi,
    private val peopleResponseObjectMapper: PeopleResponseObjectMapper
) {
    suspend fun getPeople(): Result<List<PeopleObject>> = kotlin.runCatching {
        peopleResponseObjectMapper.map(peopleApi.getPeople())
    }
}