package com.example.spreng.ui.mainscreen.home

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlin.math.PI
import kotlin.math.cos

class HomeViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(HomeUiState())
    val uiState = _uiState.asStateFlow()

    fun updateLessonCardState(lessonIdx: Int, newState: LessonCardState) {
        _uiState.update { state ->
            val newLessonList = state.lessonList.toMutableList()
            newLessonList[lessonIdx].cardState = newState
            state.copy(
                lessonList = newLessonList
            )
        }
    }

}

data class HomeUiState(
    val lessonList: List<LessonUI> = LessonUI.getSample(20),
    val userName: String = "Nguyễn Văn A",
    val userXp: Int = 1888,
    val numCompletedLesson: Int = 1,
)

data class LessonUI(
    val id: Int,
    val description: String = "",
    var cardState: LessonCardState = LessonCardState.HIDING,
    var leftWeight: Float = 0.5F,
    var rightWeight: Float = 0.5F
) {
    companion object {
        fun getSample(
            numSample: Int,
            smallestWeight: Double = 0.1,
            omega: Double = PI / 4,
            phi: Double = PI / 2
        ) : List<LessonUI> {

            val sample = List(numSample) { LessonUI(it) }

            val biggestWeight = 1 - smallestWeight

            for (idx in sample.indices) {
                val leftVal = cos(omega * idx + phi)
                val leftWeight = map(leftVal, -1.0, 1.0, smallestWeight, biggestWeight)
                val rightWeight = 1 - leftWeight
                sample[idx].leftWeight = leftWeight.toFloat()
                sample[idx].rightWeight = rightWeight.toFloat()
            }

            return sample
        }

        private fun map(
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