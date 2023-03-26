package com.goalapp.goal.database

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData

class TodoViewModel(application: Application) : AndroidViewModel(application) {
    private val repostiroy: DBRepository
    val allTodos: LiveData<List<Todo>>

    init {
        repostiroy = DBRepository(application)
        allTodos = repostiroy.allTodos
    }

    fun insert(todo: Todo?) {
        repostiroy.insert(todo)
    }

    fun update(todo: Todo?) {
        repostiroy.update(todo)
    }

    fun delete(todo: Todo?) {
        repostiroy.delete(todo)
    }

    fun plusStage(todo: Todo?) {
        repostiroy.plusStage(todo)
    }

    fun minusStage(todo: Todo?) {
        repostiroy.minusStage(todo)
    }
}