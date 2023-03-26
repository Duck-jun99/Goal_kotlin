package com.goalapp.goal.mainSrcreen

import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.goalapp.goal.*
import com.goalapp.goal.recyclerView.Adapter
import com.goalapp.goal.database.Todo
import com.goalapp.goal.database.TodoViewModel
import com.goalapp.goal.otherActivities.ThemeUtils
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.AdView
import com.google.android.gms.ads.MobileAds
import com.google.android.gms.ads.initialization.InitializationStatus
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.navigation.NavigationBarView


class CompleteGoal : AppCompatActivity() {
    var complete_data: MutableList<Todo> = ArrayList()
    private var todoViewModel: TodoViewModel? = null
    var bottomNavigationView: BottomNavigationView? = null
    private var adView: AdView? = null
    private val adapter = Adapter()
    private fun updateNavigationBarState(actionId: Int) {
        val menu = bottomNavigationView!!.menu
        val item = menu.getItem(1)
        item.isChecked = item.itemId == actionId
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        ThemeUtils.onActivityCreateSetTheme(this)
        setContentView(R.layout.activity_complete_goal)
        MobileAds.initialize(this, object : OnInitializationCompleteListener {
            override fun onInitializationComplete(initializationStatus: InitializationStatus?) {}
        })
        adView = findViewById(R.id.adView)
        val adRequest = AdRequest.Builder().build()
        adView.loadAd(adRequest)
        bottomNavigationView = findViewById(R.id.bottom_navigation)
        val img_plus_goal = findViewById<ImageView>(R.id.img_plus_goal)
        val menu = bottomNavigationView.getMenu()
        val item = menu.getItem(1)
        updateNavigationBarState(item.itemId)

        //final AppDatabase db_complete = AppDatabase.getCompAppDatabase(this);
        val intent = intent
        val small_goal_name = intent.getStringArrayListExtra("small_goal_name")
        //adapter.setItems(complete_data);
        val recyclerView = findViewById<RecyclerView>(R.id.list_complete_goal)
        val gridLayoutManager = GridLayoutManager(this, 2)
        recyclerView.layoutManager = gridLayoutManager
        recyclerView.adapter = adapter
        todoViewModel = ViewModelProvider(this).get(TodoViewModel::class.java)
        todoViewModel!!.allTodos.observe(this, object : Observer<List<Todo?>?> {
            override fun onChanged(todos: List<Todo>) {
                for (i in todos.indices) {
                    if (todos[i].complete_time != null) {
                        val todo_item = todos[i]
                        complete_data.add(todo_item)
                    }
                }
                adapter.setItems(complete_data)
                if (complete_data.size == 0) {
                    val msg_plus_goal = findViewById<TextView>(R.id.msg_plus_goal)
                    msg_plus_goal.text = "아직 완료한 목표가 없습니다!"
                    img_plus_goal.visibility = View.VISIBLE
                }
                if (complete_data.size > 0) {
                    for (i in complete_data.indices) {
                        Log.e("Complete: id : ", complete_data[i].id.toString()) //1~
                        Log.e("Complete: biggoal : ", complete_data[i].big_Goal) //id 숫자에 맞는 대목표
                        Log.e(
                            "Complete: smallgoal : ",
                            complete_data[i].small_Goal.toString()
                        ) //id 숫자에 맞는 소목표
                        Log.e("Complete: stage : ", complete_data[i].stage.toString())
                        Log.e("Complete: maketime : ", complete_data[i].maketime.toString())
                        Log.e("Complete: comp_time: ", complete_data[i].complete_time.toString())
                    }
                }
            }
        })
        bottomNavigationView.setOnItemSelectedListener(NavigationBarView.OnItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.goal_ing -> {
                    val intent = Intent(this@CompleteGoal, MainActivity::class.java)
                    startActivity(intent)
                    overridePendingTransition(R.anim.hold, R.anim.hold)
                    finish()
                }
                R.id.goal_complete -> {
                    val intent2 = Intent(this@CompleteGoal, CompleteGoal::class.java)
                    startActivity(intent2)
                    overridePendingTransition(R.anim.hold, R.anim.hold)
                    finish()
                }
                R.id.setting -> {
                    val intent3 = Intent(this@CompleteGoal, Setting::class.java)
                    startActivity(intent3)
                    overridePendingTransition(R.anim.hold, R.anim.hold)
                    finish()
                }
            }
            true
        })
        adapter.setOnItemClickListener { holder, view, position ->
            val data = adapter.getItem(position)
            val intent = Intent(this@CompleteGoal, CompleteContentActivity::class.java)
            intent.putExtra("Key", data.toString())
            intent.putExtra("position", position)
            startActivity(intent)
            finish()
        }
    } //oncreate

    override fun onBackPressed() {   // 뒤로가기 누르면 다이얼로그 생성
        val builder = AlertDialog.Builder(
            this,
            android.R.style.Theme_DeviceDefault_Light_Dialog
        )
        builder.setTitle("종료할까요?") // 다이얼로그 제목
        builder.setCancelable(false) // 다이얼로그 화면 밖 터치 방지
        builder.setPositiveButton("예") { dialog, which -> exit() }
        builder.setNegativeButton("아니요") { dialog, which ->
            Toast.makeText(
                applicationContext,
                "잘 생각했어요!",
                Toast.LENGTH_SHORT
            ).show()
        }
        builder.show() // 다이얼로그 보이기
    }

    fun exit() { // 종료
        super.onBackPressed()
    }
}