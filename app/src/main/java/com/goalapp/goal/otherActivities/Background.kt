package com.goalapp.goal.otherActivities

import android.app.Activity
import android.os.Bundle
import android.view.Window
import android.widget.*
import com.goalapp.goal.R
import com.goalapp.goal.mainSrcreen.Setting

class Background : Activity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        setContentView(R.layout.activity_background)
        val btn_red = findViewById<Button>(R.id.btn_red)
        val btn_pink = findViewById<Button>(R.id.btn_pink)
        val btn_yellow = findViewById<Button>(R.id.btn_yellow)
        val btn_green = findViewById<Button>(R.id.btn_green)
        val btn_blue = findViewById<Button>(R.id.btn_blue)
        val btn_purple = findViewById<Button>(R.id.btn_purple)
        val btn_white = findViewById<Button>(R.id.btn_white)
        val btn_gray = findViewById<Button>(R.id.btn_gray)
        btn_red.setOnClickListener {
            finish()
            ThemeUtils.changeToTheme(Setting.Companion.context as Setting, ThemeUtils.THEME_RED)
            //((Setting)Setting.context).test_layout.setBackgroundColor(Color.rgb(255,175,176));
        }
        btn_pink.setOnClickListener {
            finish()
            ThemeUtils.changeToTheme(Setting.Companion.context as Setting, ThemeUtils.THEME_PINK)
            //((Setting)Setting.context).test_layout.setBackgroundColor(Color.rgb(255,228,225));
        }
        btn_yellow.setOnClickListener {
            finish()
            ThemeUtils.changeToTheme(Setting.Companion.context as Setting, ThemeUtils.THEME_YELLOW)
            //((Setting)Setting.context).test_layout.setBackgroundColor(Color.rgb(255,250,240));
        }
        btn_green.setOnClickListener {
            finish()
            ThemeUtils.changeToTheme(Setting.Companion.context as Setting, ThemeUtils.THEME_GREEN)
            //((Setting)Setting.context).test_layout.setBackgroundColor(Color.rgb(240,255,240));
        }
        btn_blue.setOnClickListener {
            finish()
            ThemeUtils.changeToTheme(Setting.Companion.context as Setting, ThemeUtils.THEME_BLUE)
            //((Setting)Setting.context).test_layout.setBackgroundColor(Color.rgb(240,255,255));
        }
        btn_purple.setOnClickListener {
            finish()
            ThemeUtils.changeToTheme(Setting.Companion.context as Setting, ThemeUtils.THEME_PURPLE)
            //((Setting)Setting.context).test_layout.setBackgroundColor(Color.rgb(255,240,255));
        }
        btn_gray.setOnClickListener {
            finish()
            ThemeUtils.changeToTheme(Setting.Companion.context as Setting, ThemeUtils.THEME_DEFAULT)
            //((Setting)Setting.context).test_layout.setBackgroundColor(Color.rgb(175,171,171));
        }
        btn_white.setOnClickListener {
            finish()
            ThemeUtils.changeToTheme(Setting.Companion.context as Setting, ThemeUtils.THEME_WHITE)
            //((Setting)Setting.context).test_layout.setBackgroundColor(Color.rgb(255,255,255));
        }
    }
}