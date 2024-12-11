package com.example.spreng.repository

import com.example.spreng.database.Follow
import com.example.spreng.database.FollowDao
import com.example.spreng.database.User

class FollowRepository(private val followDao: FollowDao) {
    suspend fun followUser(followerId: Long, followedId: Long) {
        val follow = Follow(followerId = followerId, followedId = followedId)
        followDao.addFollow(follow)
    }

    suspend fun unfollowUser(followerId: Long, followedId: Long) {
        val follow = Follow(followerId = followerId, followedId = followedId)
        followDao.removeFollow(follow)
    }

    suspend fun isUserFollowing(followerId: Long, followedId: Long): Boolean {
        return followDao.isFollowing(followerId, followedId)
    }

    suspend fun getFollowedUsers(userId: Long): List<User> {
        // Lấy danh sách người dùng mà `userId` đang theo dõi
        return followDao.getFollowedUsers(userId)
    }

    suspend fun getFollowers(userId: Long): List<User> {
        // Lấy danh sách người dùng đang theo dõi `userId`
        return followDao.getFollowers(userId)
    }
}
