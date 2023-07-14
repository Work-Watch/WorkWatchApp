package com.grupo5.workwatchapp.network.dto.teams

import com.google.gson.annotations.SerializedName

data class TeamResponse(
    @SerializedName("message") val message: String
)
