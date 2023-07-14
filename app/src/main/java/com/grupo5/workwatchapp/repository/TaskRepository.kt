package com.grupo5.workwatchapp.repository

import com.grupo5.workwatchapp.network.service.AuthService


class TaskRepository (private val api: AuthService) {

    suspend fun taskList(token: String) = api.task(" Bearer $token")

}