package com.example.iji

import android.app.DatePickerDialog
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
import android.util.Patterns
import android.widget.TextView
import java.util.*
import java.util.regex.Pattern


class JoinBasicsActivity : AppCompatActivity() {

    val third: String = "사설 가입 - 회원가입"
    var isExistBlank = false
    var isPWSame = false
    var dateString = ""
    var timeString = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_join_basics)
        val btnDateOfBirth = findViewById<TextView>(R.id.edit_dateOfBirth) as TextView
        val btnJoinMembership = findViewById<Button>(R.id.btnLogin_S) as Button

        btnDateOfBirth.setOnClickListener {
            val cal = Calendar.getInstance()
            val dateSetListener = DatePickerDialog.OnDateSetListener {
                view, year, month, dayOfMonth ->
                dateString = "${year}년 ${month+1}월 ${dayOfMonth}일"
                edit_dateOfBirth.text = " " + dateString
            }
            DatePickerDialog(this, dateSetListener, cal.get(Calendar.YEAR),
            cal.get(Calendar.MONTH), cal.get(Calendar.DAY_OF_MONTH)).show()
        }

        btnJoinMembership.setOnClickListener {
            Log.d(third, "버튼 클릭 ")
            val email = edit_id.text.toString()
            val password1 = edit_password_1.text.toString()
            val password2 = edit_password_2.text.toString()
            if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                Toast.makeText(this, "이메일 형식이 아닙니다.", Toast.LENGTH_SHORT).show()
                Log.d(third, "Email 실패")
                return@setOnClickListener
            }

            if (!Pattern.matches("^(?=.*\\d)(?=.*[~`!@#$%\\^&*()-])(?=.*[a-zA-Z]).{8,20}$", password1)) {
                Toast.makeText(this, "숫자, 문자, 특수문자 중 2가지 포함(8~20자)", Toast.LENGTH_SHORT).show()
                Log.d(third, "패스워드1 실패")
                return@setOnClickListener
            }

            if (!Pattern.matches("^(?=.*\\d)(?=.*[~`!@#$%\\^&*()-])(?=.*[a-zA-Z]).{8,20}$", password2)) {
                Toast.makeText(this, "숫자, 문자, 특수문자 중 2가지 포함(8~20자)", Toast.LENGTH_SHORT).show()
                Log.d(third, "패스워드2 실패")
                return@setOnClickListener
            }

            if (email.isEmpty() || password1.isEmpty() || password2.isEmpty()) {
                isExistBlank = true // email, password1, 2 가 비어있는게 없다면 isExistBlank 는 false
                Log.d(third, "이메일, 패스워드1, 패스워드2 실패")
                return@setOnClickListener
            } else {
                if(password1 == password2){
                    isPWSame = true
                } else {
                    Log.d(third, "패스워드 비교 실패")
                    return@setOnClickListener
                }
            }

            if(!isExistBlank && isPWSame) { // false && true
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