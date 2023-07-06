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
import com.grupo5.workwatchapp.network.ApiResponse
import com.grupo5.workwatchapp.network.dto.login.LoginRequest
import com.grupo5.workwatchapp.network.retrofit.RetrofitInstance
import com.grupo5.workwatchapp.repository.AuthRepository
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import retrofit2.HttpException

class LoginViewModel(private val repository: AuthRepository): ViewModel(){

    private val _email = MutableLiveData<String>()
    private val _password = MutableLiveData<String>()

    val email : LiveData<String> = _email
    val password : LiveData<String> = _password

    private val _status = MutableLiveData<LoginUiStatus>(LoginUiStatus.Resume)

    val status: MutableLiveData<LoginUiStatus>
        get() = _status

    // so that the variable is modified only inside the view-model to avoid conflicts

    fun onLoginChanged(email: String, password: String){
        _email.value = email
        _password.value = password
    }

    private fun login(email: String, password: String) {

        viewModelScope.launch {
            _status.postValue(
                when ( val response = repository.login(email, password)) {
                    is ApiResponse.Error -> LoginUiStatus.Error(response.exception)
                    is ApiResponse.ErrorWithMessage -> LoginUiStatus.ErrorWithMessage(response.message)
                    is ApiResponse.Success -> LoginUiStatus.Success(response.data)
                }
            )
        }
    }

    fun onLogin() {

        login(email.value!!, password.value!!)

    }

    fun validateData(): Boolean {
        when {
            email.value.isNullOrEmpty() -> return false
            password.value.isNullOrEmpty() -> return false
        }
        return true
    }

    fun clearStatus() {
        _status.value = LoginUiStatus.Resume
    }

    companion object {
        val Factory = viewModelFactory {
            initializer {
                val app = this[APPLICATION_KEY] as RetrofitApplication
                LoginViewModel(app.authRepository)
            }
        }
    }
}