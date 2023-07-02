package com.grupo5.workwatchapp.ui.login

sealed class ScreenLogin(val route: String) {
    object Home : ScreenLogin(route = "login_screen")
    object Boss : ScreenLogin(route = "boss_screen")
}
