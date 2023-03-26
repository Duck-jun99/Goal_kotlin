package com.goalapp.goal.mainSrcreen

import android.app.AlertDialog
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.AnimationDrawable
import android.os.Bundle
import android.os.Handler
import android.text.TextUtils
import android.util.Log
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.goalapp.goal.otherActivities.GoalLetter
import com.goalapp.goal.R
import com.goalapp.goal.otherActivities.SmallGoalList
import com.goalapp.goal.otherActivities.ThemeUtils
import com.goalapp.goal.database.Todo
import com.goalapp.goal.database.TodoViewModel
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.AdView
import com.google.android.gms.ads.MobileAds
import com.google.android.material.snackbar.Snackbar
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

class MainContent : AppCompatActivity() {
    private val mFormat = SimpleDateFormat("yyyy/M/d") // 날짜 포맷
    private var adView: AdView? = null
    private val todo_item: MutableList<Todo> = ArrayList()
    private var todoViewModel: TodoViewModel? = null
    override fun onCreate(saveInstanceState: Bundle?) {
        super.onCreate(saveInstanceState)
        ThemeUtils.onActivityCreateSetTheme(this)
        setContentView(R.layout.small_goal_5)
        onWindowFocusChanged(true)
        MobileAds.initialize(this) { }
        adView = findViewById(R.id.adView)
        val adRequest = AdRequest.Builder().build()
        adView.loadAd(adRequest)
        val oDialog = AlertDialog.Builder(
            this,
            android.R.style.Theme_DeviceDefault_Light_Dialog
        )
        val oDialog2 = AlertDialog.Builder(
            this,
            android.R.style.Theme_DeviceDefault_Light_Dialog
        )
        val oDialog3 = AlertDialog.Builder(
            this,
            android.R.style.Theme_DeviceDefault_Light_Dialog
        )
        val oDialog4 = AlertDialog.Builder(
            this,
            android.R.style.Theme_DeviceDefault_Light_Dialog
        )
        val tv_day = findViewById<TextView>(R.id.tv_day)
        tv_day.visibility = View.INVISIBLE
        val textView_Big = findViewById<TextView>(R.id.view_big_goal)
        val textView_Small = findViewById<TextView>(R.id.view_small_goal)
        val textView_Comp_small = findViewById<TextView>(R.id.view_comp_small_goal)
        val textView_Next_small = findViewById<TextView>(R.id.view_next_small_goal)
        val big_goal = findViewById<ImageView>(R.id.biggoal)
        val user1 = findViewById<ImageView>(R.id.user1)
        val user_good = findViewById<ImageView>(R.id.user_good)
        val ball1 = findViewById<ImageView>(R.id.ball1)
        val completeGoal = findViewById<Button>(R.id.completeGoal)
        val Goalback = findViewById<Button>(R.id.Goalback)
        val move_completeGoal = findViewById<Button>(R.id.move_completeGoal)
        val road = findViewById<ImageView>(R.id.road)
        val view_layout = findViewById<View>(R.id.view_Layout)
        val menu_action = findViewById<ImageButton>(R.id.menu_action)
        val ani1 = user1.drawable as AnimationDrawable
        val ani_ball1 = ball1.drawable as AnimationDrawable
        val ani_road = road.drawable as AnimationDrawable
        ani1.start()
        ani_ball1.start()
        ani_road.start()
        textView_Big.isSingleLine = true
        textView_Big.ellipsize = TextUtils.TruncateAt.MARQUEE
        textView_Big.isSelected = true
        textView_Small.isSingleLine = true
        textView_Small.ellipsize = TextUtils.TruncateAt.MARQUEE
        textView_Small.isSelected = true
        val wise_saying = resources.getStringArray(R.array.wise_saying)
        val ran = (Math.random() * 16 - 1).toInt()
        //Log.e("ran 값:",String.valueOf(ran));
        val select_wise = wise_saying[ran]
        todoViewModel = ViewModelProvider(this).get(TodoViewModel::class.java)
        todoViewModel!!.allTodos.observe(this) { todos ->
            for (i in todos.indices) {
                if (todos[i].Complete_time == null) {
                    todo_item.add(todos[i])
                }
            }
            val intent = intent
            val position = intent.extras!!.getInt("position")
            if (todo_item.size != 0) {

                //Log.e("small_goal_data : ", String.valueOf(todos.get(position).getSmall_Goal()));
                Log.e("stage : ", todo_item[position].stage.toString())
                Log.e("Goal_name : ", todo_item[position].big_Goal.toString())
                val small_goal_data = todo_item[position].small_Goal
                val stage = todo_item[position].stage
                textView_Big.text = todo_item[position].big_Goal
                Log.e("db에 들어간 stage 값:", todo_item[position].stage.toString())
                Log.e("현재 선택된 목표의 id:", todo_item[position].id.toString())
                val sdf = SimpleDateFormat("yyyy/MM/dd")
                val str_start_date = todo_item[position].maketime
                val now = System.currentTimeMillis()
                if (todo_item[position].stage < todo_item[position].small_Goal_size) {
                    user_good.visibility = View.INVISIBLE
                    completeGoal.isEnabled = true
                    Goalback.isEnabled = todo_item[position].stage > 0
                    move_completeGoal.isEnabled = false
                    textView_Small.text =
                        (todo_item[position].stage + 1).toString() + "단계 : " + small_goal_data[todo_item[position].stage]
                    if (todo_item[position].stage == 0) {
                        textView_Comp_small.visibility = View.INVISIBLE
                    } else {
                        textView_Comp_small.visibility = View.VISIBLE
                        textView_Comp_small.text =
                            todo_item[position].stage.toString() + "단계 : " + small_goal_data[todo_item[position].stage - 1]
                    }
                    if (todo_item[position].stage == todo_item[position].small_Goal_size - 1 ||
                        todo_item[position].stage == todo_item[position].small_Goal_size
                    ) {
                        textView_Next_small.visibility = View.INVISIBLE
                    } else {
                        textView_Next_small.visibility = View.VISIBLE
                        textView_Next_small.text =
                            (todo_item[position].stage + 2).toString() + "단계 : " + small_goal_data[todo_item[position].stage + 1]
                    }
                } else if (todo_item[position].stage == todo_item[position].small_Goal_size) {
                    user1.visibility = View.INVISIBLE
                    ball1.visibility = View.INVISIBLE
                    user_good.visibility = View.VISIBLE
                    completeGoal.isEnabled = false
                    Goalback.isEnabled = true
                    move_completeGoal.isEnabled = true
                    Toast.makeText(applicationContext, "목표를 다 이뤘어요!!", Toast.LENGTH_SHORT).show()
                    textView_Small.text = "모든 단계를 완료했습니다!"
                    textView_Comp_small.visibility = View.INVISIBLE
                    textView_Next_small.visibility = View.INVISIBLE
                }
                completeGoal.setOnClickListener {
                    oDialog.setMessage("[" + small_goal_data[todo_item[position].stage] + "]를(을) 달성하고 다음 단계로 넘어가시겠습니까?")
                        .setNegativeButton("아니요!") { dialog, which ->
                            Log.e("Dialog", "취소")
                            Toast.makeText(applicationContext, "개발자가 항상 응원하는 중!", Toast.LENGTH_LONG)
                                .show()
                        }
                        .setPositiveButton("네!") { dialog, which ->
                            todo_item[position].stage = todo_item[position].stage + 1
                            todoViewModel!!.update(todo_item[position])
                            if (todo_item[position].stage < todo_item[position].small_Goal_size) {
                                user1.x = big_goal.x / 5 * todo_item[position].stage
                                ball1.x = user1.x + user1.width
                                Log.e("캐릭터 위치:", user1.x.toString())
                                user_good.visibility = View.INVISIBLE
                                completeGoal.isEnabled = true
                                Goalback.isEnabled = true
                                move_completeGoal.isEnabled = false
                                textView_Small.text =
                                    (todo_item[position].stage + 1).toString() + "단계 : " + small_goal_data[todo_item[position].stage]
                                if (todo_item[position].stage == 0) {
                                    textView_Comp_small.visibility = View.INVISIBLE
                                } else {
                                    textView_Comp_small.visibility = View.VISIBLE
                                    textView_Comp_small.text =
                                        todo_item[position].stage.toString() + "단계 : " + small_goal_data[todo_item[position].stage - 1]
                                }
                                if (todo_item[position].stage == todo_item[position].small_Goal_size - 1 ||
                                    todo_item[position].stage == todo_item[position].small_Goal_size
                                ) {
                                    textView_Next_small.visibility = View.INVISIBLE
                                } else {
                                    textView_Next_small.visibility = View.VISIBLE
                                    textView_Next_small.text =
                                        (todo_item[position].stage + 2).toString() + "단계 : " + small_goal_data[todo_item[position].stage + 1]
                                }
                            } else if (todo_item[position].stage == todo_item[position].small_Goal_size) {
                                user1.visibility = View.INVISIBLE
                                ball1.visibility = View.INVISIBLE
                                user_good.visibility = View.VISIBLE
                                completeGoal.isEnabled = false
                                Goalback.isEnabled = true
                                move_completeGoal.isEnabled = true
                                Toast.makeText(
                                    applicationContext,
                                    "목표를 다 이뤘어요!!",
                                    Toast.LENGTH_SHORT
                                ).show()
                                textView_Small.text = "모든 단계를 완료했습니다!"
                                textView_Comp_small.visibility = View.INVISIBLE
                                textView_Next_small.visibility = View.INVISIBLE
                            }
                        }
                        .setCancelable(false) // 백버튼으로 팝업창이 닫히지 않도록 한다.
                        .show()
                }
                Goalback.setOnClickListener {
                    oDialog2.setMessage("이전 단계로 돌아갈까요?")
                    oDialog2.setCancelable(false)
                    oDialog2.setPositiveButton("네!") { dialog, which ->
                        todo_item[position].stage = todo_item[position].stage - 1
                        todoViewModel!!.update(todo_item[position])
                        if (todo_item[position].stage <= 0) {
                            Log.e("캐릭터 위치:", user1.x.toString())
                            user1.visibility = View.VISIBLE
                            ball1.visibility = View.VISIBLE
                            user_good.visibility = View.INVISIBLE
                            completeGoal.isEnabled = true
                            Goalback.isEnabled = false
                            move_completeGoal.isEnabled = false
                            textView_Small.text =
                                (todo_item[position].stage + 1).toString() + "단계 : " + small_goal_data[todo_item[position].stage]
                            if (todo_item[position].stage == 0) {
                                textView_Comp_small.visibility = View.INVISIBLE
                            } else {
                                textView_Comp_small.visibility = View.VISIBLE
                                textView_Comp_small.text =
                                    todo_item[position].stage.toString() + "단계 : " + small_goal_data[todo_item[position].stage - 1]
                            }
                            if (todo_item[position].stage == todo_item[position].small_Goal_size - 1 ||
                                todo_item[position].stage == todo_item[position].small_Goal_size
                            ) {
                                textView_Next_small.visibility = View.INVISIBLE
                            } else {
                                textView_Next_small.visibility = View.VISIBLE
                                textView_Next_small.text =
                                    (todo_item[position].stage + 2).toString() + "단계 : " + small_goal_data[todo_item[position].stage + 1]
                            }
                        } else if (todo_item[position].stage != 0) {
                            if (todo_item[position].stage < todo_item[position].small_Goal_size) {
                                user1.x = big_goal.x / 5 * todo_item[position].stage
                                ball1.x = user1.x + user1.width
                                Log.e("캐릭터 위치:", user1.x.toString())
                                user1.visibility = View.VISIBLE
                                ball1.visibility = View.VISIBLE
                                user_good.visibility = View.INVISIBLE
                                completeGoal.isEnabled = true
                                Goalback.isEnabled = true
                                move_completeGoal.isEnabled = false
                                textView_Small.text =
                                    (todo_item[position].stage + 1).toString() + "단계 : " + small_goal_data[todo_item[position].stage]
                                if (todo_item[position].stage == 0) {
                                    textView_Comp_small.visibility = View.INVISIBLE
                                } else {
                                    textView_Comp_small.visibility = View.VISIBLE
                                    textView_Comp_small.text =
                                        todo_item[position].stage.toString() + "단계 : " + small_goal_data[todo_item[position].stage - 1]
                                }
                                if (todo_item[position].stage == todo_item[position].small_Goal_size - 1 ||
                                    todo_item[position].stage == todo_item[position].small_Goal_size
                                ) {
                                    textView_Next_small.visibility = View.INVISIBLE
                                } else {
                                    textView_Next_small.visibility = View.VISIBLE
                                    textView_Next_small.text =
                                        (todo_item[position].stage + 2).toString() + "단계 : " + small_goal_data[todo_item[position].stage + 1]
                                }
                            } else if (todo_item[position].stage == todo_item[position].small_Goal_size) {
                                user1.visibility = View.INVISIBLE
                                ball1.visibility = View.INVISIBLE
                                user_good.visibility = View.VISIBLE
                                completeGoal.isEnabled = false
                                Goalback.isEnabled = true
                                move_completeGoal.isEnabled = true
                                Toast.makeText(
                                    applicationContext,
                                    "목표를 다 이뤘어요!!",
                                    Toast.LENGTH_SHORT
                                ).show()
                                textView_Small.text = "모든 단계를 완료했습니다!"
                                textView_Comp_small.visibility = View.INVISIBLE
                                textView_Next_small.visibility = View.INVISIBLE
                            }
                        }
                    }
                    oDialog2.setNegativeButton("아니요!") { dialog, which -> }
                    oDialog2.show()
                }
                move_completeGoal.setOnClickListener {
                    oDialog3.setMessage("완료한 목표로 옮길까요? (되돌릴 수 없습니다.)") // 다이얼로그 제목
                    oDialog3.setCancelable(false) // 다이얼로그 화면 밖 터치 방지
                    oDialog3.setPositiveButton("예") { dialog, which ->
                        val date = Date()
                        val Complete_time = mFormat.format(date)
                        todo_item[position].Complete_time = Complete_time
                        todoViewModel!!.update(todo_item[position])
                        todoViewModel!!.delete(todo_item[position])
                        todoViewModel!!.insert(
                            Todo(
                                todo_item[position].big_Goal,
                                todo_item[position].small_Goal,
                                todo_item[position].stage,
                                todo_item[position].maketime,
                                Complete_time
                            )
                        )
                        try {
                            val start_date = sdf.parse(str_start_date)
                            val getNow = Date(now)
                            val diffDay = (getNow.time - start_date.time) / (24 * 60 * 60 * 1000)
                            val intent = Intent(this@MainContent, GoalLetter::class.java)
                            val intent2 = Intent(this@MainContent, MainActivity::class.java)
                            intent.putExtra("Goalname", todo_item[position].big_Goal.toString())
                            intent.putExtra("diffday", diffDay.toString())
                            startActivity(intent2)
                            startActivity(intent)
                            finish()
                        } catch (e: ParseException) {
                        }
                        Snackbar.make(textView_Big, "완료한 목표로 옮겼습니다.", Snackbar.LENGTH_SHORT).show()
                        menu_action.visibility = View.INVISIBLE
                        user1.visibility = View.INVISIBLE
                        ball1.visibility = View.INVISIBLE
                        textView_Comp_small.visibility = View.INVISIBLE
                        textView_Next_small.visibility = View.INVISIBLE
                        user1.x = big_goal.x
                        ball1.x = user1.x + user1.width
                        Goalback.isEnabled = false
                        completeGoal.isEnabled = false
                        move_completeGoal.isEnabled = false
                        big_goal.isEnabled = false
                        tv_day.visibility = View.VISIBLE
                        tv_day.text = "목표를 이루기 위해 항상 노력하는 당신!"
                        val handler = Handler()
                        handler.postDelayed({
                            try {
                                val start_date = sdf.parse(str_start_date)
                                val getNow = Date(now)
                                val diffDay =
                                    (getNow.time - start_date.time) / (24 * 60 * 60 * 1000)
                                Log.e("생성 날짜 : ", start_date.toString())
                                Log.e("현재 날짜 : ", getNow.toString())
                                tv_day.visibility = View.VISIBLE
                                tv_day.text = """
                                    목표를 이루기 위해 항상 노력하는 당신!
                                    
                                    앞으로도 도전하는 모든 목표를 이뤄가시길 바라겠습니다.
                                    
                                    ${diffDay}일 동안 고생하셨습니다.
                                    """.trimIndent()
                            } catch (e: ParseException) {
                            }
                        }, 2000) // 화면에 2초간 보이기
                    }
                    oDialog3.setNegativeButton("아니요") { dialog, which ->
                        Toast.makeText(
                            applicationContext,
                            "좀 더 고민해보세요!",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                    oDialog3.show()
                }
                menu_action.setOnClickListener {
                    val popup = PopupMenu(this@MainContent, menu_action)
                    val inf = popup.menuInflater
                    inf.inflate(R.menu.menu_action, popup.menu)
                    popup.show()
                    popup.setOnMenuItemClickListener { item ->
                        when (item.itemId) {
                            R.id.item1 -> {
                                oDialog4.setMessage("목표를 삭제하시겠습니까?") // 다이얼로그 제목
                                oDialog4.setCancelable(false) // 다이얼로그 화면 밖 터치 방지
                                oDialog4.setPositiveButton("예") { dialog, which ->
                                    todoViewModel!!.delete(todo_item[position])
                                    val intent = Intent(this@MainContent, MainActivity::class.java)
                                    startActivity(intent)
                                    finish()
                                }
                                oDialog4.setNegativeButton("아니요") { dialog, which ->
                                    Toast.makeText(
                                        applicationContext,
                                        "이 목표를 꼭 이루시길 바랄게요.",
                                        Toast.LENGTH_SHORT
                                    ).show()
                                }
                                oDialog4.show()
                            }
                            R.id.item2 -> {
                                val intent = Intent(this@MainContent, SmallGoalList::class.java)
                                intent.putStringArrayListExtra("smallgoallist", small_goal_data)
                                intent.putExtra("maketime", todo_item[position].maketime)
                                intent.putExtra("position", position)
                                startActivity(intent)
                            }
                        }
                        true
                    }
                    popup.show()
                }
                user1.setOnClickListener {
                    val sdf = SimpleDateFormat("yyyy/MM/dd")
                    val str_start_date = todo_item[position].maketime
                    val now = System.currentTimeMillis()
                    try {
                        val start_date = sdf.parse(str_start_date)
                        val getNow = Date(now)
                        val diffDay = (getNow.time - start_date.time) / (24 * 60 * 60 * 1000)
                        Log.e("생성 날짜 : ", start_date.toString())
                        Log.e("현재 날짜 : ", getNow.toString())
                        tv_day.visibility = View.VISIBLE
                        tv_day.text = diffDay.toString() + "일째 골대를 향해 달리는 중!"
                    } catch (e: ParseException) {
                    }
                    val handler = Handler()
                    handler.postDelayed({ tv_day.visibility = View.INVISIBLE }, 5000) // 화면에 5초간 보이기
                }
                big_goal.setOnClickListener {
                    tv_day.visibility = View.VISIBLE
                    tv_day.text = select_wise
                    val handler = Handler()
                    handler.postDelayed({ tv_day.visibility = View.INVISIBLE }, 7000) // 화면에 7초간 보이기
                }
            } else { /*todo_item 리스트가 비어있는 경우 예외처리하기 위해*/
            }
        }
    } //oncreate

    override fun onWindowFocusChanged(hasFocus: Boolean) {
        super.onWindowFocusChanged(hasFocus)
        val intent = intent
        val position = intent.extras!!.getInt("position")
        val view_layout = findViewById<View>(R.id.view_Layout)
        val big_goal = findViewById<ImageView>(R.id.biggoal)
        val textView_Big = findViewById<TextView>(R.id.view_big_goal)
        val user1 = findViewById<ImageView>(R.id.user1)
        val ball1 = findViewById<ImageView>(R.id.ball1)
        val textView_Big_line = findViewById<ImageView>(R.id.view_big_goal_line)
        val view_big_goal = findViewById<TextView>(R.id.view_big_goal)
        Log.e("뷰 길이:", view_layout.width.toString())
        Log.e("골대까지 길이:", big_goal.x.toString())
        Log.e("캐릭터 위치:", user1.x.toString())
        view_layout.width
        big_goal.x
        user1.x
        ball1.x
        val params = textView_Big_line.layoutParams
        params.width = textView_Big.width
        textView_Big_line.layoutParams = params
        todoViewModel = ViewModelProvider(this).get(TodoViewModel::class.java)
        todoViewModel!!.allTodos.observe(this) {
            try {
                user1.x =
                    big_goal.x / todo_item[position].small_Goal_size * todo_item[position].stage
                ball1.x = user1.x + user1.width
                val percent = (todo_item[position].stage.toDouble()
                        / todo_item[position].small_Goal_size.toDouble() * 100).toInt()
                Log.e("퍼센트 : ", percent.toString())
                if (percent < 10) {
                    textView_Big_line.setColorFilter(Color.rgb(209, 178, 255))
                } else if (percent >= 10 && percent < 20) {
                    textView_Big_line.setColorFilter(Color.rgb(181, 178, 255))
                } else if (percent >= 20 && percent < 30) {
                    textView_Big_line.setColorFilter(Color.rgb(178, 204, 255))
                } else if (percent >= 30 && percent < 40) {
                    textView_Big_line.setColorFilter(Color.rgb(178, 235, 244))
                } else if (percent >= 40 && percent < 50) {
                    textView_Big_line.setColorFilter(Color.rgb(183, 240, 177))
                } else if (percent >= 50 && percent < 60) {
                    textView_Big_line.setColorFilter(Color.rgb(206, 242, 121))
                } else if (percent >= 60 && percent < 70) {
                    textView_Big_line.setColorFilter(Color.rgb(250, 237, 125))
                } else if (percent >= 70 && percent < 80) {
                    textView_Big_line.setColorFilter(Color.rgb(255, 224, 140))
                } else if (percent >= 80 && percent < 90) {
                    textView_Big_line.setColorFilter(Color.rgb(255, 193, 158))
                } else if (percent >= 90 && percent < 100) {
                    textView_Big_line.setColorFilter(Color.rgb(255, 178, 217))
                } else if (percent >= 100) {
                    textView_Big_line.setColorFilter(Color.rgb(255, 167, 167))
                }
            } catch (e: IndexOutOfBoundsException) {
                Log.e("인덱스 오류 ", "ㅇㅋ")
            }
        }
    }

    override fun onBackPressed() {
        val intent = Intent(this@MainContent, MainActivity::class.java)
        startActivity(intent)
        finish()
    }
} //Content5 Activity
