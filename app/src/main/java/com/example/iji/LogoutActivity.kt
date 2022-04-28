package com.example.iji

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import com.example.iji.api.MyApplication

class LogoutActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_logout)
        val btnCancelLogout = findViewById<Button>(R.id.logout_no) as Button
        btnCancelLogout.setOnClickListener{
            Toast.makeText(this, "로그아웃 취소 되었습니다.", Toast.LENGTH_SHORT).show()

            val intent = Intent(this, HomeActivity::class.java)
            startActivity(intent)
        }
        val btnLogout = findViewById<Button>(R.id.logout_yes) as Button
        btnLogout.setOnClickListener{
            Toast.makeText(this, "로그아웃 되었습니다.", Toast.LENGTH_SHORT).show()

            MyApplication.prefs.edit.remove("email")
            MyApplication.prefs.edit.remove("password")
            MyApplication.prefs.edit.commit() // Shared Preference 삭제되는 것을 확인

            val intent = Intent(this, HomeActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
            startActivity(intent)
        }
    }
}
