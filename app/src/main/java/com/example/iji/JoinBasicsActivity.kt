package com.example.iji

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.Toast
import com.example.iji.retrofit.RetrofitManager
import com.example.iji.utils.Constants.TAG
import com.example.iji.utils.RESPONSE_STATE
import kotlinx.android.synthetic.main.activity_join.*
import kotlinx.android.synthetic.main.activity_join_basics.*
import org.json.JSONObject
import java.io.BufferedReader
import java.io.InputStreamReader
import java.io.OutputStream
import java.net.HttpURLConnection
import java.net.URL


class JoinBasicsActivity : AppCompatActivity() {

    val third: String = "사설 가입 - 회원가입"
    var isExistBlank = false
    var isPWSame = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_join_basics)

        val btnJoinMembership = findViewById<Button>(R.id.btnLogin_S) as Button

        btnJoinMembership.setOnClickListener {
            Log.d(third, "버튼 클릭 ")
            var json = JSONObject()
            val email = edit_id.text.toString()
            val password1 = edit_password_1.text.toString()
            val password2 = edit_password_2.text.toString()

            if (email.isEmpty() || password1.isEmpty() || password2.isEmpty()) {
                isExistBlank = true
                Log.d(third, "이메일, 패스워드1, 패스워드2 실패")
            } else {
                if(password1 == password2){
                    isPWSame = true
                } else {
                    Log.d(third, "조건문 패스워드 비교 실패")
                }
            }

            if(!isExistBlank && isPWSame) {
                Log.d(third, "메인 진입")

                // 백엔드와 통신이 이루어져야한다.
                // 사전적으로 email 과 P1 P2는 검사를 했기에
                RetrofitManager.instance.apiTest(term = null, completion = {
                    responseState, responseBody ->

                    when(responseState) {
                        RESPONSE_STATE.OKAY -> {
                            Log.d(TAG, "API 호출 성공 : $responseBody")
                        }
                        RESPONSE_STATE.FAIL -> {
                            // Toast 임시로 띄워준다 LENGTH_SHORT를 하면 짧게 화면에 노출
                            Toast.makeText(this, "API 호출 에러입니다.", Toast.LENGTH_SHORT).show()
                            Log.d(TAG, "API 호출 실패 : $responseBody")
                        }
                    }
                })

                // 홈 화면으로 이동한다.
                val intent = Intent(this, HomeActivity::class.java)
                Log.d(third, "홈 버튼 클릭")
                startActivity(intent)
            }
        }
    }
}