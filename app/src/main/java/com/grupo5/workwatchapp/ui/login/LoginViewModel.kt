package com.grupo5.workwatchapp.ui.login

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.grupo5.workwatchapp.RetrofitApplication
import com.grupo5.workwatchapp.network.login.LoginRequest
import com.grupo5.workwatchapp.network.retrofit.RetrofitInstance
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import retrofit2.HttpException

class LoginViewModel(): ViewModel(){

    private val _email = MutableLiveData<String>()
    private val _password = MutableLiveData<String>()

    val isSuccessLoading = mutableStateOf(value = false)

    private val _loginResult = MutableLiveData<Result<Boolean>>()
    val loginResult: LiveData<Result<Boolean>> = _loginResult

    // so that the variable is modified only inside the view-model to avoid conflicts
    val email : LiveData<String> = _email
    val password : LiveData<String> = _password


    // function that modifies the text-field
    fun onLoginChanged(email: String, password: String){
        _email.value = email
        _password.value = password
    }

    private fun login(email: String, password: String) {

        viewModelScope.launch {
            try {
                val response = RetrofitInstance.getLoginService().login(LoginRequest(email, password))

                if (response.isSuccessful) {
                    _loginResult.postValue(Result.success(true))
                    response.body()?.let { token ->
                        Log.d("Logging", "Response token: $token")
                    }
                } else {
                    response.errorBody()?.let {
                        Log.d("Error", "Error")
                        _loginResult.postValue(Result.failure(Exception("Credentials invalid")))
                    }
                }
            } catch (e: HttpException) {
                Log.d("Error", "Error: $e")
                _loginResult.postValue(Result.failure(Exception("Error login: $e")))
            }
        }

    }

    fun onLogin() {

        login(email.value!!, password.value!!)

    }

    fun isValidate(): Boolean {
        when {
            _email.value.isNullOrEmpty() -> return false
            _password.value.isNullOrEmpty() -> return false
        }
        return true
    }

    companion object {
        val Factory = viewModelFactory {
            initializer {
                this[APPLICATION_KEY] as RetrofitApplication
                LoginViewModel()
            }
        }
    }
}