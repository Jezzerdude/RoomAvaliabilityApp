package com.example.roomavaliabilityapp.network.mapper

import com.example.roomavaliabilityapp.data.pojo.PeopleObject
import com.example.roomavaliabilityapp.network.networkobjects.PeopleResponseObject
import javax.inject.Inject

class PeopleResponseObjectMapper @Inject constructor() {

    fun map(listOfPeople: List<PeopleResponseObject>): List<PeopleObject> =
        listOfPeople.map { mapIndividual(it) }

    fun mapIndividual(peopleResponseObject: PeopleResponseObject): PeopleObject {
        return PeopleObject(
            avatar = peopleResponseObject.avatar,
            phone = peopleResponseObject.phone,
            firstName = peopleResponseObject.firstName,
            id = peopleResponseObject.id,
            email = peopleResponseObject.email,
            jobTitle = peopleResponseObject.jobTitle,
            lastName = peopleResponseObject.lastName,
        )
    }
}