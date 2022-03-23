package com.example.iji.retrofit

import android.content.ContentValues.TAG
import android.util.Log
import com.example.iji.utils.API
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

// 코틀린에서 object는 싱글턴이다.
// 메모리를 한 개만 쓴다.
object RetrofitClient {
    // 레트로핏 클라이언트 선언
    private var retrofitClient: Retrofit? = null
    // private lateinit var retrofitClient: Retrofit
    // 레트로핏 클라이언트 가져오기
    fun getClient(baseUrl: String): Retrofit? {
        Log.d(TAG, "RetrofitClient - getClient() called")

        if(retrofitClient == null) {
            // 레트로핏 빌더를 통해 인스턴스 생성
            retrofitClient = Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build() // 객체가 생성 된다.
        }
        return retrofitClient
    }
}