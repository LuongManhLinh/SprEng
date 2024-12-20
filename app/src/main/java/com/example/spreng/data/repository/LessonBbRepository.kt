package com.example.spreng.data.repository

import com.example.spreng.data.database.Lesson
import com.example.spreng.data.database.LessonDao
import kotlinx.coroutines.flow.Flow

class LessonBbRepository(private val lessonDao: LessonDao) {
    suspend fun insertUser(lesson: Lesson) = lessonDao.insertLesson(lesson)
    fun getLessonsByUserId(userId: Long): Flow<Lesson?> {
        return lessonDao.getLessonByUserId(userId)
    }

    suspend fun updateCompletedLessonCount(userId: Long, completedLessons: Int) {
        lessonDao.updateCompletedLessonCount(userId, completedLessons)
    }

    suspend fun updateUserXp(userId: Long, newXp: Int) {
        lessonDao.updateUserXp(userId, newXp)
    }


}
