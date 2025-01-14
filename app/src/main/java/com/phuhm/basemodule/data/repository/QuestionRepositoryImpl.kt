package com.phuhm.basemodule.data.repository

import android.content.Context
import com.phuhm.basemodule.data.model.Question
import com.phuhm.basemodule.extensions.loadJsonFromAssetsToList


class QuestionRepositoryImpl(private val context: Context) : QuestionRepository {
    override suspend fun getQuestions(): List<Question> {
        return context.loadJsonFromAssetsToList("questions.json", Question::class.java)
    }
}