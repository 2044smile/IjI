package com.example.iji.api

import com.example.iji.models.LoginBackendResponse
import com.example.iji.models.LoginResponse
import com.example.iji.models.SignUpResponse
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.*

interface Api {
    // 내용 정리
    // Retrofit, 송신 시 우리는 값을 디코딩한 상태로 보였지만, OkHttpClient 에서는 인코딩 된 상태로 확인되었고,
    // 그게 백엔드로 전송이되면 당연히 오류가 발생했고, 인코딩 문제를 해결하려고 하였음
    // 최종 정리
    // postman 으로도 원하는 값을 넣어 보내도 오류가 발생하는 문제 확인완료.
    // OkHttpClient 는 @FormUrlEncoded 로 인코딩이 된 걸로 확인(확실하지 않음) 그래서 백엔드에서 인코딩 된 걸 디코딩하여
    // 받아야 되는 걸로 확인 - help 조이스
//    @FormUrlEncoded
//    @POST("/api/auth/signup/")
//    fun createUser(
//        @Field("email") email:String,
//        @Field("password") password1:String
//    ): Call<SignUpResponse>
    @Headers("content-type: application/json")
    @POST("/api/auth/signup/")
    fun createUser(
        @Body jsonParams:SignUpResponse,
    ): Call<SignUpResponse>

    @Headers("content-type: application/json")
    @POST("/api/auth/signin")
    fun userLogin(
        @Body jsonParams: LoginResponse,
    ): Call<LoginBackendResponse>

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