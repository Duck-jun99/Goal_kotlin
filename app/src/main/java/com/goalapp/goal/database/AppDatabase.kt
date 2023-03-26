package com.goalapp.goal.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters

@Database(entities = [Todo::class], version = 1)
@TypeConverters(*[Converters::class])
abstract class AppDatabase : RoomDatabase() {
    abstract fun todoDao(): TodoDao

    companion object {
        private var INSTANCE: AppDatabase? = null

        fun getInstanece(context: Context): AppDatabase? {
        return INSTANCE ?: synchronized(AppDatabase::class) {
            INSTANCE ?: Room.databaseBuilder(context.applicationContext,
                AppDatabase::class.java, "Goal.db").build().also { INSTANCE = it } }
        }
        fun destoryInstance() {
            INSTANCE = null
        }
    }

    protected fun doInBackground(vararg voids: Void): Void? {
        var ex = ArrayList<String>()
        ex.add("목표 달성을 누르면 다음 단계로 넘어갈 수 있어요.")
        ex.add("마지막 단계에서 목표 달성을 누르면 목표를 완료할 수 있습니다.")
        todoDao().insert(
            Todo(0, "예제", ex, 0, "2023/03/01")
        )
        return null
    }
}

    /*
    private class PopulateDbAsyncTask(db: AppDatabase) : AsyncTask<Void?, Void?, Void?>() {
        private val todoDao: TodoDao

        init {
            todoDao = db.todoDao()
        }

        protected override fun doInBackground(vararg voids: Void): Void? {
            val ex: ArrayList<*> = ArrayList<Any?>()
            ex.add("목표 달성을 누르면 다음 단계로 넘어갈 수 있어요.")
            ex.add("마지막 단계에서 목표 달성을 누르면 목표를 완료할 수 있습니다.")
            todoDao.insert(
                Todo(
                    "목표 예제", ex,
                    0, "2021/01/01", null
                )
            )
            return null
        }
    }


    companion object {
        private var INSTANCE: AppDatabase? = null

        @Synchronized
        fun getIngAppDatabase(context: Context?): AppDatabase {
            if (INSTANCE == null) {
                synchronized(AppDatabase::class) {
                    INSTANCE = Room.databaseBuilder(
                        context.applicationContext,
                        AppDatabase::class.java, "Goal.db"
                    )
                        .fallbackToDestructiveMigration()
                        .build()
                    return INSTANCE!!
                }

                fun destroyInstance() {
                    INSTANCE = null
                }
            }
        }
    }
    */
