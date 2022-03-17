package com.example.iji

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class JoinActivity : AppCompatActivity() { // 회원가입 페이지 Ex) 구글, 네이버, 카카오, 회원가입
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_join)

        val btn = findViewById<Button>(R.id.btnJoinBasics) as Button

        btn.setOnClickListener{
            val intent = Intent(this, JoinBasicsActivity::class.java)
            // putExtra 보따리에 넣는다.
            intent.putExtra("email", "2044smile@naver.com")
            intent.putExtra("password1", 1234)
            intent.putExtra("password2", 1234)
            startActivity(intent)
        }
    }
}