package com.grupo5.workwatchapp.ui.employee.registeruser

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.grupo5.workwatchapp.RetrofitApplication
import com.grupo5.workwatchapp.network.ApiResponse
import com.grupo5.workwatchapp.repository.AuthRepository
import com.grupo5.workwatchapp.ui.login.LoginUiStatus
import com.grupo5.workwatchapp.ui.login.LoginViewModel
import kotlinx.coroutines.launch

class RegisterViewModel(private val repository: AuthRepository): ViewModel() {

    private val _name = MutableLiveData<String>()
    private val _lastName = MutableLiveData<String>()
    private val _phone = MutableLiveData<String>()
    private val _email = MutableLiveData<String>()
    private val _company = MutableLiveData<String>()
    private val _password = MutableLiveData<String>()
    private val _confirmPassword = MutableLiveData<String>()

    val name : LiveData<String> = _name
    val lastName : LiveData<String> = _lastName
    val phone : LiveData<String> = _phone
    val email : LiveData<String> = _email
    val company : LiveData<String> = _company
    val password : LiveData<String> = _password
    val confirmPassword : LiveData<String> = _confirmPassword

    private val _status = MutableLiveData<RegisterUiStates>(RegisterUiStates.Resume)

    val status: MutableLiveData<RegisterUiStates>
        get() = _status

    fun onRegisterChanged(
        name: String, lastName: String, phone: String, email: String, company: String, password: String, confirmPassword: String
    ){
        _name.value = name
        _lastName.value = lastName
        _phone.value = phone
        _email.value = email
        _company.value = company
        _password.value = password
        _confirmPassword.value = confirmPassword
    }

    private fun register(
        name: String, lastName: String, phone: String, email: String, company: String, password: String, confirmPassword: String
    ) {

        viewModelScope.launch {
            _status.postValue(
                when ( val response = repository.register(name, lastName, phone, email, company, password, confirmPassword)) {
                    is ApiResponse.Error -> RegisterUiStates.Error(response.exception)
                    is ApiResponse.ErrorWithMessage -> RegisterUiStates.ErrorWithMessage(response.message)
                    is ApiResponse.Success -> RegisterUiStates.Success(response.data)
                }
            )
        }
    }

    companion object {
        val Factory = viewModelFactory {
            initializer {
                val app = this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as RetrofitApplication
                LoginViewModel(app.authRepository)
            }
        }
    }

}