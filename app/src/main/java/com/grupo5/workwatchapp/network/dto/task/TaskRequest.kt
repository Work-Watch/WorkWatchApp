package com.grupo5.workwatchapp.network.dto.task

import com.google.gson.annotations.SerializedName

data class TaskRequest(
    @SerializedName("task") val task: String,
    @SerializedName("latitude") val latitude: Double,
    @SerializedName("longitude") val longitude: Double,
    @SerializedName("hourStart") val hourStart: String,
    @SerializedName("hourFinal") val hourFinal: String?,
    @SerializedName("date") val date: String,
    @SerializedName("idTeam") val idTeam: Int,
)
