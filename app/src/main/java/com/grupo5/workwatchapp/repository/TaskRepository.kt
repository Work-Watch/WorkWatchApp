package com.grupo5.workwatchapp.repository

import android.util.Log
import com.grupo5.workwatchapp.network.ApiResponse
import com.grupo5.workwatchapp.network.dto.login.LoginRequest
import com.grupo5.workwatchapp.network.service.AuthService
import retrofit2.HttpException
import java.io.IOException

class TaskRepository (private val api: AuthService) {

    suspend fun task(token: String): ApiResponse<String> {
        try {
            val response = api.task(" Bearer $token")
            return ApiResponse.Success(response.body()?.toString() ?: "")

        }catch (e: HttpException){
            if (e.code() == 400)
                return ApiResponse.ErrorWithMessage("Error 400")

            return ApiResponse.Error(e)
        }catch (e: IOException){
            return ApiResponse.Error(e)
        }
    }
}