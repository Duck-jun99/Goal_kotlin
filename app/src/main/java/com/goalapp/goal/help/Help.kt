package com.goalapp.goal.help

import android.app.Activity
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ImageButton
import android.widget.ImageView
import com.goalapp.goal.R

class Help : Activity() {
    var page = 1
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (Build.VERSION.SDK_INT <= Build.VERSION_CODES.P) {
            setTheme(R.style.Theme_AppCompat_NoActionBar)
        }
        setContentView(R.layout.activity_help)
        val imageView = findViewById<ImageView>(R.id.img_help)
        val btn_back = findViewById<ImageButton>(R.id.btn_back_img)
        val btn_next = findViewById<ImageButton>(R.id.btn_next_img)
        btn_back.visibility = View.INVISIBLE
        btn_next.setOnClickListener {
            page = page + 1
            Log.e("페이지:", page.toString())
            btn_back.visibility = View.VISIBLE
            if (page == 1) {
                btn_back.visibility = View.INVISIBLE
                btn_next.visibility = View.VISIBLE
                imageView.setImageResource(R.drawable.help01)
            } else if (page == 2) {
                btn_back.visibility = View.VISIBLE
                btn_next.visibility = View.VISIBLE
                imageView.setImageResource(R.drawable.help02)
            } else if (page == 3) {
                imageView.setImageResource(R.drawable.help03)
            } else if (page == 4) {
                imageView.setImageResource(R.drawable.help04)
            } else if (page == 5) {
                imageView.setImageResource(R.drawable.help05)
            } else if (page == 6) {
                imageView.setImageResource(R.drawable.help06)
            } else if (page == 7) {
                imageView.setImageResource(R.drawable.help07)
            } else if (page == 8) {
                imageView.setImageResource(R.drawable.help08)
                btn_next.visibility = View.VISIBLE
                btn_back.visibility = View.VISIBLE
            } else if (page == 9) {
                imageView.setImageResource(R.drawable.help09)
                btn_next.visibility = View.INVISIBLE
                btn_back.visibility = View.VISIBLE
            }
        }
        btn_back.setOnClickListener {
            page = page - 1
            Log.e("페이지:", page.toString())
            if (page == 1) {
                btn_back.visibility = View.INVISIBLE
                btn_next.visibility = View.VISIBLE
                imageView.setImageResource(R.drawable.help01)
            } else if (page == 2) {
                btn_back.visibility = View.VISIBLE
                btn_next.visibility = View.VISIBLE
                imageView.setImageResource(R.drawable.help02)
            } else if (page == 3) {
                imageView.setImageResource(R.drawable.help03)
            } else if (page == 4) {
                imageView.setImageResource(R.drawable.help04)
            } else if (page == 5) {
                imageView.setImageResource(R.drawable.help05)
            } else if (page == 6) {
                imageView.setImageResource(R.drawable.help06)
            } else if (page == 7) {
                imageView.setImageResource(R.drawable.help07)
            } else if (page == 8) {
                imageView.setImageResource(R.drawable.help08)
                btn_next.visibility = View.VISIBLE
                btn_back.visibility = View.VISIBLE
            } else if (page == 9) {
                imageView.setImageResource(R.drawable.help09)
                btn_next.visibility = View.INVISIBLE
                btn_back.visibility = View.VISIBLE
            }
        }
    } //oncreate
}