package com.grupo5.workwatchapp.network.repository

import com.grupo5.workwatchapp.network.ApiResponse
import com.grupo5.workwatchapp.network.dto.login.LoginRequest
import com.grupo5.workwatchapp.network.service.AuthService
import retrofit2.HttpException
import java.io.IOException

class AuthRepository (private val api: AuthService) {

    suspend fun login(email: String, password: String): ApiResponse<String>{
        try {

            val response = api.login(LoginRequest(email, password))
            return ApiResponse.Sucess(response.token)

        }catch (e: HttpException){
            if(e.code() == 400)
                return ApiResponse.ErrorWithMessage("invalid credentials")

            return ApiResponse.Error(e)
        }catch (e: IOException){
            return ApiResponse.Error(e)
        }
    }

}