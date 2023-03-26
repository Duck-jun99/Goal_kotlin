package com.goalapp.goal.otherActivities

import android.app.Activity
import android.os.Bundle
import android.util.Log
import android.view.Window
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.TextView
import com.goalapp.goal.R
import com.goalapp.goal.database.Todo
import com.goalapp.goal.database.TodoViewModel

class SmallGoalList : Activity() {
    var small_goal_list: ListView? = null
    var tv_make_time: TextView? = null
    private val todos: List<Todo> = ArrayList()
    private val todoViewModel: TodoViewModel? = null
    var data = ArrayList<String?>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        setContentView(R.layout.activity_small_goal_list)
        small_goal_list = findViewById(R.id.small_goal_list)
        tv_make_time = findViewById(R.id.make_time)
        val intent = intent
        val position = intent.extras!!.getInt("position")
        val smallgoallist = intent.extras!!.getStringArrayList("smallgoallist")
        val maketime = intent.extras!!.getString("maketime")
        Log.e("small_goal_list : ", smallgoallist.toString())
        var stage = 1
        for (i in smallgoallist!!) {
            data.add(stage.toString() + "단계: " + i)
            stage++
        }
        tv_make_time.setText("시작 날짜 : $maketime")
        val content_Adapter: ArrayAdapter<*> = ArrayAdapter<Any?>(this, R.layout.list_type1, data)
        small_goal_list.setAdapter(content_Adapter)
    }
}