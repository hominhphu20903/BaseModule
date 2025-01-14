package com.phuhm.basemodule.event


sealed class JsonEvents {
    data class OnSubmitQuestion(val correctAnswer: Int) : JsonEvents()
    data object OnNextQuestion : JsonEvents()
}