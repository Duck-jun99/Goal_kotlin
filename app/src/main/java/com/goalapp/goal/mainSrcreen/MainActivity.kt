package com.goalapp.goal.mainSrcreen

import android.appwidget.AppWidgetManager
import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.goalapp.goal.*
import com.goalapp.goal.database.Todo
import com.goalapp.goal.database.TodoViewModel
import com.goalapp.goal.otherActivities.ThemeUtils
import com.goalapp.goal.widget.MainWidget
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.AdView
import com.google.android.gms.ads.MobileAds
import com.google.android.gms.ads.initialization.InitializationStatus
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.navigation.NavigationBarView

class MainActivity : AppCompatActivity() {
    private var todoViewModel: TodoViewModel? = null
    var bottomNavigationView: BottomNavigationView? = null
    var todo_item: MutableList<Todo> = ArrayList()
    var btn_make_goal: ImageButton? = null
    private var adView: AdView? = null
    private val adapter = Adapter()
    private fun updateNavigationBarState(actionId: Int) {
        val menu = bottomNavigationView!!.menu
        val item = menu.getItem(0)
        item.isChecked = item.itemId == actionId
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        ThemeUtils.onActivityCreateSetTheme(this)
        setContentView(R.layout.activity_main)
        MobileAds.initialize(this, object : OnInitializationCompleteListener {
            override fun onInitializationComplete(initializationStatus: InitializationStatus?) {}
        })
        adView = findViewById(R.id.adView)
        val adRequest = AdRequest.Builder().build()
        adView.loadAd(adRequest)
        MainActivity.Companion.context = this
        val appWidgetManager = AppWidgetManager.getInstance(this)
        val appWidgetIds = appWidgetManager.getAppWidgetIds(
            ComponentName(this, MainWidget::class.java)
        )
        appWidgetManager.notifyAppWidgetViewDataChanged(appWidgetIds, R.id.widget_listview)
        bottomNavigationView = findViewById(R.id.bottom_navigation)
        val img_plus_goal = findViewById<ImageView>(R.id.img_plus_goal)
        val menu = bottomNavigationView.getMenu()
        val item = menu.getItem(0)
        updateNavigationBarState(item.itemId)
        val recyclerView = findViewById<RecyclerView>(R.id.list_goal)
        val gridLayoutManager = GridLayoutManager(this, 2)
        recyclerView.layoutManager = gridLayoutManager
        recyclerView.adapter = adapter
        btn_make_goal = findViewById(R.id.btn_make_goal)
        btn_make_goal.setOnClickListener(View.OnClickListener { view: View? ->
            val intent = Intent(applicationContext, MakeGoal::class.java)
            startActivity(intent)
            finish()
        })
        val intent = intent
        val goal1_name = intent.getStringExtra("big_goal_name")
        val small_goal_name = intent.getStringArrayListExtra("small_goal_name")


        //todoViewModel

        todoViewModel = ViewModelProvider(this).get(TodoViewModel::class.java)
        todoViewModel!!.getAll().observe(this, Observer{
            fun onChanged(todos: List<Todo>) {
                Log.e("todos사이즈 : ", todos.size.toString())
                for (i in todos.indices) {
                    if (todos[i].complete_time == null) {
                        todo_item.add(todos[i])
                        adapter.setItems(todo_item)
                    }
                }
                Log.e("todo_item 사이즈 : ", todo_item.size.toString())
                if (todo_item.size == 0) {
                    val msg_plus_goal = findViewById<TextView>(R.id.msg_plus_goal)
                    msg_plus_goal.text = "진행중인 목표가 없습니다!"
                    img_plus_goal.visibility = View.VISIBLE
                } else if (todo_item.size != 0) {
                    for (i in todo_item.indices) {
                        Log.e("onCreate: id() : ", todo_item[i].id.toString()) //1~
                        Log.e("onCreate: biggoal : ", todo_item[i].big_Goal) //id 숫자에 맞는 대목표
                        Log.e(
                            "onCreate: smallgoal : ",
                            todo_item[i].small_Goal.toString()
                        ) //id 숫자에 맞는 소목표
                        Log.e("smallgoal Size : ", todo_item[i].get_small_Goal_size().toString())
                        Log.e("onCreate: stage : ", todo_item[i].stage.toString())
                        Log.e("onCreate: maketime : ", todo_item[i].get_maketime().toString())
                        Log.e("onCreate: comp_time : ", todo_item[i].complete_time.toString())
                    }
                }


                adapter.setOnItemClickListener { holder, view, position ->
                    val data = todo_item[position]
                    Log.e("Todo data : ", data.toString())
                    Log.e("Todo item position : ", position.toString())
                    val intent = Intent(this@MainActivity, MainContent::class.java)
                    intent.putExtra("Key", data.toString())
                    intent.putExtra("position", position)
                    intent.putStringArrayListExtra("small_goal_name", small_goal_name)
                    startActivity(intent)
                    finish()
                }
            }
        })

        /*
        todoViewModel.allTodos.observe(this, object : Observer<List<Todo?>?> {
            override fun onChanged(todos: List<Todo>) {
                Log.e("todos사이즈 : ", todos.size.toString())
                for (i in todos.indices) {
                    if (todos[i].Complete_time == null) {
                        todo_item.add(todos[i])
                        adapter.setItems(todo_item)
                    }
                }
                Log.e("todo_item 사이즈 : ", todo_item.size.toString())
                if (todo_item.size == 0) {
                    val msg_plus_goal = findViewById<TextView>(R.id.msg_plus_goal)
                    msg_plus_goal.text = "진행중인 목표가 없습니다!"
                    img_plus_goal.visibility = View.VISIBLE
                } else if (todo_item.size != 0) {
                    for (i in todo_item.indices) {
                        Log.e("onCreate: id() : ", todo_item[i].id.toString()) //1~
                        Log.e("onCreate: biggoal : ", todo_item[i].big_Goal) //id 숫자에 맞는 대목표
                        Log.e(
                            "onCreate: smallgoal : ",
                            todo_item[i].small_Goal.toString()
                        ) //id 숫자에 맞는 소목표
                        Log.e("smallgoal Size : ", todo_item[i].small_Goal_size.toString())
                        Log.e("onCreate: stage : ", todo_item[i].stage.toString())
                        Log.e("onCreate: maketime : ", todo_item[i].maketime.toString())
                        Log.e("onCreate: comp_time : ", todo_item[i].complete_time.toString())
                    }
                }
                adapter.setOnItemClickListener { holder, view, position ->
                    val data = todo_item[position]
                    Log.e("Todo data : ", data.toString())
                    Log.e("Todo item position : ", position.toString())
                    val intent = Intent(this@MainActivity, MainContent::class.java)
                    intent.putExtra("Key", data.toString())
                    intent.putExtra("position", position)
                    intent.putStringArrayListExtra("small_goal_name", small_goal_name)
                    startActivity(intent)
                    finish()
                }
            }
        })
        */


        bottomNavigationView.setOnItemSelectedListener(NavigationBarView.OnItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.goal_ing -> {
                    val intent = Intent(this@MainActivity, MainActivity::class.java)
                    startActivity(intent)
                    overridePendingTransition(R.anim.hold, R.anim.hold)
                    finish()
                }
                R.id.goal_complete -> {
                    val intent2 = Intent(this@MainActivity, CompleteGoal::class.java)
                    intent2.putStringArrayListExtra("small_goal_name", small_goal_name)
                    startActivity(intent2)
                    overridePendingTransition(R.anim.hold, R.anim.hold)
                    finish()
                }
                R.id.setting -> {
                    val intent3 = Intent(this@MainActivity, Setting::class.java)
                    startActivity(intent3)
                    overridePendingTransition(R.anim.hold, R.anim.hold)
                    finish()
                }
            }
            true
        })
    } //oncreate()

    override fun onBackPressed() {
        super.onBackPressed()
    }


    companion object {
        var context: Context? = null
    }
} //MainActivity.class
