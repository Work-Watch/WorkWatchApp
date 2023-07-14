package com.grupo5.workwatchapp.network.service

import com.grupo5.workwatchapp.network.dto.login.LoginRequest
import com.grupo5.workwatchapp.network.dto.login.LoginResponse
import com.grupo5.workwatchapp.network.dto.register.RegisterRequest
import com.grupo5.workwatchapp.network.dto.register.RegisterResponse
import com.grupo5.workwatchapp.network.dto.task.TaskListResponse
import com.grupo5.workwatchapp.network.dto.teams.TeamResponse
import com.grupo5.workwatchapp.network.dto.teams.TeamsListResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST

interface AuthService {

    @POST("auth/login")
    suspend fun login(@Body credentials: LoginRequest): LoginResponse

    @POST("auth/register")
    suspend fun register(@Body credentials: RegisterRequest): RegisterResponse

    @GET("task/")
    suspend fun task(@Header("Authorization") token: String): Response<TaskListResponse>

    @GET("team/")
    suspend fun getTeams(@Header("Authorization") token: String): Response<TeamsListResponse>

}