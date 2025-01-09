package com.phuhm.basemodule.data.repository

import android.util.Log
import com.phuhm.basemodule.data.model.Todo
import com.phuhm.basemodule.remote.RetrofitInstance

class TodoRepositoryImpl : TodoRepository {
    private val api = RetrofitInstance.apiService

    override suspend fun getTodos(): List<Todo>? {
        return try {
            val response = api.getTodos()
            if (response.isSuccessful && response.body() != null) {
                response.body()
            } else {
                null
            }
        } catch (e: Exception) {
            Log.e("TodoRepositoryImpl", "getTodos: $e")
            null
        }
    }
}