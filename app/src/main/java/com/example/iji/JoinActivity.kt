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
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_join)

        val btn = findViewById<Button>(R.id.btnJoinBasics) as Button

        btn.setOnClickListener { // 사용자가 버튼을 눌렀을 때 반응하는 이벤트 처리
            val intent = Intent(this, JoinBasicsActivity::class.java)
            intent.putExtra("email", "2044smile@naver.com")
            intent.putExtra("password1", 1234)
            intent.putExtra("password2", 1234)
            startActivity(intent)
        }

    fun getJoinBasics() {
        var th: Thread = object : Thread() {
            override fun run() {
                super.run()
                val json = JSONObject()
                json.put("email", edit_id)
                json.put("password1", edit_password_1)
                json.put("password2", edit_password_2)

                val url =
                    URL("http://ec2-3-35-54-69.ap-northeast-2.compute.amazonaws.com:3000/api/routerTest")
                var conn: HttpURLConnection? = null
                conn = url.openConnection() as HttpURLConnection
                conn.doOutput = true
                conn.requestMethod = "POST"
                conn.setRequestProperty("Connection", "Keep-Alive")
                conn.setRequestProperty("Content-Type", "application/json")

                val jsonStr = json.toString() // json을 string으로 변환 후 서버로 전송
                val os: OutputStream = conn.outputStream
                os.write(jsonStr.toByteArray(charset("UTF-8")))
                os.flush()

                val sb = StringBuilder()
                val httpResult = conn.responseCode
                if (httpResult == HttpURLConnection.HTTP_OK) {
                    val br = BufferedReader(
                        InputStreamReader(conn.inputStream, "utf-8")
                    )

                    br.close()
                    println("" + sb.toString())
                } else
                    println(conn.responseMessage)
                os.close()
                Log.d("json", "" + jsonStr)
            }
        }
        th.start()
    }
}
}
