package com.grupo5.workwatchapp.network.dto.teams

import com.google.gson.annotations.SerializedName

data class TeamRequest(
    @SerializedName("name") val name: String
)
