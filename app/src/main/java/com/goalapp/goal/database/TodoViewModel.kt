package com.goalapp.goal.database

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.schedulers.Schedulers

class TodoViewModel(application: Application) : AndroidViewModel(application) {

    private val disposable: CompositeDisposable = CompositeDisposable()
    private val repository: DBRepository by lazy {
        DBRepository(application)
    }
    private val todos: LiveData<List<Todo>> by lazy {
        repository.getAll()
    }
    fun getAll() = todos

    fun getTodoById(id: Long): LiveData<Todo> {
        return repository.getTodoById(id)
    }

    fun insert(todo: Todo, next: () -> Unit) {
        disposable.add( repository.insert(todo).subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe { next() }
        )
    }

    fun delete(todo: Todo, next: () -> Unit) {
        disposable.add( repository.delete(todo).subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe { next() }
        )
    }

    override fun onCleared() {
        super.onCleared()
        disposable.dispose()
    }



    /*
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
     */
}