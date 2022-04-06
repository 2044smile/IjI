package com.example.iji.retrofit

import android.widget.EditText
import com.example.iji.utils.API
import com.google.gson.JsonElement
import retrofit2.Call
import retrofit2.http.*

interface IRetrofit { // API

    @FormUrlEncoded
    @POST("/api/auth/signin/")
    fun login(@Field("email") email: String, @Field("password") password: String): Call<String>
    // 파라미터 통신, JSON 통신이 가능하다.
}