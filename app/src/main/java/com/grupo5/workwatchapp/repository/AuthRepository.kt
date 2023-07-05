package com.grupo5.workwatchapp.repository

import android.util.Log
import com.grupo5.workwatchapp.network.ApiResponse
import com.grupo5.workwatchapp.network.dto.login.LoginRequest
import com.grupo5.workwatchapp.network.service.AuthService
import retrofit2.HttpException
import java.io.IOException

class AuthRepository(private val api: AuthService){

    suspend fun login(email: String, password: String): ApiResponse<String> {
        try {

            val response = api.login(LoginRequest(email, password))
            Log.d("TOKEN", "Token=${response.token}")
            return ApiResponse.Success(response.token)

        }catch (e: HttpException){
            if (e .code() == 400)
                return ApiResponse.ErrorWithMessage("Invalid email or password")

            return ApiResponse.Error(e)
        }catch (e: IOException){
            return ApiResponse.Error(e)
        }
    }
}