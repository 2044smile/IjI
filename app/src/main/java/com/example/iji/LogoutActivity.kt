package com.example.iji

import android.app.Dialog
import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import kotlinx.android.synthetic.main.activity_join_basics.*
import kotlinx.android.synthetic.main.activity_main.*
import org.json.JSONObject
import java.io.BufferedReader
import java.io.InputStreamReader
import java.io.OutputStream
import java.net.HttpURLConnection
import java.net.URL

class LogoutActivity : AppCompatActivity() { // 로그아웃 페이지

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btn = findViewById<Button>(R.id.btnLogout) as Button
        btn.setOnClickListener{
            var dialog = AlertDialog.Builder(this)
            dialog.setTitle("로그아웃을 하시겠습니까?")
            dialog.setMessage("서비스를 이용해주셔서 감사합니다.")
            // dialog.setIcon 추후에 아이콘 삽입

            fun toast() {
                Toast.makeText(this, "로그아웃 되었습니다.", Toast.LENGTH_SHORT).show()
                val intent = Intent(this, HomeActivity::class.java)
                startActivity(intent)
            }
            var dialogLister = DialogInterface.OnClickListener { p0, p1 ->
                when (p1) {
                    DialogInterface.BUTTON_POSITIVE -> toast()
                }
            }
            dialog.setPositiveButton("YES", dialogLister)
            dialog.setNegativeButton("NO", null)
            dialog.show()
        }
    }
}
