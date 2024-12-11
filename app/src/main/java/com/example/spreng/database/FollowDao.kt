package com.example.spreng.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface FollowDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addFollow(follow: Follow)

    @Delete
    suspend fun removeFollow(follow: Follow)

    @Query("SELECT COUNT(*) > 0 FROM follow_table WHERE followerId = :followerId AND followedId = :followedId")
    suspend fun isFollowing(followerId: Long, followedId: Long): Boolean

    @Query("SELECT * FROM user_table WHERE id IN (SELECT followedId FROM follow_table WHERE followerId = :userId)")
    suspend fun getFollowedUsers(userId: Long): List<User>

    @Query("SELECT * FROM user_table WHERE id IN (SELECT followerId FROM follow_table WHERE followedId = :userId)")
    suspend fun getFollowers(userId: Long): List<User>

}