package com.example.spreng.ui.mainscreen.login

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.spreng.repository.Lesson
import com.example.spreng.repository.LessonBbRepository
import com.example.spreng.repository.OfflineUserRepository
import com.example.spreng.repository.User
import com.example.spreng.repository.UserApplication
import com.example.spreng.repository.UserManager
import com.example.spreng.repository.UserRepository
import com.example.spreng.repository.UserViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class SignUpViewModel(
    private val userRepository: UserRepository,
    private val lessonBbRepository: LessonBbRepository
) : ViewModel() {
    private val _uiState = MutableStateFlow(SignUpUIState())
    val uiState = _uiState.asStateFlow()

    fun updateUsername(username: String) {
        _uiState.update {
            it.copy(
                username = username
            )
        }
    }

    fun updateEmail(email: String) {
        _uiState.update {
            it.copy(
                email = email
            )
        }
    }

    fun updatePassword(password: String) {
        _uiState.update {
            it.copy(
                password = password
            )
        }
    }
    sealed class SignUpState {
        object Loading : SignUpState()
        data class Success(val success: Boolean) : SignUpState()
        data class Error(val error: String) : SignUpState()
    }

    fun signUp() {
        viewModelScope.launch(Dispatchers.IO) {
            val userId = userRepository.insertUser(
                User(
                    username = _uiState.value.username,
                    email = _uiState.value.email,
                    password = _uiState.value.password
                )
            )
            lessonBbRepository.insertUser(
                Lesson(
                    userId = userId,
                    lessonIsCompleteNumber = 0,
                    exp = 0,
                    streak = 0,
                    rank = "Đồng",
                    top3Count = 0
                )
            )
        }
    }

    companion object {
        val factory: ViewModelProvider.Factory = object : ViewModelProvider.Factory {
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                val application = UserApplication.instance
                val userDao = application.database.userDao()
                val repository = OfflineUserRepository(userDao)
                val lessonDao = application.database.lessonDao()
                val repository1 = LessonBbRepository(lessonDao)
                @Suppress("UNCHECKED_CAST")
                return SignUpViewModel(repository, repository1) as T
            }
        }
    }
}

data class SignUpUIState(
    val username: String = "",
    val email: String = "",
    val password: String = "",
)