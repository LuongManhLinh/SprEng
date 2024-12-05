package com.example.spreng.ui.mainscreen.login

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.spreng.repository.OfflineUserRepository
import com.example.spreng.repository.User
import com.example.spreng.repository.UserApplication
import com.example.spreng.repository.UserManager
import com.example.spreng.repository.UserRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class SignInViewModel(private val userRepository: UserRepository) : ViewModel() {
    private val _uiState = MutableStateFlow(SignInUIState())
    val uiState = _uiState.asStateFlow()
    private val _signInState = MutableStateFlow<SignInState>(SignInState.Loading)
    val signInState = _signInState.asStateFlow()

    fun updateUsername(username: String) {
        _uiState.update {
            it.copy(
                username = username
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

    sealed class SignInState {
        object Loading : SignInState()
        data class Success(val success: Boolean) : SignInState()
        data class Error(val error: String) : SignInState()
    }

    fun signIn(context: Context) {
        viewModelScope.launch(Dispatchers.IO) {
            _signInState.value = SignInState.Loading

            val userId = userRepository.checkCredentials(
                username = uiState.value.username,
                password = uiState.value.password
            )

            if (userId != null) {
                _signInState.value = SignInState.Success(true)
                UserManager.saveUserId(context, userId)
            } else {
                _signInState.value = SignInState.Error("Invalid username or password.")
            }
        }
    }

    companion object {
        val factory: ViewModelProvider.Factory = object : ViewModelProvider.Factory {
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                val application = UserApplication.instance
                val userDao = application.database.userDao()
                val repository = OfflineUserRepository(userDao)
                @Suppress("UNCHECKED_CAST")
                return SignInViewModel(repository) as T
            }
        }
    }
}

data class SignInUIState(
    val username: String = "",
    val password: String = "",
)