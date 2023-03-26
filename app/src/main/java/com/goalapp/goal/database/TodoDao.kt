package com.goalapp.goal.database

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface TodoDao {
    @Query("SELECT * FROM Todo")
    fun getAll(): LiveData<List<Todo>>

    @get:Query("SELECT * FROM Todo")
    val allList: List<Todo?>?

    @Insert
    fun insert(todo: Todo?)

    @Update
    fun update(todo: Todo?)

    @Delete
    fun delete(todo: Todo?)

    @Query("UPDATE Todo SET stage = stage + 1 WHERE id = :id")
    fun plusStage(id: Int)

    @Query("UPDATE Todo SET stage = stage - 1 WHERE id = :id")
    fun minusStage(id: Int)

    @Query("SELECT FROM Todo WHERE id= id")
    fun getTodoById(id : Long): LiveData<Todo>
}