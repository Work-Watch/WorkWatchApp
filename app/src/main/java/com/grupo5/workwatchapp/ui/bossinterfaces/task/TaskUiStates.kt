package com.grupo5.workwatchapp.ui.bossinterfaces.task

import java.lang.Exception

sealed class TaskUiStates {
    object Resume : TaskUiStates()
    class Error(val exception: Exception) : TaskUiStates()
    data class ErrorWithMessage(val message: String) : TaskUiStates()
    data class Success(val message: String) : TaskUiStates()
}
