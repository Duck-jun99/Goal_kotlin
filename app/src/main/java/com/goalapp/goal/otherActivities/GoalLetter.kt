package com.goalapp.goal.otherActivities

import android.app.Activity
import android.os.Bundle
import android.view.Window
import android.widget.TextView
import com.goalapp.goal.R


class GoalLetter : Activity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        setContentView(R.layout.activity_goal_letter)
        val txt_letter = findViewById<TextView>(R.id.txt_goal_letter)
        val letter1 = resources.getString(R.string.letter_1)
        val letter2 = resources.getString(R.string.letter_2)
        val letter3 = resources.getString(R.string.letter_3)
        val intent = intent
        val diffday = intent.extras!!.getString("diffday")
        val Goal_name = intent.extras!!.getString("Goalname")
        txt_letter.text = "$letter1\n\n\"$Goal_name\"를(을) 이루셨네요!\n\n$diffday$letter2\n\n$letter3\n"
    }
}