package com.goalapp.goal.mainSrcreen

import android.app.AlertDialog
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.goalapp.goal.*
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.AdView
import com.google.android.gms.ads.MobileAds
import com.google.android.gms.ads.initialization.InitializationStatus
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener

import com.goalapp.goal.database.Todo
import com.goalapp.goal.database.TodoViewModel
import com.goalapp.goal.otherActivities.ThemeUtils

class CompleteContentActivity : AppCompatActivity() {
    private var adView: AdView? = null
    var data2 = ArrayList<String?>()
    private var todoViewModel: TodoViewModel? = null
    private val complete_data: MutableList<Todo> = ArrayList()
    override fun onCreate(saveInstanceState: Bundle?) {
        super.onCreate(saveInstanceState)
        ThemeUtils.onActivityCreateSetTheme(this)
        setContentView(R.layout.activity_complete_content)
        MobileAds.initialize(this, object : OnInitializationCompleteListener {
            override fun onInitializationComplete(initializationStatus: InitializationStatus?) {}
        })
        adView = findViewById(R.id.adView)
        val adRequest = AdRequest.Builder().build()
        adView.loadAd(adRequest)
        val menu_action = findViewById<ImageButton>(R.id.menu_action)
        val oDialog = AlertDialog.Builder(
            this,
            android.R.style.Theme_DeviceDefault_Light_Dialog
        )

        //AppDatabase db_complete = AppDatabase.getCompAppDatabase(this);
        val textView1 = findViewById<View>(R.id.complete_big_goal_name) as TextView
        val listView3 = findViewById<View>(R.id.complete_small_goal_name) as ListView
        val tv_make_time = findViewById<View>(R.id.make_time) as TextView
        val tv_comp_time = findViewById<View>(R.id.complete_time) as TextView
        textView1.isSingleLine = true
        textView1.ellipsize = TextUtils.TruncateAt.MARQUEE
        textView1.isSelected = true
        onWindowFocusChanged(true)
        todoViewModel = ViewModelProvider(this).get(TodoViewModel::class.java)
        todoViewModel!!.allTodos.observe(this, object : Observer<List<Todo?>?> {
            override fun onChanged(todos: List<Todo>) {
                for (i in todos.indices) {
                    if (todos[i].complete_time != null) {
                        complete_data.add(todos[i])
                    }
                }
                val intent = intent
                val position = intent.extras!!.getInt("position")
                val small_goal_data = complete_data[position].small_Goal
                for (i in small_goal_data) {
                    data2.add(i)
                }
                val content_Adapter: ArrayAdapter<*> = ArrayAdapter<Any?>(
                    applicationContext, R.layout.list_type1, data2
                )
                listView3.adapter = content_Adapter
                textView1.text = complete_data[position].big_Goal
                tv_make_time.text = "시작 날짜 : " + complete_data[position].maketime
                tv_comp_time.text = "완료 날짜 : " + complete_data[position].complete_time
                menu_action.setOnClickListener {
                    val popup = PopupMenu(this@CompleteContentActivity, menu_action)
                    val inf = popup.menuInflater
                    inf.inflate(R.menu.menu_action, popup.menu)
                    popup.menu.findItem(R.id.item2).isVisible = false
                    popup.show()
                    popup.setOnMenuItemClickListener { item ->
                        when (item.itemId) {
                            R.id.item1 -> {
                                oDialog.setMessage("목표를 삭제하시겠습니까?") // 다이얼로그 제목
                                oDialog.setCancelable(false) // 다이얼로그 화면 밖 터치 방지
                                oDialog.setPositiveButton("예") { dialog, which ->
                                    todoViewModel!!.delete(complete_data[position])
                                    val intent = Intent(
                                        this@CompleteContentActivity,
                                        CompleteGoal::class.java
                                    )
                                    startActivity(intent)
                                    finish()
                                }
                                oDialog.setNegativeButton("아니요") { dialog, which ->
                                    Toast.makeText(
                                        applicationContext,
                                        "취소",
                                        Toast.LENGTH_SHORT
                                    ).show()
                                }
                                oDialog.show()
                            }
                        }
                        true
                    }
                    popup.show()
                }
            }
        })
    } //oncreate

    override fun onWindowFocusChanged(hasFocus: Boolean) {
        super.onWindowFocusChanged(hasFocus)
        val tv_big_line = findViewById<ImageView>(R.id.view_comp_big_goal_line)
        val textView1 = findViewById<View>(R.id.complete_big_goal_name) as TextView
        val params = tv_big_line.layoutParams
        params.width = textView1.width
        tv_big_line.layoutParams = params
        tv_big_line.setColorFilter(Color.rgb(255, 167, 167))
    }

    override fun onBackPressed() {
        val intent = Intent(this@CompleteContentActivity, CompleteGoal::class.java)
        startActivity(intent)
        finish()
    }
}