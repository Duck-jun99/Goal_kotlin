package com.goalapp.goal.mainSrcreen

import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.goalapp.goal.otherActivities.Background
import com.goalapp.goal.R
import com.goalapp.goal.otherActivities.ThemeUtils
import com.goalapp.goal.help.Help
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.navigation.NavigationBarView

class Setting : AppCompatActivity() {
    var bottomNavigationView: BottomNavigationView? = null
    var btn_QA: Button? = null
    var btn_statistics: Button? = null
    var btn_background: Button? = null
    var test_layout: View? = null
    private fun updateNavigationBarState(actionId: Int) {
        val menu = bottomNavigationView!!.menu
        val item = menu.getItem(2)
        item.isChecked = item.itemId == actionId
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        ThemeUtils.onActivityCreateSetTheme(this)
        setContentView(R.layout.activity_setting)
        context = this
        bottomNavigationView = findViewById(R.id.bottom_navigation)
        btn_QA = findViewById(R.id.btn_QA)
        btn_statistics = findViewById(R.id.btn_statistics)
        btn_background = findViewById(R.id.btn_background)
        test_layout = findViewById(R.id.test_layout)
        val menu = bottomNavigationView.getMenu()
        val item = menu.getItem(2)
        updateNavigationBarState(item.itemId)
        btn_QA.setOnClickListener(View.OnClickListener {
            val intent = Intent(this@Setting, Help::class.java)
            startActivity(intent)
        })
        btn_statistics.setOnClickListener(View.OnClickListener {
            val intent = Intent(this@Setting, Statistics::class.java)
            startActivity(intent)
        })
        btn_background.setOnClickListener(View.OnClickListener {
            val intent = Intent(this@Setting, Background::class.java)
            startActivity(intent)
        })
        bottomNavigationView.setOnItemSelectedListener(NavigationBarView.OnItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.goal_ing -> {
                    val intent = Intent(this@Setting, MainActivity::class.java)
                    startActivity(intent)
                    overridePendingTransition(R.anim.hold, R.anim.hold)
                    finish()
                }
                R.id.goal_complete -> {
                    val intent2 = Intent(this@Setting, CompleteGoal::class.java)
                    startActivity(intent2)
                    overridePendingTransition(R.anim.hold, R.anim.hold)
                    finish()
                }
                R.id.setting -> {
                    val intent3 = Intent(this@Setting, Setting::class.java)
                    startActivity(intent3)
                    overridePendingTransition(R.anim.hold, R.anim.hold)
                    finish()
                }
            }
            true
        })
    } //oncreate

    override fun onBackPressed() {
        val builder = AlertDialog.Builder(
            this,
            android.R.style.Theme_DeviceDefault_Light_Dialog
        )
        builder.setTitle("종료할까요?")
        builder.setCancelable(false)
        builder.setPositiveButton("예") { dialog, which -> exit() }
        builder.setNegativeButton("아니요") { dialog, which ->
            Toast.makeText(
                applicationContext,
                "잘 생각했어요!",
                Toast.LENGTH_SHORT
            ).show()
        }
        builder.show()
    }

    fun exit() { // 종료
        super.onBackPressed()
    }

    companion object {
        var context: Context? = null
    }
}