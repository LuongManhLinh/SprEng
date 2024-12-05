package com.example.spreng.database

import androidx.room.*
import com.example.spreng.repository.UserWithExp
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

    @Query("""
    SELECT user_table.username, lesson_table.exp 
    FROM user_table 
    INNER JOIN lesson_table ON user_table.id = lesson_table.userId 
    WHERE lesson_table.rank = :rank AND user_table.id != :userId 
    ORDER BY lesson_table.exp DESC LIMIT 49
""")
    fun getOtherUsersByRank(rank: String, userId: Long): Flow<List<UserWithExp>>




    @Query("""
    SELECT user_table.username, lesson_table.exp 
    FROM user_table 
    INNER JOIN lesson_table ON user_table.id = lesson_table.userId 
    WHERE user_table.id = :userId AND lesson_table.rank = :rank
""")
    fun getCurrentUserWithExp(userId: Long, rank: String): Flow<UserWithExp?>




}
