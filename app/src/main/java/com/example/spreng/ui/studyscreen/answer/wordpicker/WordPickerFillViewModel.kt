package com.example.spreng.ui.studyscreen.answer.wordpicker

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class WordPickerFillViewModel : BaseWordPickerViewModel() {
    private val _uiState = MutableStateFlow(WordPickerFillUIState())
    val uiState = _uiState.asStateFlow()

    init {
        _uiState.value = WordPickerFillUIState(
            unselectedWords = listOf(
                UnselectedWord("this"),
                UnselectedWord("these"),
                UnselectedWord("an"),
                UnselectedWord("sentence"),
                UnselectedWord("expensive"),
                UnselectedWord("beautiful"),
            ),
            sentenceUI = mutableListOf(null, " is ", " a ", null, "sentence")
        )
    }

    override fun clickUnselectedWord(index: Int) {
        val word = _uiState.value.unselectedWords[index]
        _uiState.update { state ->
            val newSentenceUI = state.sentenceUI.toMutableList()
            var isUpdated = false
            for (i in 0 until newSentenceUI.size) {
                if (newSentenceUI[i] == null) {
                    newSentenceUI[i] = SelectedWord(word.word, index)
                    isUpdated = true
                    break
                }
            }

            if (isUpdated) {
                state.copy(
                    sentenceUI = newSentenceUI,
                    unselectedWords = state.unselectedWords.mapIndexed { idx, unselectedWord ->
                        if (idx == index) unselectedWord.copy(selected = true) else unselectedWord
                    }
                )
            } else {
                state
            }
        }
    }

    override fun clickSelectedWord(selectedWord: SelectedWord) {
        _uiState.update { state ->
            val newSentenceUI = state.sentenceUI.toMutableList()
            for (i in 0 until newSentenceUI.size) {
                if (newSentenceUI[i] == selectedWord) {
                    newSentenceUI[i] = null
                    break
                }
            }

            state.copy(
                sentenceUI = newSentenceUI,
                unselectedWords = state.unselectedWords.mapIndexed { idx, unselectedWord ->
                    if (idx == selectedWord.indexInUnselected) unselectedWord.copy(selected = false) else unselectedWord
                }
            )
        }
    }

}

data class WordPickerFillUIState(
    val sentenceUI: MutableList<Any?> = mutableListOf(),
    val unselectedWords: List<UnselectedWord> = emptyList()
)


