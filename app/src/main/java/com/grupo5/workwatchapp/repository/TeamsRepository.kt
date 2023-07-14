package com.grupo5.workwatchapp.repository

import com.grupo5.workwatchapp.network.service.AuthService

class TeamsRepository(private val api: AuthService) {

    suspend fun getTeamsList(token: String) = api.getTeams("Bearer $token")

}