package com.example.iji.utils

object Constants {
    const val TAG: String = "로그"
}

enum class RESPONSE_STATE {
    OKAY,
    FAIL
}

object API { // 변수에 값을 박아놓은 느낌?
    const val BASE_URL : String = "http://ec2-3-35-54-69.ap-northeast-2.compute.amazonaws.com:3000/"
    const val TEST : String = "api/routerTest"
}