package com.goalapp.goal.database

import android.app.Application
import android.os.AsyncTask
import androidx.lifecycle.LiveData
import io.reactivex.rxjava3.core.Observable

class DBRepository(application: Application) {

    private val todoDao: TodoDao by lazy{
        val db = AppDatabase.getInstanece(application)!!
        db.todoDao()
    }
    private val todos : LiveData<List<Todo>> by lazy { todoDao.getAll() }
    fun getAll(): LiveData<List<Todo>> {return todos}

    fun getTodoById(id: Long): LiveData<Todo> {
        return todoDao.getTodoById(id)
    }

    fun insert(todo: Todo): Observable<Unit> {
        return Observable.fromCallable { todoDao.insert(todo) }
    }

    fun delete(todo: Todo): Observable<Unit> {
        return Observable.fromCallable { todoDao.delete(todo) }
    }


   /*
    val allTodos: LiveData<List<Todo>>


    init {
        val db: AppDatabase = AppDatabase.getIngAppDatabase(application)
        todoDao = db.todoDao()
        allTodos = todoDao.all
    }

    fun insert(todo: Todo?) {
        InsertTodoAsyncTask(todoDao).execute(todo)
    }

    fun update(todo: Todo?) {
        UpdateTodoAsyncTask(todoDao).execute(todo)
    }

    fun delete(todo: Todo?) {
        DeleteTodoAsyncTask(todoDao).execute(todo)
    }

    fun plusStage(todo: Todo?) {
        PlusStageTodoAsyncTask(todoDao).execute(todo)
    }

    fun minusStage(todo: Todo?) {
        MinusStageTodoAsyncTask(todoDao).execute(todo)
    }

    private class InsertTodoAsyncTask(private val todoDao: TodoDao) :
        AsyncTask<Todo?, Void?, Void?>() {
        protected override fun doInBackground(vararg todos: Todo): Void? {
            todoDao.insert(todos[0])
            return null
        }
    }

    private class UpdateTodoAsyncTask(private val todoDao: TodoDao) :
        AsyncTask<Todo?, Void?, Void?>() {
        protected override fun doInBackground(vararg todos: Todo): Void? {
            todoDao.update(todos[0])
            return null
        }
    }

    private class DeleteTodoAsyncTask(private val todoDao: TodoDao) :
        AsyncTask<Todo?, Void?, Void?>() {
        protected override fun doInBackground(vararg todos: Todo): Void? {
            todoDao.delete(todos[0])
            return null
        }
    }

    private class PlusStageTodoAsyncTask(private val todoDao: TodoDao) :
        AsyncTask<Todo?, Void?, Void?>() {
        protected override fun doInBackground(vararg todos: Todo): Void? {
            todoDao.plusStage(todos[0].id)
            return null
        }
    }

    private class MinusStageTodoAsyncTask(private val todoDao: TodoDao) :
        AsyncTask<Todo?, Void?, Void?>() {
        protected override fun doInBackground(vararg todos: Todo): Void? {
            val id = 0
            todoDao.minusStage(todos[0].id)
            return null
        }
    }
    */
}