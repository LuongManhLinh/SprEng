package com.example.spreng.ui.studyscreen.answer.wordpicker

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlin.math.pow

class WordPickerSequenceViewModel : BaseWordPickerViewModel() {
    private var _uiState = MutableStateFlow(WordPickerSequenceUIState())
    val uiState = _uiState.asStateFlow()

    init {
        _uiState.value = WordPickerSequenceUIState(
            unselectedWords = List(10) { UnselectedWord("Word ${it.toDouble().pow(5.0)}") }.toMutableList()
        )
    }

    override fun clickUnselectedWord(index: Int) {
        val word = _uiState.value.unselectedWords[index]
        _uiState.update { state ->
            state.copy(
                selectedWords = state.selectedWords + SelectedWord(word.word, index),
                unselectedWords = state.unselectedWords.apply {
                    this[index].selected = true
                }
            )
        }
    }

    override fun clickSelectedWord(selectedWord: SelectedWord) {
        _uiState.update { state ->
            state.copy(
                selectedWords = state.selectedWords.filterNot { it == selectedWord },
                unselectedWords = state.unselectedWords.apply {
                    this[selectedWord.indexInUnselected].selected = false
                }
            )
        }
    }
}

data class WordPickerSequenceUIState(
    val selectedWords: List<SelectedWord> = emptyList(),
    val unselectedWords: List<UnselectedWord> = emptyList()
)

