package com.phuhm.basemodule.data.repository

import com.phuhm.basemodule.data.model.Question

interface QuestionRepository {
    suspend fun getQuestions() : List<Question>
}