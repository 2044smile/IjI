package com.example.iji.retrofit

import com.example.iji.utils.API
import com.google.gson.JsonElement
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface IRetrofit {

    // @GET("api/routerTest") 내가 사용해야 될 것

    // https://www.naver.com/search/photos/?query=""
    @POST(API.TEST)
    fun apiTest(@Query("query") term: String) : Call<JsonElement>
}