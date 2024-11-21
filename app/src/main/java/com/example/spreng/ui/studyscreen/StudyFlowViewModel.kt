package com.example.spreng.ui.studyscreen

import android.content.Context
import android.content.Intent
import android.speech.tts.TextToSpeech
import android.speech.tts.UtteranceProgressListener
import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.spreng.MainActivity
import com.example.spreng.data.DemoLessonRepository
import com.example.spreng.data.LessonRepository
import com.example.spreng.form.AnswerType
import com.example.spreng.form.ChallengeForm
import com.example.spreng.form.QuestionType
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class StudyFlowViewModel(
    lessonRepository: LessonRepository = DemoLessonRepository(),
    lessonId: Int = 0
) : ViewModel() {

    private val _uiState = MutableStateFlow(StudyFlowUIState())
    val uiState = _uiState.asStateFlow()

    private val lesson: List<ChallengeForm> = lessonRepository.getLesson(lessonId)
    private var currentChallengeIndex = -1

    init {
        nextChallenge()
    }

    fun complete() {
        _uiState.update { state ->
            state.copy(isDone = true)
        }
    }

    fun nextChallenge() {
        currentChallengeIndex++
        if (currentChallengeIndex >= lesson.size) {
            return
        }

        _uiState.update {
            StudyFlowUIState.buildFromChallengeForm(
                lesson[currentChallengeIndex],
                currentChallengeIndex,
                lesson.size
            )
        }
    }

    fun clickUnselectedWord(index: Int) {
        if (_uiState.value.answerUIState is AnswerUIState.WordPickerFilling) {
            clickUnselectedWordOnFilling(index)
        } else if (_uiState.value.answerUIState is AnswerUIState.WordPickerSequence) {
            clickUnselectedWordOnSequence(index)
        }
    }

    private fun clickUnselectedWordOnFilling(index: Int) {
        val answerUIState = _uiState.value.answerUIState as AnswerUIState.WordPickerFilling
        val word = answerUIState.unselectedWords[index]
        val newSentenceUI = answerUIState.sentenceUI.toMutableList()
        var isUpdated = false
        for (i in 0 until newSentenceUI.size) {
            if (newSentenceUI[i] == null) {
                newSentenceUI[i] = SelectedWord(word.word, index)
                isUpdated = true
                break
            }
        }

        if (isUpdated) {
            val newUnselectedWords = answerUIState.unselectedWords.toMutableList()
            newUnselectedWords[index].selected = true

            _uiState.update { state ->
                state.copy(
                    answerUIState = AnswerUIState.WordPickerFilling(
                        sentenceUI = newSentenceUI,
                        unselectedWords = newUnselectedWords
                    )
                )
            }
            Log.d("StudyFlowViewModel", "clickUnselectedWordOnFilling: $newSentenceUI")
        }
    }

    private fun clickUnselectedWordOnSequence(index: Int) {
        val answerUIState = _uiState.value.answerUIState as AnswerUIState.WordPickerSequence
        val word = answerUIState.unselectedWords[index]

        val newSelectedWords = answerUIState.selectedWords + SelectedWord(word.word, index)
        val newUnselectedWords = answerUIState.unselectedWords.toMutableList()
        newUnselectedWords[index].selected = true

        _uiState.update { state ->
            state.copy(
                answerUIState = AnswerUIState.WordPickerSequence(
                    selectedWords = newSelectedWords,
                    unselectedWords = newUnselectedWords
                )
            )
        }
    }


    fun clickSelectedWord(selectedWord: SelectedWord) {
        if (_uiState.value.answerUIState is AnswerUIState.WordPickerFilling) {
            clickSelectedWordOnFilling(selectedWord)
        } else if (_uiState.value.answerUIState is AnswerUIState.WordPickerSequence) {
            clickSelectedWordOnSequence(selectedWord)
        }
    }

    private fun clickSelectedWordOnFilling(selectedWord: SelectedWord) {
        val answerUIState = _uiState.value.answerUIState as AnswerUIState.WordPickerFilling
        val newSentenceUI = answerUIState.sentenceUI.toMutableList()
        for (i in 0 until newSentenceUI.size) {
            if (newSentenceUI[i] == selectedWord) {
                newSentenceUI[i] = null
                break
            }
        }

        val newUnselectedWord = answerUIState.unselectedWords.toMutableList()
        newUnselectedWord[selectedWord.indexInUnselected].selected = false

        _uiState.update { state ->
            state.copy(
                answerUIState = AnswerUIState.WordPickerFilling(
                    sentenceUI = newSentenceUI,
                    unselectedWords = newUnselectedWord
                )
            )
        }
    }

    private fun clickSelectedWordOnSequence(selectedWord: SelectedWord) {
        val answerUIState = _uiState.value.answerUIState as AnswerUIState.WordPickerSequence
        val newSelectedWords = answerUIState.selectedWords.filterNot { it == selectedWord }
        val newUnselectedWords = answerUIState.unselectedWords.toMutableList()
        newUnselectedWords[selectedWord.indexInUnselected].selected = false

        _uiState.update { state ->
            state.copy(
                answerUIState = AnswerUIState.WordPickerSequence(
                    selectedWords = newSelectedWords,
                    unselectedWords = newUnselectedWords
                )
            )
        }
    }

    // Cập nhật câu trả lời dạng viết
    fun updateAnswerWriting(inputUser: String) {
        _uiState.update {
            it.copy(
                answerUIState = AnswerUIState.TextTyping(answerWriting = inputUser)
            )
        }
    }

    fun checkWritingAnswer(rightAnswer: String, userAnswer: String): Boolean{
        val lowQs = rightAnswer.lowercase().replace("\\s+".toRegex(), "").trim()

        val asQs = userAnswer.lowercase().replace("\\s+".toRegex(), "").trim()
        return lowQs == asQs
    }

    fun exit(context: Context) {
        context.startActivity(Intent(context, MainActivity::class.java))
    }

}

