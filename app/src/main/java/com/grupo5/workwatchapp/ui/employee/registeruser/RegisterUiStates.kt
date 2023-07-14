package com.grupo5.workwatchapp.ui.employee.registeruser

import java.lang.Exception

sealed class RegisterUiStates {
    object Resume : RegisterUiStates()
    class Error(val exception: Exception) : RegisterUiStates()
    data class ErrorWithMessage(val message: String) : RegisterUiStates()
    data class Success(val token: String) : RegisterUiStates()
}