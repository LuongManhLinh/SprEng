package com.example.spreng.data.form

data class LessonSummarizationForm(
    val id: Int,
    val title: String,
    val summarization: String,
    val isCompleted: Boolean,
    val rating: Int? = null
)
