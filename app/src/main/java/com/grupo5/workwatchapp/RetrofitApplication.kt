package com.grupo5.workwatchapp

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import com.grupo5.workwatchapp.network.repository.AuthRepository
import com.grupo5.workwatchapp.network.retrofit.RetrofitInstance

class RetrofitApplication: Application() {

    // store and retrieve data
    private val prefs: SharedPreferences by lazy {
        getSharedPreferences("Retrofit", Context.MODE_PRIVATE)
    }

    private fun getApiService() = with(RetrofitInstance){
        setToken(getToken())
        getLoginService() //RetrofitInstance
    }

    fun getToken(): String = prefs.getString(USER_TOKEN, "")!!

    val authRepository: AuthRepository by lazy {
        AuthRepository(getApiService())
    }

    fun saveAuthToken(token: String) {
        val editor = prefs.edit()
        editor.putString(USER_TOKEN, token)
        editor.apply()
    }

    companion object{
        const val USER_TOKEN =  "user_token"
    }
}