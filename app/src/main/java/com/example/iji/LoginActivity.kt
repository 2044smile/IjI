package com.example.iji

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.iji.api.Api
import com.example.iji.models.LoginResponse
import retrofit2.Callback
import retrofit2.Response


class LoginActivity : AppCompatActivity() {

    val logLogin: String = "사설 로그인 - 로그인 "
    lateinit var email: EditText
    lateinit var password: EditText
    lateinit var loginBtn: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        email = findViewById(R.id.login_email)
        password = findViewById(R.id.login_password)
        loginBtn = findViewById(R.id.login_btnLogin)

        loginBtn.setOnClickListener {
            val email = email.text.toString().trim()
            val password1 = password.text.toString().trim()
            var state: Boolean? = null

            val api = Api.create()
            val data = LoginResponse(email, password1)
            Log.d(logLogin, "---- ${state} 출력 ----")
            api.userLogin(data).enqueue(object: Callback<LoginResponse> {
                override fun onResponse(call: retrofit2.Call<LoginResponse>, response: Response<LoginResponse>) {
                    state = true
                    Log.d(logLogin, "Success")
                    Log.d(logLogin, "- ${state} - ")
                }
                override fun onFailure(call: retrofit2.Call<LoginResponse>, t: Throwable) {
                    state = false
                    Log.d(logLogin, "Failed")
                    Log.d(logLogin, "-- ${state} -- ")
                }
            })
            Log.d(logLogin, "--- ${state} 출력 ---")
            if (state == false){ // state 가 false 라면
                Toast.makeText(this, "로그인에 실패하였습니다.", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            } else {
                val intent = Intent(this, HomeActivity::class.java)
                Log.d(logLogin, "홈 버튼 클릭")
                startActivity(intent)
            }
        }
    }
}