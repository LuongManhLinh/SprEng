package com.example.spreng.ui.mainscreen.home

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.spreng.data.DemoLessonSummarizationRepository
import com.example.spreng.data.LessonRepository
import com.example.spreng.data.LessonSummarizationRepository
import com.example.spreng.form.LessonSummarizationForm
import com.example.spreng.repository.LessonBbRepository
import com.example.spreng.repository.LessonDao
import com.example.spreng.repository.OfflineUserRepository
import com.example.spreng.repository.UserApplication
import com.example.spreng.repository.UserManager
import com.example.spreng.repository.UserRepository
import com.example.spreng.ui.mainscreen.login.SignInViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlin.math.PI
import kotlin.math.cos

class HomeViewModel(
    private val lessonDbRepository: LessonBbRepository,
    private val userRepository: UserRepository,
    lessonSummarizationRepository: LessonSummarizationRepository = DemoLessonSummarizationRepository()
) : ViewModel() {

    private val _uiState = MutableStateFlow(
        HomeUiState(
            lessonList = LessonUIState.buildFromLessonSummarizationForm(
                lessonSummarizationRepository.getAllLessonSummarization()
            ),
        )
    )

    val uiState = _uiState.asStateFlow()

    fun updateAccount(context: Context) {
        viewModelScope.launch {
            val userId = UserManager.getUserId(context)
            val user = userRepository.getUserById(userId).firstOrNull()
            val lessons = lessonDbRepository.getLessonsByUserId(userId).firstOrNull()

            _uiState.update { uiState ->
                uiState.copy(
                    userName = user?.username ?: uiState.userName,
                    userXp = lessons?.exp ?: uiState.userXp,
//                     = lessons?.count { it.lessonIsCompleteNumber > 0 } ?: uiState.numCompletedLesson
                )
            }
        }
    }


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
    companion object {
        val factory: ViewModelProvider.Factory = object : ViewModelProvider.Factory {
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                val application = UserApplication.instance
                val userDao = application.database.userDao()
                val repository1 = OfflineUserRepository(userDao)
                val lessonDao = application.database.lessonDao()
                val repository = LessonBbRepository(lessonDao)
                @Suppress("UNCHECKED_CAST")
                return HomeViewModel(repository, repository1) as T
            }
        }
    }

}

data class HomeUiState(
    val lessonList: List<LessonUIState>,
    val userName: String = "Nguyễn Văn A",
    val userXp: Int = 1888,
    val isProgressBarAppeared: Boolean = false
) {
    val numCompletedLesson: Int
        get() = lessonList.count { it.isCompleted }

}

data class LessonUIState(
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
        ) : List<LessonUIState> {

            val samples =  formList.map {
                LessonUIState(
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