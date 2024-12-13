package com.example.spreng.ui.mainscreen.revision

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

//mỗi mistake gồm mô tả câu hỏi và câu hỏi
data class Mistake(
    val questionDescription: String,
    val question: String,
    val yourAnswer: String,
    val answer: String
)

class MistakeViewModel : ViewModel() {
    private val _mistakeList = MutableStateFlow(
        listOf(
            Mistake("Chọn đáp án phù hợp nhất", "Why is recycling important?", "It is a waste of time", "It helps reduce waste"),
            Mistake("Nghe và điền lại câu", "Global warming affects everyone", "Global warming changes everyone", "Global warming affects everyone"),
            Mistake("Nghe và sắp xếp lại câu sau", "Reducing plastic waste is important", "Plastic waste is important", "Reducing plastic waste is important"),
            Mistake("Nhắc lại câu sau", "Driving responsibly can save lives", "Driving can't save lives", "Driving responsibly can save lives"),
        )
    )
    val mistakeList: StateFlow<List<Mistake>> = _mistakeList
}