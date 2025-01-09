package com.phuhm.basemodule.data.repository

import com.phuhm.basemodule.data.model.Todo

interface TodoRepository {
    suspend fun getTodos() : List<Todo>?
}