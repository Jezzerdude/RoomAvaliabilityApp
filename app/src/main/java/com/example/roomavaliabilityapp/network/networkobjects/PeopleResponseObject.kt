package com.example.roomavaliabilityapp.network.networkobjects

import com.google.gson.annotations.SerializedName

data class PeopleResponseObject(
    @SerializedName("avatar")
    val avatar: String,
    @SerializedName("phone")
    val phone: String,
    @SerializedName("firstName")
    val firstName: String,
    @SerializedName("id")
    val id: Long,
    @SerializedName("email")
    val email: String,
    @SerializedName("jobTitle")
    val jobTitle: String,
    @SerializedName("lastName")
    val lastName: String
)