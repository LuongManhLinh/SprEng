package com.example.spreng.ui.studyscreen

import android.content.Context
import android.content.Intent
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.spreng.MainActivity
import com.example.spreng.data.repository.DemoLessonRepository
import com.example.spreng.data.repository.LessonRepository
import com.example.spreng.data.database.UserApplication
import com.example.spreng.data.form.AnswerType
import com.example.spreng.data.form.ChallengeForm
import com.example.spreng.data.form.QuestionType
import com.example.spreng.data.repository.LessonBbRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class StudyFlowViewModel(
    private val lessonBbRepository: LessonBbRepository,
    private val lessonRepository: LessonRepository = DemoLessonRepository(),
) : ViewModel() {

    private lateinit var lesson: List<ChallengeForm>

    private var currentChallengeIndex = 0
    private var numCorrect = 0
    private val _uiState = MutableStateFlow(
        StudyFlowUIState.buildFromChallengeForm(
            ChallengeForm(
                title = "Nghe và điền lại câu",
                questionContent = "Water is essential for life",
                questionType = QuestionType.LISTENING,
                answer = "Water is essential for life",
                answerType = AnswerType.TYPING
            ),
            0,
            0
        )
    )
    val uiState = _uiState.asStateFlow()

    private var canChangePopupVisibility = true
    private var _popupUiState = MutableStateFlow(PopupUIState())
    val popupUiState = _popupUiState.asStateFlow()

    fun initialize(lessonId: Int) {
        if (!this::lesson.isInitialized) { // Chỉ khởi tạo nếu chưa được khởi tạo
            lesson = lessonRepository.getLesson(lessonId)
            _uiState.value = StudyFlowUIState.buildFromChallengeForm(
                lesson[currentChallengeIndex],
                currentChallengeIndex,
                lesson.size
            )
        }
    }

    fun complete() {

        var isCorrect = true
        val currentLesson = lesson[currentChallengeIndex]

        when (val answerUIState = _uiState.value.answerUIState) {

            is AnswerUIState.WordPickerFilling -> {
                isCorrect = checkWordPickerFillingAnswer(
                    answerUIState.sentenceUI,
                    currentLesson.answer as List<String>
                )
            }

            is AnswerUIState.WordPickerSequence -> {
                isCorrect = checkWordPickerSequenceAnswer(
                    answerUIState.selectedWords,
                    currentLesson.answer as List<String>
                )
            }

            is AnswerUIState.Talking -> {
                isCorrect = checkTalkingAnswer(
                    currentLesson.answer as String,
                    answerUIState.answerTalking
                )
            }

            is AnswerUIState.TextTyping -> {
                isCorrect = checkWritingAnswer(
                    currentLesson.questionContent,
                    answerUIState.answerWriting
                )
            }

            is AnswerUIState.MultiChoice -> {
                isCorrect = answerUIState.selectedIdx == currentLesson.answer
            }
        }

        val correctAnswer = if (isCorrect) {
            null
        } else {
            when (currentLesson.answerType) {
                AnswerType.TYPING -> currentLesson.answer as String
                AnswerType.WORD_PICKER_FILLING -> {
                    (currentLesson.answer as List<*>).joinToString("")
                }

                AnswerType.WORD_PICKER_SEQUENCE -> {
                    (currentLesson.answer as List<*>).joinToString(" ")
                }

                AnswerType.TALKING -> {
                    currentLesson.answer as String
                }

                AnswerType.MULTI_CHOICE -> {
                    currentLesson.answerOptions!![currentLesson.answer as Int]
                }
            }
        }

        _uiState.update {
            it.copy(
                isDone = true
            )
        }

        _popupUiState.update {
            it.copy(
                isVisible = true,
                isCorrect = isCorrect,
                correctAnswer = correctAnswer
            )
        }

        if (isCorrect) {
            numCorrect++
        }
    }


    fun nextChallenge() {
        currentChallengeIndex++
        if (currentChallengeIndex >= lesson.size) {
            _uiState.update {
                it.copy(
                    isLessonDone = true,
                    numCorrect = numCorrect,
                    totalChallenge = lesson.size
                )
            }
            return
        }

        if (_popupUiState.value.isVisible) {
            _popupUiState.update {
                it.copy(
                    isVisible = false
                )
            }
        }

        _uiState.update {
            StudyFlowUIState.buildFromChallengeForm(
                lesson[currentChallengeIndex],
                currentChallengeIndex,
                lesson.size,
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

    // cập nhật câu trả lời dạng nói
    fun updateAnswerTalking(input: String) {
        _uiState.update {
            it.copy(
                answerUIState = AnswerUIState.Talking(answerTalking = input)
            )
        }
    }

    fun updateAnswerMultiChoice(selectedIdx: Int) {
        val oldAnswerUIState = _uiState.value.answerUIState as AnswerUIState.MultiChoice

        val idxToUpdate = if (selectedIdx == oldAnswerUIState.selectedIdx) {
            null
        } else {
            selectedIdx
        }

        _uiState.update {
            it.copy(
                answerUIState = AnswerUIState.MultiChoice(
                    choices = oldAnswerUIState.choices,
                    selectedIdx = idxToUpdate
                )
            )
        }
    }


    fun changeResultPopupVisibility() {
        if (canChangePopupVisibility) {
            _popupUiState.update {
                it.copy(
                    isVisible = !it.isVisible
                )
            }
            canChangePopupVisibility = false
            viewModelScope.launch {
                delay(500)
                canChangePopupVisibility = true
            }
        }
    }


    private fun checkWordPickerFillingAnswer(
        sentenceUI: MutableList<Any?>,
        answer: List<String>,
    ): Boolean {
        for (i in sentenceUI.indices) {
            val word = sentenceUI[i]
            if (word == null) {
                return false
            } else if (word is SelectedWord) {
                if (word.word != answer[i]) {
                    return false
                }
            }
        }
        return true
    }

    private fun checkWordPickerSequenceAnswer(
        selectedWords: List<SelectedWord>,
        answer: List<String>,
    ): Boolean {
        if (selectedWords.size != answer.size) {
            return false
        }
        for (i in selectedWords.indices) {
            if (selectedWords[i].word != answer[i]) {
                return false
            }
        }
        return true
    }

    private fun checkWritingAnswer(rightAnswer: String, userAnswer: String): Boolean {
        val lowQs = rightAnswer.lowercase().replace("\\s+".toRegex(), "").trim()
        val asQs = userAnswer.lowercase().replace("\\s+".toRegex(), "").trim()
        return lowQs == asQs
    }

    // kiểm tra câu trả lời dạng nói với đáp án đúng
    private fun checkTalkingAnswer(rightAnswer: String, userAnswer: String): Boolean {
        return rightAnswer.lowercase() == userAnswer.lowercase()
    }

    fun updateCompletedLesson(userId: Long, lessonId: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            val lessons = lessonBbRepository.getLessonsByUserId(userId).firstOrNull()

            if (lessons?.numCompletedLessons == lessonId) {
                val newCompletedLessons = (lessons.numCompletedLessons) + 1
                lessonBbRepository.updateCompletedLessonCount(userId, newCompletedLessons)
            }
            val newXp = _uiState.value.numCorrect * 10 + (lessons?.exp ?: 0)
            lessonBbRepository.updateUserXp(userId, newXp)
        }
    }

    fun exit(context: Context) {
        context.startActivity(Intent(context, MainActivity::class.java))
    }

    companion object {
        val factory: ViewModelProvider.Factory = object : ViewModelProvider.Factory {
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                val application = UserApplication.instance
                val lessonDao = application.database.lessonDao()
                val repository = LessonBbRepository(lessonDao)
                @Suppress("UNCHECKED_CAST")
                return StudyFlowViewModel(repository) as T
            }
        }
    }

}

data class StudyFlowUIState(
    val currentLesson: Int = 1,
    val title: String = "",
    val learningProgress: Float = 0F,
    val questionUIState: QuestionUIState,
    val answerUIState: AnswerUIState,
    val isDone: Boolean = false,
    val isLessonDone: Boolean = false,
    val numCorrect: Int = 0,
    val totalChallenge: Int = 0,
) {
    companion object {
        fun buildFromChallengeForm(
            challengeForm: ChallengeForm,
            challengeIndex: Int,
            totalChallenge: Int,
        ): StudyFlowUIState {
            val questionUIState: QuestionUIState = when (challengeForm.questionType) {
                QuestionType.TEXT -> QuestionUIState.Text(challengeForm.questionContent)
                QuestionType.LISTENING -> QuestionUIState.Listening(challengeForm.questionContent)
            }

            val answerUIState: AnswerUIState = when (challengeForm.answerType) {

                AnswerType.WORD_PICKER_FILLING -> {
                    AnswerUIState.WordPickerFilling()
                    val sentenceUI = (challengeForm.answer as List<Any?>).toMutableList()
                    for (maskedWord in challengeForm.maskedAnswer!!) {
                        for (i in sentenceUI.indices) {
                            if (sentenceUI[i] == maskedWord) {
                                sentenceUI[i] = null
                                break
                            }
                        }
                    }

                    val unselectedWords = (challengeForm.maskedAnswer
                            + challengeForm.answerOptions!!).shuffled()

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

                AnswerType.TALKING -> AnswerUIState.Talking(
                    answerTalking = ""
                )

                AnswerType.MULTI_CHOICE -> AnswerUIState.MultiChoice(
                    choices = challengeForm.answerOptions!!,
                    selectedIdx = null
                )
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
        val questionContent: String = "",
    ) : QuestionUIState

    class Listening(
        val questionContent: String = "",
    ) : QuestionUIState
}

sealed interface AnswerUIState {
    data class WordPickerFilling(
        val unselectedWords: List<UnselectedWord> = emptyList(),
        var sentenceUI: MutableList<Any?> = mutableListOf(),
    ) : AnswerUIState

    data class WordPickerSequence(
        var selectedWords: List<SelectedWord>,
        val unselectedWords: List<UnselectedWord>,
    ) : AnswerUIState

    data class TextTyping(
        val answerWriting: String,
    ) : AnswerUIState

    data class Talking(
        val answerTalking: String,
    ) : AnswerUIState

    data class MultiChoice(
        val choices: List<String> = emptyList(),
        val selectedIdx: Int? = null
    ) : AnswerUIState
}

data class PopupUIState(
    val isVisible: Boolean = false,
    val isCorrect: Boolean = false,
    val correctAnswer: String? = null,
)

data class UnselectedWord(
    val word: String,
    var selected: Boolean = false,
)

data class SelectedWord(
    val word: String,
    val indexInUnselected: Int,
)


