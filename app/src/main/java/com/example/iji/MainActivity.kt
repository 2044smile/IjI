package com.example.iji

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.content.Intent
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.core.view.isGone
import androidx.core.view.isInvisible
import androidx.core.view.isVisible
import com.example.iji.api.MyApplication
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btnSignUp = findViewById<Button>(R.id.btnSignUp) as Button // 첫 번째 회원가입을 누르면 JoinActivity

        val email = MyApplication.prefs.getString("email", "")
        val password = MyApplication.prefs.getString("password", "")

        if (email == "" && password == ""){
            val btnLogin = findViewById<Button>(R.id.btnLogin) as Button
            btnLogout.visibility = View.GONE
            btnLogin.setOnClickListener{
                val intent = Intent(this, LoginActivity::class.java)
                startActivity(intent)
            }
        } else {
            val btnLogout = findViewById<Button>(R.id.btnLogout) as Button
            btnLogin.visibility = View.GONE
            btnLogout.setOnClickListener{
                val intent = Intent(this, LogoutActivity::class.java)
                startActivity(intent)
            }
        }
        btnSignUp.setOnClickListener{
            val intent = Intent(this, JoinActivity::class.java)
            startActivity(intent)
        }

    }
}