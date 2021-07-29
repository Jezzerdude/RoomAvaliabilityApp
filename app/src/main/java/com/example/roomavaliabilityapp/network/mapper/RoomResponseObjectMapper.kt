package com.example.roomavaliabilityapp.network.mapper

import com.example.roomavaliabilityapp.data.pojo.RoomObject
import com.example.roomavaliabilityapp.network.networkobjects.RoomResponseObject
import javax.inject.Inject

class RoomResponseObjectMapper @Inject constructor() {
    fun map(listOfRooms: List<RoomResponseObject>): List<RoomObject> =
        listOfRooms.map { mapIndividual(it) }

    fun mapIndividual(roomResponseObject: RoomResponseObject): RoomObject {
        return RoomObject(
            id = roomResponseObject.id,
            createdAt = roomResponseObject.createdAt ?: "",
            name = roomResponseObject.name,
            maxOccupancy = roomResponseObject.maxOccupancy,
            isOccupied = roomResponseObject.isOccupied
        )
    }
}