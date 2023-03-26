package com.goalapp.goal.mainSrcreen

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import com.goalapp.goal.R
import com.goalapp.goal.otherActivities.ThemeUtils

class loading : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        ThemeUtils.onActivityCreateSetTheme(this)
        setContentView(R.layout.activity_loading)
        startLoading()
    } // oncreate

    private fun startLoading() {
        val handler = Handler()
        handler.postDelayed({
            val intent = Intent(applicationContext, MainActivity::class.java) //접속창 뜨기
            startActivity(intent)
            finish() //현재 액티비티 종료
        }, 2000) // 화면에 Loading 화면 2초간 보이기
    } // startLoading
}