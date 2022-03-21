package com.example.iji

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import kotlinx.android.synthetic.main.activity_join.*
import kotlinx.android.synthetic.main.activity_join_basics.*
import org.json.JSONObject
import java.io.BufferedReader
import java.io.InputStreamReader
import java.io.OutputStream
import java.net.HttpURLConnection
import java.net.URL

class JoinBasicsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_join_basics)

        val btnJoinMembership = findViewById<Button>(R.id.btnLogin_S) as Button

        btnJoinMembership.setOnClickListener{
            val intent = Intent(this, HomeActivity::class.java)
                startActivity(intent)
            }
        }
    }
//
//    private fun getJoinBasics() {
//
//        var th: Thread = object : Thread() {
//            override fun run() {
//                super.run()
//                val json = JSONObject()
//                json.put("email", edit_id)
//                json.put("password1", edit_password_1)
//                json.put("password2", edit_password_2)
//
//                val url = URL("http://ec2-3-35-54-69.ap-northeast-2.compute.amazonaws.com:3000/api/routerTest")
//                var conn: HttpURLConnection? = null
//                conn = url.openConnection() as HttpURLConnection
//                conn.doOutput = true
//                conn.requestMethod = "POST"
//                conn.setRequestProperty("Connection", "Keep-Alive")
//                conn.setRequestProperty("Content-Type", "application/json")
//
//                val jsonStr = json.toString() // json을 string으로 변환 후 서버로 전송
//                val os: OutputStream = conn.outputStream
//                os.write(jsonStr.toByteArray(charset("UTF-8")))
//                os.flush()
//
//                val sb = StringBuilder()
//                val httpResult = conn.responseCode
//                if (httpResult == HttpURLConnection.HTTP_OK) {
//                    val br = BufferedReader(
//                        InputStreamReader(conn.inputStream, "utf-8")
//                    )
//
//                    br.close()
//                    println("" + sb.toString())
//                } else
//                    println(conn.responseMessage)
//                os.close()
//                Log.d("json", "" + jsonStr)
//            }
//        }
//    th.start()
//    }
//  }
// }