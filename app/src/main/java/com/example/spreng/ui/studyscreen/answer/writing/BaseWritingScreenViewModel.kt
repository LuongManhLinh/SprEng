package com.example.spreng.ui.studyscreen.answer.writing

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class BaseWritingScreenViewModel : ViewModel() {
    private var _uiState = MutableStateFlow(WritingUIState())
    val uiState = _uiState.asStateFlow()

    fun updateAnswer(answer: String){
        _uiState.update {
            it.copy(
                answerWriting = answer
            )
        }
    }
}

data class WritingUIState(
    val answerWriting: String = ""
)