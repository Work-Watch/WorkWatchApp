package com.grupo5.workwatchapp.network.dto.teams

import com.grupo5.workwatchapp.network.dto.task.TaskRequest

data class TeamsListResponse(
    val body: List<TeamRequest>
)
