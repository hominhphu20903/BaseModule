package com.phuhm.basemodule.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.phuhm.basemodule.data.model.Question
import com.phuhm.basemodule.data.model.QuestionResult
import com.phuhm.basemodule.data.repository.QuestionRepositoryImpl
import com.phuhm.basemodule.event.JsonEvents
import com.phuhm.basemodule.shared.MyApplication
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class JsonViewModel(private val questionRepositoryImpl: QuestionRepositoryImpl) : ViewModel() {
    private val _question = MutableStateFlow<Question?>(null)
    val question = _question.asStateFlow()

    private val _core = MutableStateFlow(0)
    val core = _core.asStateFlow()

    private val _questionResult = MutableSharedFlow<QuestionResult>()
    val questionResult = _questionResult.asSharedFlow()

    private val _isCompleted = MutableSharedFlow<Boolean>()
    val isCompleted = _isCompleted.asSharedFlow()

    private var questionIndex = 0

    private var job: Job? = null

    init {
        getQuestion()
    }

    fun onEvent(event: JsonEvents) {
        when (event) {
            is JsonEvents.OnSubmitQuestion -> {
                viewModelScope.launch {
                    _question.value?.let { question ->
                        if (event.correctAnswer == question.correctAnswer) {
                            _questionResult.emit(QuestionResult.Success)
                            _core.value = (_core.value + 2).coerceAtMost(10)
                        } else {
                            _questionResult.emit(QuestionResult.Failed)
                        }
                    }
                }
            }

            is JsonEvents.OnNextQuestion -> {
                questionIndex++
                getQuestion()
            }
        }
    }

    private fun getQuestion() {
        job?.cancel()
        job = viewModelScope.launch {
            val questions = questionRepositoryImpl.getQuestions()
            if (questions.isEmpty() || questionIndex >= questions.size) {
                _isCompleted.emit(questionIndex >= questions.size)
                return@launch
            }

            _question.value = questions[questionIndex]
        }
    }

    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val questionRepositoryImpl = (this[APPLICATION_KEY] as MyApplication).questionRepositoryImpl
                JsonViewModel(questionRepositoryImpl = questionRepositoryImpl)
            }
        }
    }
}