package com.example.spreng.ui.mainscreen.ranking

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.spreng.database.UserApplication
import com.example.spreng.repository.LessonBbRepository
import com.example.spreng.repository.UserRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.launch

class InfoUserViewModel(
    private val userRepository: UserRepository,
    private val lessonRepository: LessonBbRepository
) : ViewModel() {
    private val _userDetail = MutableStateFlow<UserDetail?>(null)
    val userDetail = _userDetail.asStateFlow()

    fun fetchUserDetail(userId: Long) {
        viewModelScope.launch {
            val user = userRepository.getUserById(userId).firstOrNull()
            val lessons = lessonRepository.getLessonsByUserId(userId).firstOrNull()
            if (user != null) {
                _userDetail.value = UserDetail(
                    username = user.username,
                    email = user.email,
                    xp = lessons?.exp ?: 0,
                    streak = lessons?.streak ?: 0,
                    rank = lessons?.rank ?: "Bronze",
                    top3Count = lessons?.top3Count ?: 0,
                )
            }
        }
        Log.i("Userdetail", _userDetail.value.toString())
    }
    companion object {
        val factory: ViewModelProvider.Factory = object : ViewModelProvider.Factory {
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                val application = UserApplication.instance
                val userDao = application.database.userDao()
                val repository = UserRepository(userDao)
                val lessonDao = application.database.lessonDao()
                val repository1 = LessonBbRepository(lessonDao)
                @Suppress("UNCHECKED_CAST")
                return InfoUserViewModel(repository, repository1) as T
            }
        }
    }
}

data class UserDetail(
    val username: String,
    val email: String,
    val xp: Int,
    val streak: Int,
    val rank: String,
    val top3Count: Int,
)
