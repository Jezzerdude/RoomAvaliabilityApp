package com.example.roomavaliabilityapp.network.networkobjects

import com.google.gson.annotations.SerializedName

data class RoomResponseObject(
    @SerializedName("id")
    val id: String,
    @SerializedName("created_at")
    val createdAt: String?,
    @SerializedName("name")
    val name: String,
    @SerializedName("max_occupancy")
    val maxOccupancy: Int,
    @SerializedName("is_occupied")
    val isOccupied: Boolean
)