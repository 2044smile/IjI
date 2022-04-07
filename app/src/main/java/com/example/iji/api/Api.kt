package com.example.iji.api

import com.example.iji.models.DefaultResponse
import com.example.iji.models.LoginResponse
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface Api {
    @FormUrlEncoded
    @POST("/api/auth/signup/")
    fun createUser(
        @Field("email") email:String,
        @Field("password") password1:String
    ): Call<DefaultResponse>

    @FormUrlEncoded
    @POST("/api/auth/signin")
    fun userLogin(
        @Field("email") email:String,
        @Field("password") password:String
    ): Call<LoginResponse>
}