data class StudyFlowUIState(
    val title: String = "",
    val learningProgress: Float = 0F,
    val isDone: Boolean = false,
    val questionUIState: QuestionUIState? = null,
    val answerUIState: AnswerUIState? = null
) {
    companion object {
        fun buildFromChallengeForm(
            challengeForm: ChallengeForm,
            challengeIndex: Int,
            totalChallenge: Int
        ) : StudyFlowUIState {
            val questionUIState : QuestionUIState = when (challengeForm.questionType) {
                QuestionType.TEXT -> QuestionUIState.Text(challengeForm.questionContent)
                QuestionType.LISTENING -> QuestionUIState.Listening(challengeForm.questionContent)
          
            }

            val answerUIState : AnswerUIState = when (challengeForm.answerType) {

                AnswerType.WORD_PICKER_FILLING -> {
                    AnswerUIState.WordPickerFilling()
                    val sentenceUI = challengeForm.answer as MutableList<Any?>
                    for (maskedWord in challengeForm.maskedAnswer!!) {
                        for (i in 0 until sentenceUI.size) {
                            if (sentenceUI[i] == maskedWord) {
                                sentenceUI[i] = null
                                break
                            }
                        }
                    }

                    val unselectedWords = challengeForm.maskedAnswer + challengeForm.answerOptions!!
                    unselectedWords.shuffled()
                    AnswerUIState.WordPickerFilling(
                        unselectedWords = unselectedWords.map { UnselectedWord(it) },
                        sentenceUI = sentenceUI
                    )
                }

                AnswerType.WORD_PICKER_SEQUENCE -> AnswerUIState.WordPickerSequence(
                    selectedWords = emptyList(),
                    unselectedWords = (
                            challengeForm.answerOptions!! + challengeForm.answer as List<String>
                    ).shuffled().map { UnselectedWord(it) }
                )

                AnswerType.TYPING -> AnswerUIState.TextTyping(
                    answerWriting = ""
                )

                AnswerType.TALKING -> AnswerUIState.Talking()
            }

            return StudyFlowUIState(
                title = challengeForm.title,
                learningProgress = (challengeIndex + 1).toFloat() / totalChallenge.toFloat(),
                questionUIState = questionUIState,
                answerUIState = answerUIState
            )
        }
    }
}

sealed interface QuestionUIState {
    data class Text(
        val questionContent: String = ""
    ) : QuestionUIState

    class Listening(
        val questionContent: String = ""
    ) : QuestionUIState
}

sealed interface AnswerUIState {
    data class WordPickerFilling(
        val unselectedWords: List<UnselectedWord> = emptyList(),
        var sentenceUI: MutableList<Any?> = mutableListOf()
    ) : AnswerUIState

    data class WordPickerSequence(
        var selectedWords: List<SelectedWord>,
        val unselectedWords: List<UnselectedWord>
    ) : AnswerUIState

    class TextTyping(
        val answerWriting: String
    ) : AnswerUIState

    class Talking(

    ) : AnswerUIState
}

data class UnselectedWord(
    val word: String,
    var selected: Boolean = false
)

data class SelectedWord(
    val word: String,
    val indexInUnselected: Int
)

