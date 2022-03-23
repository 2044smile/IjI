package com.example.iji

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import kotlinx.android.synthetic.main.activity_join_basics.*
import kotlinx.android.synthetic.main.activity_main.*
import org.json.JSONObject
import java.io.BufferedReader
import java.io.InputStreamReader
import java.io.OutputStream
import java.net.HttpURLConnection
import java.net.URL

class JoinActivity : AppCompatActivity() { // 회원가입 페이지 Ex) 구글, 네이버, 카카오, 회원가입

    val second: String = "G, N, K, 사설 회원가입"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_join)

        val btn = findViewById<Button>(R.id.btnJoinBasics) as Button
        btn.setOnClickListener { // 사용자가 버튼을 눌렀을 때 반응하는 이벤트 처리
//            로그인 후 다른 액티비티로 전환
            Log.d(second, "가입 버튼 클릭")
            val intent = Intent(this, JoinBasicsActivity::class.java)
            startActivity(intent)
//            전환 된 액티비티에 데이터 값 전달 ex) Welcome to IjI
//            intent.putExtra("email", "2044smile@naver.com")
//            intent.putExtra("password1", 1234)
//            intent.putExtra("password2", 1234)
        }
    }
}
