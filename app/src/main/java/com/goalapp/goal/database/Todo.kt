package com.goalapp.goal.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
class Todo(
    var big_Goal: String,
    var small_Goal: ArrayList<String>,
    var stage: Int,
    var maketime: String,
    var complete_time: String
) {
    @PrimaryKey(autoGenerate = true)
    var id = 0

    fun getSmall_Goal_One(i: Int): String {
        return small_Goal[i]
    }

    val small_Goal_size: Int
        get() = small_Goal.size

    fun setMake_time(Make_time: String) {
        maketime = Make_time
    }

    override fun toString(): String {
        return big_Goal + small_Goal
    }
}