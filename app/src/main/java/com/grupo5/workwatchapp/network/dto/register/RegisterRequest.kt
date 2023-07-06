package com.grupo5.workwatchapp.network.dto.register

import com.google.gson.annotations.SerializedName

data class RegisterRequest(
    @SerializedName("name") val name: String,
    @SerializedName("lastName") val lastName: String,
    @SerializedName("phone") val phone: String,
    @SerializedName("email") val email: String,
    @SerializedName("company") val company: String,
    @SerializedName("password") val password: String,
    @SerializedName("confirmPassword") val confirmPassword: String,
)