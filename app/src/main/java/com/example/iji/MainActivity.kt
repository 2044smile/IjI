package com.example.iji

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.content.Intent
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Button
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.option_menu, menu)

        menu?.add(Menu.NONE, Menu.FIRST + 1, Menu.NONE, "LOGIN")
        menu?.add(Menu.NONE, Menu.FIRST + 2, Menu.NONE, "LOGOUT")

        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.menu_login ->
                btnLogin.text = "LOGIN"
            R.id.menu_logout ->
                btnLogout.text = "LOGOUT"

            Menu.FIRST + 1 -> btnLogin.text = "LOGIN CLICK"
            Menu.FIRST + 2 -> btnLogout.text = "LOGOUT CLICK"
        }

        return super.onOptionsItemSelected(item)
    }
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