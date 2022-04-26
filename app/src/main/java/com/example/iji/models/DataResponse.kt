package com.example.iji.models

import com.google.gson.annotations.SerializedName

data class DataResponse(val email: String, val password: String)

data class LogoutResponse(val email: String, val password: String, val state: Boolean)

data class LoginResponse(val email: String, val password: String)
data class LoginBackendResponse(
    val code: String,
    val message: String,
    val token: String? = null
    )

data class SignUpResponse(val email: String, val password: String)

