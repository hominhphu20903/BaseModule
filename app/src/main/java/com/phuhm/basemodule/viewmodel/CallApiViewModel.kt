package com.phuhm.basemodule.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.phuhm.basemodule.data.model.Todo
import com.phuhm.basemodule.data.repository.TodoRepositoryImpl
import com.phuhm.basemodule.shared.MyApplication
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class CallApiViewModel(private val todoRepositoryImpl: TodoRepositoryImpl) : ViewModel() {
    private val _loading = MutableStateFlow(false)
    val loading = _loading.asStateFlow()

    private val _todos = MutableStateFlow<List<Todo>>(emptyList())
    val todos = _todos.asStateFlow()

    private var job: Job? = null

    init {
        getTodos()
    }

    fun getTodos() {
        job?.cancel()
        job = viewModelScope.launch {
            _loading.emit(true)

            val response = todoRepositoryImpl.getTodos() ?: return@launch
            _todos.emit(response)
        }
        job?.invokeOnCompletion {
            _loading.value = false
        }
    }

    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val noteRepositoryImpl = (this[APPLICATION_KEY] as MyApplication).todoRepositoryImpl
                CallApiViewModel(todoRepositoryImpl = noteRepositoryImpl)
            }
        }
    }
}