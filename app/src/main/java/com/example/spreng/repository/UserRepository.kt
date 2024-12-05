package com.example.spreng.repository

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.map

interface UserRepository {
    fun getAllUsers(): Flow<List<User>>
    fun getUserById(id: Long): Flow<User?>
    suspend fun insertUser(user: User): Long
    suspend fun deleteUser(user: User)
    suspend fun updateUser(user: User)
    fun checkUserExistByUsername(username: String): Flow<Boolean>
    suspend fun checkCredentials(username: String, password: String): Long?
}

class OfflineUserRepository(private val userDao: UserDao) : UserRepository {
    override fun getAllUsers(): Flow<List<User>> = userDao.getAllUsers()
    override fun getUserById(id: Long): Flow<User?> = userDao.getUserById(id)
    override suspend fun insertUser(user: User): Long {
        return userDao.insertUser(user)
    }
    override suspend fun deleteUser(user: User) = userDao.deleteUser(user)
    override suspend fun updateUser(user: User) = userDao.updateUser(user)
    override fun checkUserExistByUsername(username: String): Flow<Boolean> {
        return userDao.getUserByUsername(username).map { it != null }
    }
    override suspend fun checkCredentials(username: String, password: String): Long? {
        val user = userDao.getUserByUsername(username).firstOrNull()
        return if (user?.password == password) {
            user.id // Trả về id nếu tên người dùng và mật khẩu khớp
        } else {
            null // Trả về null nếu không khớp
        }
    }
}




