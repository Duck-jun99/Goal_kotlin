package com.goalapp.goal.mainSrcreen

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.goalapp.goal.R
import com.goalapp.goal.database.Todo


class ContentActivity : AppCompatActivity() {
    var data2 = ArrayList<String?>()
    private val todos: List<Todo> = ArrayList()
    override fun onCreate(saveInstanceState: Bundle?) {
        super.onCreate(saveInstanceState)
        setContentView(R.layout.activity_content)
        val textView1 = findViewById<View>(R.id.big_goal_name) as TextView
        val listView3 = findViewById<View>(R.id.small_goal_name) as ListView
        val intent = intent
        val position = intent.extras!!.getInt("position")
        val small_goal_data = todos[position].small_Goal
        for (i in small_goal_data) {
            data2.add(i)
        }
        textView1.text = todos[position].big_Goal
        val content_Adapter: ArrayAdapter<*> =
            ArrayAdapter<Any?>(this, android.R.layout.simple_list_item_single_choice, data2)
        listView3.adapter = content_Adapter
    }

    override fun onBackPressed() {
        val intent = Intent(this@ContentActivity, MainActivity::class.java)
        startActivity(intent)
        finish()
    }
}