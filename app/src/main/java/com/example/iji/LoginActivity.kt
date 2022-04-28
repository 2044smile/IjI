package com.example.iji

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.iji.api.Api
import com.example.iji.api.MyApplication
import com.example.iji.models.LoginBackendResponse
import com.example.iji.models.LoginResponse
import org.json.JSONObject
import retrofit2.Callback
import retrofit2.Response


class LoginActivity : AppCompatActivity() {
    // Login 에서 Shared Preferences
    // Shared Preferences 는 간단한 값을 저장할 때 주로 사용한다. 초기 설정 값이나 **자동 로그인 여부** 등 간단한 값을
    // 저장할 때 DB를 사용하면 복잡하기 때문에 Shared Preferences 를 사용하면 적합하다.
    // 로그아웃 할 때 Shared Preferences 데이터를 가져와 세션을 같으 삭제하면 완벽한 로그아웃이 완성?!

    lateinit var email: EditText
    lateinit var password: EditText
    lateinit var loginBtn: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        email = findViewById(R.id.login_email)
        password = findViewById(R.id.login_password)
        loginBtn = findViewById(R.id.login_btnLogin)

        loginBtn.setOnClickListener {
            val email = email.text.toString().trim()
            val password1 = password.text.toString().trim()
            var state: Boolean?

            val api = Api.create()
            val data = LoginResponse(email, password1)

            api.userLogin(data).enqueue(object: Callback<LoginBackendResponse> {
                override fun onResponse(call: retrofit2.Call<LoginBackendResponse>, response: Response<LoginBackendResponse>) {
                    state = if(response.body()?.code == "200"){
                        MyApplication.prefs.setString("email", email) // Shared Preferences setString
                        MyApplication.prefs.setString("password", password1)

                        val intent = Intent(this@LoginActivity, HomeActivity::class.java)
                        startActivity(intent)

                        Toast.makeText(this@LoginActivity, "로그인에 성공하였습니다.", Toast.LENGTH_SHORT).show()

                        true
                    } else {
                        Toast.makeText(this@LoginActivity, "로그인에 실패하였습니다.", Toast.LENGTH_SHORT).show()

                        false
                    }
                }
                override fun onFailure(call: retrofit2.Call<LoginBackendResponse>, t: Throwable) {
                    false.also { state = it }
                }
            })
        }
    }
}