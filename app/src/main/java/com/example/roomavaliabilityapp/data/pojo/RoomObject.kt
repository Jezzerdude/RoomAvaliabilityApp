package com.example.roomavaliabilityapp.data.pojo

data class RoomObject(
    val id: String,
    val createdAt: String,
    val name: String,
    val maxOccupancy: Int,
    val isOccupied: Boolean
)