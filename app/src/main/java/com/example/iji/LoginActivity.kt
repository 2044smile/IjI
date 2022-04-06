package com.example.iji

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import com.example.iji.retrofit.IRetrofit
import com.example.iji.utils.API
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory

class LoginActivity : AppCompatActivity() {

    lateinit var email: EditText
    lateinit var password: EditText
    lateinit var loginBtn: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        email = findViewById(R.id.login_email)
        password = findViewById(R.id.login_password)
        loginBtn = findViewById(R.id.login_btnLogin)

        val retrofit = Retrofit.Builder()
            .baseUrl("${API.BASE_URL}")
            .addConverterFactory(ScalarsConverterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val services = retrofit.create(IRetrofit::class.java)

        email.setOnClickListener{

        }
        password.setOnClickListener{

        }
        loginBtn.setOnClickListener {
            val emailStr = email.text.toString()
            val passwordStr = password.text.toString()

            services.login(emailStr, passwordStr).enqueue(object:Callback<String> {
                override fun onResponse(call: Call<String>, response: Response<String>) {
                    val result = response.body()
                    Log.d("로그인 성공", "${result}")
                }
                override fun onFailure(call: Call<String>, t: Throwable) {
                    Log.d("로그인 실패", "${t.localizedMessage}")
                    }
                }
            )
        }
    }
}