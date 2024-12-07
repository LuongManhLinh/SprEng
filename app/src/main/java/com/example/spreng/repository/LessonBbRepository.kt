package com.example.spreng.repository

import com.example.spreng.database.Lesson
import com.example.spreng.database.LessonDao
import kotlinx.coroutines.flow.Flow

class LessonBbRepository(private val lessonDao: LessonDao) {
    suspend fun insertUser(lesson: Lesson) = lessonDao.insertLesson(lesson)
    fun getLessonsByUserId(userId: Long): Flow<Lesson?> {
        return lessonDao.getLessonByUserId(userId)
    }
    fun getCompletedLessonCount(userId: Long): Flow<Int> {
        return lessonDao.getCompletedLessonCount(userId)
    }
    suspend fun updateCompletedLessonCount(userId: Long, completedLessons: Int) {
        lessonDao.updateCompletedLessonCount(userId, completedLessons)
    }

}
