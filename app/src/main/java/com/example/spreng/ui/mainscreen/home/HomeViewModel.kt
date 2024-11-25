package com.example.spreng.ui.mainscreen.home

import androidx.lifecycle.ViewModel
import com.example.spreng.data.DemoLessonSummarizationRepository
import com.example.spreng.data.LessonSummarizationRepository
import com.example.spreng.form.LessonSummarizationForm
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlin.math.PI
import kotlin.math.cos

class HomeViewModel(
    lessonSummarizationRepository: LessonSummarizationRepository = DemoLessonSummarizationRepository()
) : ViewModel() {

    private val _uiState = MutableStateFlow(
        HomeUiState(
            lessonList = LessonUI.buildFromLessonSummarizationForm(
                lessonSummarizationRepository.getAllLessonSummarization()
            )
        )
    )

    val uiState = _uiState.asStateFlow()

    fun onPressChanged(lessonIdx: Int, isPressed: Boolean) {
        val newLessonList = _uiState.value.lessonList.toMutableList()
        var newLessonUI = newLessonList[lessonIdx].copy(isPressed = isPressed)

        if (isPressed) {
            if (newLessonUI.cardState == LessonCardState.HIDING) {
                newLessonUI = newLessonUI.copy(cardState = LessonCardState.OPENING)
            } else if (newLessonUI.cardState == LessonCardState.SHOWING) {
                newLessonUI = newLessonUI.copy(cardState = LessonCardState.CLOSING)
            }
        }

        newLessonList[lessonIdx] = newLessonUI

        _uiState.update {
            it.copy(lessonList = newLessonList)
        }
    }

    fun onCardOpeningCompleted(lessonIdx: Int) {
        val newLessonList = _uiState.value.lessonList.toMutableList()
        newLessonList[lessonIdx] = newLessonList[lessonIdx].copy(cardState = LessonCardState.SHOWING)

        _uiState.update {
            it.copy(lessonList = newLessonList)
        }
    }

    fun onCardClosingCompleted(lessonIdx: Int) {
        val newLessonList = _uiState.value.lessonList.toMutableList()
        newLessonList[lessonIdx] = newLessonList[lessonIdx].copy(cardState = LessonCardState.HIDING)

        _uiState.update {
            it.copy(lessonList = newLessonList)
        }
    }

    fun onProgressBarAppearanceChanged(isAppear: Boolean) {
        _uiState.update {
            it.copy(isProgressBarAppeared = isAppear)
        }
    }

}

data class HomeUiState(
    val lessonList: List<LessonUI>,
    val userName: String = "Nguyễn Văn A",
    val userXp: Int = 1888,
    val isProgressBarAppeared: Boolean = false
) {
    val numCompletedLesson: Int
        get() = lessonList.count { it.isCompleted }
}

data class LessonUI(
    val id: Int,
    val isCompleted: Boolean,
    val title: String = "",
    val summarization: String = "",
    val rating: Int? = null,
    var isPressed: Boolean = false,
    var cardState: LessonCardState = LessonCardState.HIDING,
    var leftWeight: Float = 0.5F,
    var rightWeight: Float = 0.5F
) {
    companion object {
        fun buildFromLessonSummarizationForm(
            formList: List<LessonSummarizationForm>,
            smallestWeight: Double = 0.1,
            omega: Double = PI / 4,
            phi: Double = PI / 2
        ) : List<LessonUI> {

            val samples =  formList.map {
                LessonUI(
                    id = it.id,
                    isCompleted = it.isCompleted,
                    title = it.title,
                    summarization = it.summarization,
                    rating = it.rating
                )
            }

            val biggestWeight = 1 - smallestWeight

            for (idx in samples.indices) {
                val leftVal = cos(omega * idx + phi)
                val leftWeight = map(leftVal, -1.0, 1.0, smallestWeight, biggestWeight)
                val rightWeight = 1 - leftWeight
                samples[idx].leftWeight = leftWeight.toFloat()
                samples[idx].rightWeight = rightWeight.toFloat()
            }

            return samples
        }

        fun map(
            value: Double,
            oldLeft: Double, oldRight: Double,
            newLeft: Double, newRight: Double
        ): Double {
            return newLeft + (value - oldLeft) * (newRight - newLeft) / (oldRight - oldLeft)
        }

    }
}

enum class LessonCardState {
    HIDING,
    OPENING,
    SHOWING,
    CLOSING
}