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
import retrofit2.Callback
import retrofit2.Response


class LoginActivity : AppCompatActivity() {
    // Login 에서 Shared Preferences
    // Shared Preferences 는 간단한 값을 저장할 때 주로 사용한다. 초기 설정 값이나 **자동 로그인 여부** 등 간단한 값을
    // 저장할 때 DB를 사용하면 복잡하기 때문에 Shared Preferences 를 사용하면 적합하다.
    // 로그아웃 할 때 Shared Preferences 데이터를 가져와 세션을 같으 삭제하면 완벽한 로그아웃이 완성?!

    val logLogin: String = "사설 로그인 - 로그인 "
    lateinit var email: EditText
    lateinit var password: EditText
    lateinit var loginBtn: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

//        loadData()

        email = findViewById(R.id.login_email)
        password = findViewById(R.id.login_password)
        loginBtn = findViewById(R.id.login_btnLogin)

        loginBtn.setOnClickListener {
            val email = email.text.toString().trim()
            val password1 = password.text.toString().trim()
            var state: Boolean? = null

            val api = Api.create()
            val data = LoginBackendResponse(code = "", message = "", token = "")

            api.userLogin(data).enqueue(object: Callback<LoginBackendResponse> {
                override fun onResponse(call: retrofit2.Call<LoginBackendResponse>, response: Response<LoginBackendResponse>) {
                    Log.d("Q", "plz ${response.body().toString()}")
//                  plz LoginBackendResponse(code=-1, message=아이디를 찾을 수 없습니다., token=null)

                    var response = response.body().toString()
                    Log.d("Q", "plz ${response.get(index = 0)}")


                    MyApplication.prefs.setString("email", email) // Shared Preferences setString
                    MyApplication.prefs.setString("password", password1)
                    state = true
                }
                override fun onFailure(call: retrofit2.Call<LoginBackendResponse>, t: Throwable) {
                    state = false
                }
            })
            if (state == false){ // state 가 false 라면
                Toast.makeText(this, "로그인에 실패하였습니다.", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            } else {
                val intent = Intent(this, HomeActivity::class.java)
                Log.d(logLogin, "홈 버튼 클릭")
                startActivity(intent)
            }
        }
    }
//
//    private fun loadData() {
//        Log.d(logLogin, "---- 출력 loadData ----")
//        val pref = getSharedPreferences("pref", 0)
//        login_email.setText(pref.getString("email", "")) // email 값이 존재하지 않을 경우 대체 데이터
//        login_password.setText(pref.getString("password", ""))
//    }
//    private fun saveData() {
//        Log.d(logLogin, "---- 출력 saveData ----")
//        val pref = getSharedPreferences("pref", 0)
//        val edit = pref.edit()
//        edit.putString("email", "")
//        edit.putString("password", "")
//        edit.apply()
//        edit.clear()
//        edit.commit()
//    }
//    override fun onDestroy() {
//        super.onDestroy()
//
//        saveData()
//    }
}