package com.example.iji

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.util.Patterns
import android.widget.Button
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_login.*
import java.util.regex.Pattern

class LoginActivity : AppCompatActivity() {

    val four: String = "로그인 -"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val btnLogin = findViewById<Button>(R.id.btnLogin) as Button

        btnLogin.setOnClickListener{
            Log.d(four, "버튼 클릭 ")
            val email = login_email.text.toString()
            val password = login_password.text.toString()
            if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                Toast.makeText(this, "이메일 형식이 아닙니다.", Toast.LENGTH_SHORT).show()
                Log.d(four, "Email 실패")
                return@setOnClickListener
            }

            if (!Pattern.matches("^(?=.*\\d)(?=.*[~`!@#$%\\^&*()-])(?=.*[a-zA-Z]).{8,20}$", password)) {
                Toast.makeText(this, "숫자, 문자, 특수문자 중 2가지 포함(8~20자)", Toast.LENGTH_SHORT).show()
                Log.d(four, "패스워드 실패")
                return@setOnClickListener
            }
            // 위에서는 아이디가 이메일 양식이 맞는지, 패스워드가 조건을 지켰는지 확인
            // 아이디, 패스워드를 Node.js로 넣어 맞는지 확인
        }
    }
}