package com.example.iji.api

import com.example.iji.models.LoginResponse
import com.example.iji.models.SignUpResponse
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface Api {
    @FormUrlEncoded
    @POST("/api/auth/signup/")
    fun createUser(
        @Field("email") email:String,
        @Field("password") password1:String
    ): Call<SignUpResponse>

    @FormUrlEncoded
    @POST("/api/auth/signin")
    fun userLogin(
        @Field("email") email:String,
        @Field("password") password:String
    ): Call<LoginResponse>

    companion object {
        private const val BASE_URL = "http://54.180.71.162:4000"

        fun create(): Api {
            val httpLoggingInterceptor = HttpLoggingInterceptor()
            httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY

            val headerInterceptor = Interceptor {
                val request = it.request()
                    .newBuilder()
                    .addHeader("Content-Type", "application/json; charset=utf-8")
                    .build()
                return@Interceptor it.proceed(request)
            }

            val client = OkHttpClient.Builder()
                .addInterceptor(headerInterceptor)
                .addInterceptor(httpLoggingInterceptor)
                .addNetworkInterceptor(headerInterceptor)
                .build()

            return Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(Api::class.java)
        }
    }
}