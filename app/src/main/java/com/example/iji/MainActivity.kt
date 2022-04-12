package com.example.iji

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.content.Intent
import android.view.View
import android.widget.Button

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btnSignUp = findViewById<Button>(R.id.btnSignUp) as Button // 첫 번째 회원가입을 누르면 JoinActivity
        val btnLogin = findViewById<Button>(R.id.btnLogin) as Button // 로그인을 누르면
        val btnLogout = findViewById<Button>(R.id.btnLogout) as Button

        btnSignUp.setOnClickListener{
            val intent = Intent(this, JoinActivity::class.java)
            startActivity(intent)
        }

        btnLogin.setOnClickListener{
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }

        btnLogout.setOnClickListener{
            val intent = Intent(this, LogoutActivity::class.java)
            startActivity(intent)
        }
    }
}