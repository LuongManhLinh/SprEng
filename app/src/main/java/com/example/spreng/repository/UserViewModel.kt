package com.example.spreng.repository

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch

class UserViewModel(private val repository: UserRepository) : ViewModel() {
    val users: Flow<List<User>> = repository.getAllUsers()

    fun getUserById(id: Long): Flow<User?> = repository.getUserById(id)

    fun insertUser(user: User) {
        viewModelScope.launch {
            repository.insertUser(user)
        }
    }

    fun deleteUser(user: User) {
        viewModelScope.launch {
            repository.deleteUser(user)
        }
    }

    fun updateUser(user: User) {
        viewModelScope.launch {
            repository.updateUser(user)
        }
    }

    companion object {
        val factory: ViewModelProvider.Factory = object : ViewModelProvider.Factory {
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                val application = UserApplication.instance
                val userDao = application.database.userDao()
                val repository = OfflineUserRepository(userDao)
                @Suppress("UNCHECKED_CAST")
                return UserViewModel(repository) as T
            }
        }
    }
}

