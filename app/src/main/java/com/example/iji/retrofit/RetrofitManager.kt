package com.example.iji.retrofit

import android.util.Log
import retrofit2.Retrofit
import com.example.iji.utils.API
import com.example.iji.retrofit.IRetrofit
import com.example.iji.utils.Constants.TAG
import com.example.iji.utils.RESPONSE_STATE
import com.google.gson.JsonElement
import retrofit2.Call
import retrofit2.Response

class RetrofitManager {

    companion object { // 싱글턴이 적용되도록
        val instance = RetrofitManager() // RetrofitManager의 인스턴스만 가져온다.
    }
    // http 콜 만들기
    // 레트로핏 인터페이스 가져오기
    private val iRetrofit : IRetrofit? = RetrofitClient.getClient(API.BASE_URL)?.create(IRetrofit::class.java)

    // apiTest 호출
    fun apiTest(term: String?, completion:(RESPONSE_STATE, String) -> Unit) {
        val t = term.let {
            it
        }?: ""
    // val t = term ?: ""
        // val call = iRetrofit?.searchPhotos(searchTerm = term).let {
        //      it
        // }?: return
        val call: Call<JsonElement> = iRetrofit?.apiTest(term=t) ?: return

        call.enqueue(object: retrofit2.Callback<JsonElement>{
            // 응답 실패 시
            override fun onFailure(call: Call<JsonElement>, t: Throwable) {
                Log.d(TAG, "RetrofitManager - onFailure() called / t: $t")

                completion(RESPONSE_STATE.FAIL, t.toString())
            }
            // 응답 성공 시
            override fun onResponse(call: Call<JsonElement>, response: Response<JsonElement>) {
                Log.d(TAG, "RetrofitManager - onResponse() called / response ${response.raw()}")

                completion(RESPONSE_STATE.OKAY, response.raw().toString())
            }

        })



    }
}