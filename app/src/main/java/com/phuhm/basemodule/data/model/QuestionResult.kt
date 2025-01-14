package com.phuhm.basemodule.data.model

sealed class QuestionResult {
    data object Success: QuestionResult()
    data object Failed: QuestionResult()
}