package com.example.roomavaliabilityapp.data.usecase

import com.example.roomavaliabilityapp.data.Repository.PeopleRepository
import com.example.roomavaliabilityapp.data.pojo.PeopleObject
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class PeopleUseCase @Inject constructor(
    private val peopleRepository: PeopleRepository
) {
    val peopleList: Flow<Result<List<PeopleObject>>> = peopleRepository.peopleList
}