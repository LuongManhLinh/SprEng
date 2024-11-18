package com.example.spreng.ui.studyscreen.answer.wordpicker

import androidx.lifecycle.ViewModel

abstract class BaseWordPickerViewModel : ViewModel() {
    abstract fun clickUnselectedWord(index: Int)
    abstract fun clickSelectedWord(selectedWord: SelectedWord)
}

data class SelectedWord(
    val word: String,
    val indexInUnselected: Int
)

data class UnselectedWord(
    val word: String,
    var selected: Boolean = false
)