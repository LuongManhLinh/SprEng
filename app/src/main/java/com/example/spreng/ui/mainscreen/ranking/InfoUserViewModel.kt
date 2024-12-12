package com.example.spreng.ui.mainscreen.ranking

import android.content.Context
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.spreng.data.database.User
import com.example.spreng.data.database.UserApplication
import com.example.spreng.data.preferences.UserManager
import com.example.spreng.data.repository.FollowRepository
import com.example.spreng.data.repository.LessonBbRepository
import com.example.spreng.data.repository.UserRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class InfoUserViewModel(
    private val userRepository: UserRepository,
    private val lessonRepository: LessonBbRepository,
    private val followRepository: FollowRepository
) : ViewModel() {
    private val _userDetail = MutableStateFlow(UserDetail())
    val userDetail = _userDetail.asStateFlow()

    private val _isLoading = MutableStateFlow(false)
    val isLoading = _isLoading.asStateFlow()

    fun fetchUserDetail(context: Context, userId: Long) {
        viewModelScope.launch {
            _isLoading.value = true
            try {
                val currentUserId = UserManager.getUserId(context)
                val user = userRepository.getUserById(userId).firstOrNull()
                val lessons = lessonRepository.getLessonsByUserId(userId).firstOrNull()

                val followers = followRepository.getFollowedUsers(userId)
                val followedUsers = followRepository.getFollowers(userId)

                val isUserFollowing = followRepository.isUserFollowing(currentUserId, userId)
                if (user != null) {
                    _userDetail.update {
                        it.copy(
                            username = user.username,
                            email = user.email,
                            xp = lessons?.exp ?: 0,
                            streak = lessons?.streak ?: 0,
                            rank = lessons?.rank ?: "Bronze",
                            top3Count = lessons?.top3Count ?: 0,
                            followers = followers,
                            followedUsers = followedUsers,
                            isFollow = isUserFollowing
                        )
                    }
                    Log.i("duoc theo doi trong fetch", followedUsers.size.toString())

                }
            } finally {
                _isLoading.value = false
            }
        }
    }

    fun addFollow(context: Context, followedId: Long) {
        viewModelScope.launch {
            val followerId = UserManager.getUserId(context)
            if (followerId == followedId || _isLoading.value) return@launch

            _isLoading.value = true
            try {
                followRepository.followUser(followerId, followedId)
                val updatedFollowers = followRepository.getFollowedUsers(followedId) // Cập nhật lại danh sách người theo dõi
                val updatedFollowedUsers = followRepository.getFollowers(followedId)
                fetchUserDetail(context, followedId)
                _userDetail.update { it.copy(
                    followers = updatedFollowers,
                    followedUsers = updatedFollowedUsers,
                    isFollow = true
                ) }
            } finally {
                _isLoading.value = false
            }
        }
    }

    fun unfollow(context: Context, visitedUserId: Long) {
        viewModelScope.launch {
            val currentUserId = UserManager.getUserId(context)
            if (currentUserId == visitedUserId || _isLoading.value) return@launch

            _isLoading.value = true
            try {
                Log.i("hehehe", "134")
                followRepository.unfollowUser(currentUserId, visitedUserId)
                val updatedFollowers = followRepository.getFollowedUsers(visitedUserId) // Cập nhật lại danh sách người theo dõi
                val updatedFollowedUsers = followRepository.getFollowers(visitedUserId)
                Log.i("duoc theo doi", updatedFollowers.size.toString())
                fetchUserDetail(context, visitedUserId)
                _userDetail.update { it.copy(
                    followers = updatedFollowers,
                    followedUsers = updatedFollowedUsers,
                    isFollow = false
                ) }
            } finally {
                _isLoading.value = false
            }
        }
    }

    companion object {
        val factory: ViewModelProvider.Factory = object : ViewModelProvider.Factory {
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                val application = UserApplication.instance
                val userDao = application.database.userDao()
                val userRepository = UserRepository(userDao)
                val lessonDao = application.database.lessonDao()
                val lessonRepository = LessonBbRepository(lessonDao)
                val followDao = application.database.followDao()
                val followRepository = FollowRepository(followDao)
                @Suppress("UNCHECKED_CAST")
                return InfoUserViewModel(userRepository, lessonRepository, followRepository) as T
            }
        }
    }
}

data class UserDetail(
    val username: String = "",
    val email: String = "",
    val xp: Int = 0,
    val streak: Int = 0,
    val rank: String = "",
    val top3Count: Int = 0,
    val followers: List<User> = emptyList(), // Danh sách người theo dõi
    val followedUsers: List<User> = emptyList(), // Danh sách người đã theo dõi
    val isFollow: Boolean = false
)
