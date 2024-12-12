package com.example.spreng.ui.mainscreen.ranking

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.spreng.data.repository.LessonBbRepository
import com.example.spreng.data.database.UserApplication
import com.example.spreng.data.repository.UserRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.launch

class RankingViewModel(
    private val userRepository: UserRepository,
    private val lessonBbRepository: LessonBbRepository
) : ViewModel() {
    private val _uiState = MutableStateFlow<List<RankingUIState>>(emptyList())
    val uiState = _uiState.asStateFlow()

    fun fetchRanking(rank: String, currentUserId: Long) {
        viewModelScope.launch {
            val otherUsers = userRepository.getOtherUsersByRank(rank, currentUserId).firstOrNull() ?: emptyList()
            val currentUser = userRepository.getCurrentUserWithExp(rank, currentUserId).firstOrNull()

            val rankingList = mutableListOf<RankingUIState>()

            rankingList.addAll(otherUsers.map { user ->
                RankingUIState(
                    id = user.id,
                    username = user.username,
                    xp = user.exp
                )
            })

            if (currentUser != null) {
                rankingList.add(
                    RankingUIState(id = currentUserId, username = currentUser.username, xp = currentUser.exp)
                )
            }
            rankingList.sortByDescending { it.xp }

            _uiState.value = rankingList
        }
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
                return RankingViewModel(repository, repository1) as T
            }
        }
    }
}

data class RankingUIState(
    val id: Long = 0,
    val username: String = "",
    val xp: Int = 0,
)
