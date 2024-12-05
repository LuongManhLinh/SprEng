package com.example.spreng.repository

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface UserDao {
    @Query("SELECT * FROM user_table ORDER BY id ASC")
    fun getAllUsers(): Flow<List<User>>

    @Query("SELECT * FROM user_table WHERE id = :id")
    fun getUserById(id: Long): Flow<User?>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUser(user: User): Long

    @Delete
    suspend fun deleteUser(user: User)

    @Update
    suspend fun updateUser(user: User)
    @Query("SELECT * FROM user_table WHERE username = :username LIMIT 1")
    fun getUserByUsername(username: String): Flow<User?>
}
