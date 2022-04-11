package com.example.iji

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.telecom.Call
import android.util.Log
import android.util.Patterns
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.iji.api.Api
import com.example.iji.models.LoginResponse
import com.example.iji.models.SignUpResponse
import retrofit2.Callback
import retrofit2.Response
import java.util.regex.Pattern


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

            val api = Api.create()
            val data = LoginResponse(email, password1)
            api.userLogin(data).enqueue(object: Callback<LoginResponse> {
                override fun onResponse(
                    call: retrofit2.Call<LoginResponse>,
                    response: Response<LoginResponse>
                ) {
                    Log.d(logLogin, "Success")
                }

                override fun onFailure(call: retrofit2.Call<LoginResponse>, t: Throwable) {
                    Log.d(logLogin, "Failed")
                }

            })
            val intent = Intent(this, HomeActivity::class.java)
            Log.d(logLogin, "홈 버튼 클릭")
            startActivity(intent)
        }
    }
}