package com.example.iji.models

data class DataResponse(val email: String, val password: String)

data class LogoutResponse(val email: String, val password: String, val state: Boolean)

data class LoginResponse(val email: String, val password: String)

data class SignUpResponse(val email: String, val password: String)

