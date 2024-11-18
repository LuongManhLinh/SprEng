package com.example.spreng.ui.mainscreen.revision

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

//mỗi mistake gồm mô tả câu hỏi và câu hỏi
data class Mistake(
    val questionDescription: String,
    val question: String
)

class MistakeViewModel : ViewModel() {
    private val _mistakeList = MutableStateFlow(
        listOf(
            Mistake("Mô tả câu hỏi", "This is a fucking cat"),
            Mistake("Mô tả câu hỏi", "This is a fucking cat"),
            Mistake("Mô tả câu hỏi", "This is a fucking cat"),
            Mistake("Mô tả câu hỏi", "This is a fucking cat"),
            Mistake("Mô tả câu hỏi", "This is a fucking cat"),
            Mistake("Mô tả câu hỏi", "This is a fucking cat"),
            Mistake("Mô tả câu hỏi", "This is a fucking cat"),
            Mistake("Mô tả câu hỏi", "This is a fucking cat"),
            Mistake("Mô tả câu hỏi", "This is a fucking cat"),
            Mistake("Mô tả câu hỏi", "This is a fucking cat"),
            Mistake("Mô tả câu hỏi", "This is a fucking cat"),
            Mistake("Mô tả câu hỏi", "This is a fucking cat"),
        )
    )
    val mistakeList: StateFlow<List<Mistake>> = _mistakeList
}