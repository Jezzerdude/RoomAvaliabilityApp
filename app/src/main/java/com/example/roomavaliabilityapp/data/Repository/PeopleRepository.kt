package com.example.roomavaliabilityapp.data.Repository

import com.example.roomavaliabilityapp.network.datasource.PeopleRemoteDataSource
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class PeopleRepository @Inject constructor(
    private val api: PeopleRemoteDataSource
) {

    val peopleList = flow {
        emit(api.getPeople())
    }
}