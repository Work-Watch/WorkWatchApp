package com.grupo5.workwatchapp.network.service

import com.grupo5.workwatchapp.network.dto.login.LoginRequest
import com.grupo5.workwatchapp.network.dto.login.LoginResponse
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthService {

    @POST("auth/login")
    suspend fun login (@Body credentials: LoginRequest) : LoginResponse

}