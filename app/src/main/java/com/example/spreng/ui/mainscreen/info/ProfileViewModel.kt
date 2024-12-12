package com.example.spreng.ui.mainscreen.info

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.spreng.data.database.User
import com.example.spreng.data.repository.LessonBbRepository
import com.example.spreng.data.database.UserApplication
import com.example.spreng.data.preferences.UserManager
import com.example.spreng.data.repository.FollowRepository
import com.example.spreng.data.repository.UserRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch


class ProfileViewModel(
    private val lessonDbRepository: LessonBbRepository,
    private val userRepository: UserRepository,
    private val followRepository: FollowRepository
) : ViewModel() {
    private val _uiState = MutableStateFlow(ProfileData())
    val uiState = _uiState.asStateFlow()
    fun updateProfile(context: Context) {
        viewModelScope.launch {
            val userId = UserManager.getUserId(context)
            val user = userRepository.getUserById(userId).firstOrNull()
            val lessons = lessonDbRepository.getLessonsByUserId(userId).firstOrNull()
            val followers = followRepository.getFollowedUsers(userId)
            val followedUsers = followRepository.getFollowers(userId)

            _uiState.update { uiState ->
                uiState.copy(
                    username = user?.username ?: uiState.username,
                    exp = lessons?.exp ?: uiState.exp,
                    rank = lessons?.rank ?: uiState.rank,
                    top3Count = lessons?.top3Count ?: uiState.top3Count,
                    followers = followers,
                    followedUsers = followedUsers
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
                val followRepository = application.database.followDao()
                val repository2 = FollowRepository(followRepository)
                @Suppress("UNCHECKED_CAST")
                return ProfileViewModel(repository, repository1, repository2) as T
            }
        }
    }
}

data class ProfileData(
    val username: String = "",
    val fullName: String = "",
    val email: String = "@example.com",
    val phoneNumber: String = "0123456789",
    val exp: Int = 0,
    val streak: Int = 0,
    val top3Count: Int = 0,
    val rank: String = "",
    var profilePicture: Int = android.R.drawable.ic_menu_gallery, // Placeholder image resource ID
    val followers: List<User> = emptyList(), // Danh sách người theo dõi
    val followedUsers: List<User> = emptyList() // Danh sách người đã theo dõi
)