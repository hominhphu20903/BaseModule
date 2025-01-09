package com.phuhm.basemodule.remote

import com.phuhm.basemodule.data.model.Todo
import retrofit2.Response
import retrofit2.http.GET

interface ApiService {
    @GET("todos")
    suspend fun getTodos(): Response<List<Todo>>
}
