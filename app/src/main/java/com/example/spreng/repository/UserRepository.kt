package com.example.spreng.repository

import com.example.spreng.database.User
import com.example.spreng.database.UserDao
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.map


class UserRepository(private val userDao: UserDao){
    fun getAllUsers(): Flow<List<User>> = userDao.getAllUsers()
      fun getUserById(id: Long): Flow<User?> = userDao.getUserById(id)
      suspend fun insertUser(user: User): Long {
        return userDao.insertUser(user)
    }
      suspend fun deleteUser(user: User) = userDao.deleteUser(user)
      suspend fun updateUser(user: User) = userDao.updateUser(user)
      fun checkUserExistByUsername(username: String): Flow<Boolean> {
        return userDao.getUserByUsername(username).map { it != null }
    }
      suspend fun checkCredentials(username: String, password: String): Long? {
        val user = userDao.getUserByUsername(username).firstOrNull()
        return if (user?.password == password) {
            user.id // Trả về id nếu tên người dùng và mật khẩu khớp
        } else {
            null // Trả về null nếu không khớp
        }
    }
      fun getOtherUsersByRank(rank: String, userId: Long): Flow<List<UserWithExp>> {
        return userDao.getOtherUsersByRank(rank, userId)
    }

      fun getCurrentUserWithExp(rank: String, userId: Long): Flow<UserWithExp?> {
        return userDao.getCurrentUserWithExp(userId, rank)
    }
}

data class UserWithExp(
    val username: String,
    val exp: Int
)




