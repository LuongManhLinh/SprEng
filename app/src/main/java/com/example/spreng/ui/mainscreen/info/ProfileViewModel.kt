package com.example.spreng.ui.mainscreen.info

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.spreng.repository.LessonBbRepository
import com.example.spreng.database.UserApplication
import com.example.spreng.preferences.UserManager
import com.example.spreng.repository.UserRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

data class ProfileData(
    val username: String = "",
    val fullName: String = "",
    val email: String = "@example.com",
    val phoneNumber: String = "0123456789",
    val exp: Int = 0,
    val streak: Int = 0,
    val top3Count: Int = 0,
    val rank: String = "",
    var profilePicture: Int = android.R.drawable.ic_menu_gallery // Placeholder image resource ID
)
class ProfileViewModel(
    private val lessonDbRepository: LessonBbRepository,
    private val userRepository: UserRepository,
) : ViewModel() {
    private val _uiState = MutableStateFlow(ProfileData())
    val uiState = _uiState.asStateFlow()
    fun updateProfile(context: Context) {
        viewModelScope.launch {
            val userId = UserManager.getUserId(context)
            val user = userRepository.getUserById(userId).firstOrNull()
            val lessons = lessonDbRepository.getLessonsByUserId(userId).firstOrNull()
            _uiState.update { uiState ->
                uiState.copy(
                    username = user?.username ?: uiState.username,
                    exp = lessons?.exp ?: uiState.exp,
                    rank = lessons?.rank ?: uiState.rank,
                    top3Count = lessons?.top3Count ?: uiState.top3Count
                )
            }
        }
    }
    companion object {
        val factory: ViewModelProvider.Factory = object : ViewModelProvider.Factory {
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                val application = UserApplication.instance
                val userDao = application.database.userDao()
                val repository1 = UserRepository(userDao)
                val lessonDao = application.database.lessonDao()
                val repository = LessonBbRepository(lessonDao)
                @Suppress("UNCHECKED_CAST")
                return ProfileViewModel(repository, repository1) as T
            }
        }
    }
}