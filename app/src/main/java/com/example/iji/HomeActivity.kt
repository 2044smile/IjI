package com.example.iji

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.viewpager2.widget.ViewPager2
import kotlinx.android.synthetic.main.activity_home.*
import me.relex.circleindicator.CircleIndicator3

class HomeActivity : AppCompatActivity() { // 메인(홈) 페이지
    // 1. ViewPager2 확정

    private var imagesList = mutableListOf<Int>() // mutable 변경가능한

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        postToList()

        view_pager2.adapter = ViewPagerAdapter(imagesList)
        view_pager2.orientation = ViewPager2.ORIENTATION_HORIZONTAL

        val indicator = findViewById<CircleIndicator3>(R.id.indicator)
        indicator.setViewPager(view_pager2)
    }

    private fun addToList(image: Int){
        imagesList.add(image)
    }
    private fun postToList() {
        for (i in 1..5){
            addToList(R.mipmap.ic_launcher_round)
        }
    }
}
