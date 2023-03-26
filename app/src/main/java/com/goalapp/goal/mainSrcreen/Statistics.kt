package com.goalapp.goal.mainSrcreen

import android.graphics.Color
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.goalapp.goal.R
import com.goalapp.goal.otherActivities.ThemeUtils
import com.goalapp.goal.database.Todo
import com.goalapp.goal.database.TodoViewModel
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

class Statistics : AppCompatActivity() {
    private var todoViewModel: TodoViewModel? = null
    var todo_item: MutableList<Todo> = ArrayList()
    var complete_data: MutableList<Todo> = ArrayList()
    var txt_ing_statistics_1: TextView? = null
    var txt_ing_statistics_2: TextView? = null
    var txt_ing_statistics_3: TextView? = null
    var txt_ing_statistics_4: TextView? = null
    var txt_comp_statistics_1: TextView? = null
    var txt_comp_statistics_2: TextView? = null
    var txt_comp_statistics_3: TextView? = null
    var txt_comp_statistics_4: TextView? = null
    var recent_ing_goal: String? = null
    var recent_comp_goal: String? = null
    var str_start_date: String? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        ThemeUtils.onActivityCreateSetTheme(this)
        setContentView(R.layout.activity_statistics)
        onWindowFocusChanged(true)
        txt_ing_statistics_1 = findViewById(R.id.txt_ing_statistics_1)
        txt_ing_statistics_2 = findViewById(R.id.txt_ing_statistics_2)
        txt_ing_statistics_3 = findViewById(R.id.txt_ing_statistics_3)
        txt_ing_statistics_4 = findViewById(R.id.txt_ing_statistics_4)
        txt_comp_statistics_1 = findViewById(R.id.txt_comp_statistics_1)
        txt_comp_statistics_2 = findViewById(R.id.txt_comp_statistics_2)
        txt_comp_statistics_3 = findViewById(R.id.txt_comp_statistics_3)
        txt_comp_statistics_4 = findViewById(R.id.txt_comp_statistics_4)
        val sdf = SimpleDateFormat("yyyy/MM/dd")
        todoViewModel = ViewModelProvider(this).get(TodoViewModel::class.java)
        todoViewModel!!.allTodos.observe(this) { todos ->
            for (i in todos.indices) {
                if (todos[i].Complete_time == null) {
                    todo_item.add(todos[i])
                }
            }
            for (i in todos.indices) {
                if (todos[i].complete_time != null) {
                    complete_data.add(todos[i])
                }
            }
            Log.e("db 사이즈 : ", todo_item.size.toString())
            val diffDay_all_1: ArrayList<*> = ArrayList<Any?>()
            var diffDay_1: Long = 0
            Log.e("db_comp 사이즈 : ", complete_data.size.toString())
            val diffDay_all_2: ArrayList<*> = ArrayList<Any?>()
            var diffDay_2: Long = 0
            if (todo_item.size > 0) {
                recent_ing_goal = todo_item[todo_item.size - 1].big_Goal
                for (i in todo_item.indices) {
                    str_start_date = todo_item[i].maketime
                    val now = System.currentTimeMillis()
                    try {
                        val start_date = sdf.parse(str_start_date)
                        val getNow = Date(now)
                        diffDay_1 = (getNow.time - start_date.time) / (24 * 60 * 60 * 1000)
                    } catch (e: ParseException) {
                    }
                    diffDay_all_1.add(diffDay_1)
                }
                txt_ing_statistics_1.setText(todo_item.size.toString() + "개")
                txt_ing_statistics_2.setText(
                    Collections.max<Comparable<*>>(diffDay_all_1).toString() + "일"
                )
                txt_ing_statistics_3.setText(
                    Collections.min<Comparable<*>>(diffDay_all_1).toString() + "일"
                )
                txt_ing_statistics_4.setText(recent_ing_goal)
            } else {
                txt_ing_statistics_1.setText(" ")
                txt_ing_statistics_2.setText(" ")
                txt_ing_statistics_3.setText(" ")
                txt_ing_statistics_4.setText(" ")
            }
            if (complete_data.size > 0) {
                recent_comp_goal = complete_data[complete_data.size - 1].big_Goal
                for (i in complete_data.indices) {
                    str_start_date = complete_data[i].maketime
                    val now = System.currentTimeMillis()
                    try {
                        val start_date = sdf.parse(str_start_date)
                        val getNow = Date(now)
                        diffDay_2 = (getNow.time - start_date.time) / (24 * 60 * 60 * 1000)
                    } catch (e: ParseException) {
                    }
                    diffDay_all_2.add(diffDay_2)
                }
                txt_comp_statistics_1.setText(complete_data.size.toString() + "개")
                txt_comp_statistics_2.setText(
                    Collections.max<Comparable<*>>(diffDay_all_2).toString() + "일"
                )
                txt_comp_statistics_3.setText(
                    Collections.min<Comparable<*>>(diffDay_all_2).toString() + "일"
                )
                txt_comp_statistics_4.setText(recent_comp_goal)
            } else {
                txt_comp_statistics_1.setText(" ")
                txt_comp_statistics_2.setText(" ")
                txt_comp_statistics_3.setText(" ")
                txt_comp_statistics_4.setText(" ")
            }
        }
    }

    override fun onWindowFocusChanged(hasFocus: Boolean) {
        super.onWindowFocusChanged(hasFocus)
        val statistics_line_1 = findViewById<ImageView>(R.id.statistics_line_1)
        val statistics_line_2 = findViewById<ImageView>(R.id.statistics_line_2)
        val statistics_ing = findViewById<TextView>(R.id.statistics_ing)
        val statistics_comp = findViewById<TextView>(R.id.statistics_comp)
        statistics_ing.isSingleLine = true
        statistics_ing.ellipsize = TextUtils.TruncateAt.MARQUEE
        statistics_ing.isSelected = true
        statistics_comp.isSingleLine = true
        statistics_comp.ellipsize = TextUtils.TruncateAt.MARQUEE
        statistics_comp.isSelected = true
        val params_1 = statistics_line_1.layoutParams
        params_1.width = statistics_ing.width
        statistics_line_1.layoutParams = params_1
        statistics_line_1.setColorFilter(Color.rgb(209, 178, 255))
        val params_2 = statistics_line_2.layoutParams
        params_2.width = statistics_comp.width
        statistics_line_2.layoutParams = params_2
        statistics_line_2.setColorFilter(Color.rgb(255, 167, 167))
    }
}