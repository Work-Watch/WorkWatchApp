package com.grupo5.workwatchapp.ui.bossinterfaces.teams

import java.lang.Exception

sealed class TeamsUiStates{
    object Resume: TeamsUiStates()

    class Error(val exception: Exception): TeamsUiStates()

    data class ErrorWithMessage(val message: String): TeamsUiStates()

    data class Success(val message: String): TeamsUiStates()
}
