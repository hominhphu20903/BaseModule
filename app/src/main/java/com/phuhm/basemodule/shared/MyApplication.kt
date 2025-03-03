package com.phuhm.basemodule.shared

import android.app.Application
import android.content.Context
import com.phuhm.basemodule.data.database.NoteDatabase
import com.phuhm.basemodule.data.repository.NoteRepositoryImpl
import com.phuhm.basemodule.data.repository.QuestionRepositoryImpl
import com.phuhm.basemodule.data.repository.TodoRepositoryImpl

class MyApplication : Application() {
    companion object {
        private lateinit var application: MyApplication
        val context: Context get() = application.applicationContext
    }

    lateinit var todoRepositoryImpl: TodoRepositoryImpl
    lateinit var noteRepositoryImpl: NoteRepositoryImpl
    lateinit var questionRepositoryImpl: QuestionRepositoryImpl

    override fun onCreate() {
        super.onCreate()
        application = this

        todoRepositoryImpl = TodoRepositoryImpl()
        noteRepositoryImpl = NoteRepositoryImpl(NoteDatabase.getDatabase(this).noteDao())
        questionRepositoryImpl = QuestionRepositoryImpl(this)
    }
}