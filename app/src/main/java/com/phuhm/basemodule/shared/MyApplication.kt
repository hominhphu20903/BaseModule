package com.phuhm.basemodule.shared

import android.app.Application
import com.phuhm.basemodule.data.database.NoteDatabase
import com.phuhm.basemodule.data.repository.NoteRepositoryImpl
import com.phuhm.basemodule.data.repository.QuestionRepositoryImpl
import com.phuhm.basemodule.data.repository.TodoRepositoryImpl

class MyApplication : Application() {
    companion object {
        private var instance: MyApplication? = null

        fun getInstance(): MyApplication {
            return instance ?: throw IllegalStateException("Application instance not initialized")
        }
    }

    lateinit var todoRepositoryImpl: TodoRepositoryImpl
    lateinit var noteRepositoryImpl: NoteRepositoryImpl
    lateinit var questionRepositoryImpl: QuestionRepositoryImpl

    override fun onCreate() {
        super.onCreate()
        instance = this

        todoRepositoryImpl = TodoRepositoryImpl()
        noteRepositoryImpl = NoteRepositoryImpl(NoteDatabase.getDatabase(this).noteDao())
        questionRepositoryImpl = QuestionRepositoryImpl(this)
    }
}