package com.phuhm.basemodule.data.model

data class Question(
    val question: String,
    val options: List<String>,
    val correctAnswer: Int
)
