package com.example.iji

import android.app.DatePickerDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import kotlinx.android.synthetic.main.activity_join_basics.*
import android.util.Patterns
import android.view.View
import android.widget.*
import com.example.iji.api.RetrofitClient
import com.example.iji.models.DefaultResponse
import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.*
import java.util.regex.Pattern


class JoinBasicsActivity : AppCompatActivity() {

    val third: String = "사설 가입 - 회원가입"
    var isExistBlank = false
    var isPWSame = false
    var dateString = ""
    lateinit var email: TextView
    lateinit var password: TextView
    lateinit var joinBtn: Button

    fun onRadioButtonClicked(view: View) {

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_join_basics)
        email = findViewById(R.id.edit_email)
        password = findViewById(R.id.edit_password_1)
        joinBtn = findViewById(R.id.join_btnJoin)
        val btnDateOfBirth = findViewById<TextView>(R.id.edit_dateOfBirth) as TextView
        val btnJoinMembership = findViewById<Button>(R.id.join_btnJoin) as Button

        btnDateOfBirth.setOnClickListener { // 생년월일
            val cal = Calendar.getInstance()
            val dateSetListener =
                DatePickerDialog.OnDateSetListener { view, year, month, dayOfMonth ->
                    dateString = "${year}년 ${month + 1}월 ${dayOfMonth}일"
                    edit_dateOfBirth.text = " " + dateString
                }
            DatePickerDialog(
                this, dateSetListener, cal.get(Calendar.YEAR),
                cal.get(Calendar.MONTH), cal.get(Calendar.DAY_OF_MONTH)
            ).show()
        }

        btnJoinMembership.setOnClickListener { // 회원가입 버튼 클릭
            Log.d(third, "버튼 클릭 ")
            val email = edit_email.text.toString().trim()
            val password1 = edit_password_1.text.toString().trim()
            val password2 = edit_password_2.text.toString().trim()

            if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                Toast.makeText(this, "이메일 형식이 아닙니다.", Toast.LENGTH_SHORT).show()
                Log.d(third, "Email 실패")
                return@setOnClickListener
            }

            if (!Pattern.matches(
                    "^(?=.*\\d)(?=.*[~`!@#$%\\^&*()-])(?=.*[a-zA-Z]).{8,20}$",
                    password1
                )
            ) {
                Toast.makeText(this, "숫자, 문자, 특수문자 중 2가지 포함(8~20자)", Toast.LENGTH_SHORT).show()
                Log.d(third, "패스워드1 실패")
                return@setOnClickListener
            }

            if (!Pattern.matches(
                    "^(?=.*\\d)(?=.*[~`!@#$%\\^&*()-])(?=.*[a-zA-Z]).{8,20}$",
                    password2
                )
            ) {
                Toast.makeText(this, "숫자, 문자, 특수문자 중 2가지 포함(8~20자)", Toast.LENGTH_SHORT).show()
                Log.d(third, "패스워드2 실패")
                return@setOnClickListener
            }

            if (email.isEmpty() || password1.isEmpty() || password2.isEmpty()) {
                isExistBlank = true // email, password1, 2 가 비어있는게 없다면 isExistBlank 는 false
                Log.d(third, "이메일, 패스워드1, 패스워드2 실패")
                return@setOnClickListener
            } else {
                if (password1 == password2) {
                    isPWSame = true
                } else {
                    Log.d(third, "패스워드 비교 실패")
                    return@setOnClickListener
                }
            }

            if (!isExistBlank && isPWSame) {
                Log.d(third, "메인 진입")

                RetrofitClient.instance.createUser(email, password1)
                    .enqueue(object: Callback<DefaultResponse> {
                        override fun onResponse(
                            call: Call<DefaultResponse>,
                            response: Response<DefaultResponse>
                        ) {
//                            if (!response.body()?.error!!){
//                                Log.d(third, "error!!!")
//                            } else {
                            Log.d(third, "onResponse 성공")
                            Log.d(third, response.message())
                            Log.d(third, response.body().toString())
                            Log.d(third, response.raw().toString())
//                            }
                        }

                        override fun onFailure(call: Call<DefaultResponse>, t: Throwable) {
                            Log.d(third, "onFailure 실패")
                            Log.d(third, "${t.localizedMessage}, ${t.message}")
                        }
                    })

                val intent = Intent(this, HomeActivity::class.java)
                Log.d(third, "홈 버튼 클릭")
                startActivity(intent)
            }
        }
    }
}