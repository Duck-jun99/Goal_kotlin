package com.goalapp.goal.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "TODO")
data class Todo(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "Id") var id: Long = 0,
    @ColumnInfo(name = "big_Goal") var big_Goal: String,
    @ColumnInfo(name = "small_Goal") var small_Goal: ArrayList<String>,
    @ColumnInfo(name = "stage") var stage: Int,
    @ColumnInfo(name = "make_time") var make_time: String,
    @ColumnInfo(name = "complete_time") var complete_time: String = "") {

    fun getId(): Int {
        return id.toInt()
    }

    fun setId(id: Int) {
        this.id = id.toLong()
    }

    fun get_big_Goal(): String? {
        return big_Goal
    }

    fun set_big_Goal(big_Goal: String) {
        this.big_Goal = big_Goal
    }

    fun get_small_Goal(): ArrayList<String> {
        return small_Goal
    }

    fun get_small_Goal_One(i: Int): String? {
        return small_Goal.get(i)
    }

    fun get_small_Goal_size(): Int {
        return small_Goal.size
    }



    fun getStage(): Int {
        return stage
    }

    fun setStage(stage: Int) {
        this.stage = stage
    }

    fun get_maketime(): String? {
        return make_time
    }


    fun get_complete_time(): String? {
        return complete_time
    }

    override fun toString(): String {
        return big_Goal + small_Goal
    }


}