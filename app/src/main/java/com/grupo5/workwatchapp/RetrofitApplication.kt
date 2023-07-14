package com.grupo5.workwatchapp

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import com.grupo5.workwatchapp.network.retrofit.RetrofitInstance
import com.grupo5.workwatchapp.repository.AuthRepository
import com.grupo5.workwatchapp.repository.TaskRepository
import com.grupo5.workwatchapp.repository.TeamsRepository

class RetrofitApplication: Application() {

    private val prefs: SharedPreferences by lazy {
        getSharedPreferences("Retrofit", Context.MODE_PRIVATE)
    }

    private fun getAPIService() = with(RetrofitInstance) {
        setToken(getToken())
        getLoginService()
    }

    fun getToken(): String = prefs.getString(USER_TOKEN, "")!!

    val authRepository: AuthRepository by lazy {
        AuthRepository(getAPIService())
    }

    val taskRepository: TaskRepository by lazy {
        TaskRepository(getAPIService())
    }

    val teamsRepository: TeamsRepository by lazy {
        TeamsRepository(getAPIService())
    }

    fun saveAuthToken(token: String) {
        val editor = prefs.edit()
        editor.putString(USER_TOKEN, token)
        editor.apply()
    }

    companion object {
        const val USER_TOKEN = "user_token"
    }
}