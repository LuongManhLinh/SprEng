package com.example.spreng.repository

import kotlinx.coroutines.flow.Flow

class LessonBbRepository(private val lessonDao: LessonDao) {
    suspend fun insertUser(lesson: Lesson) = lessonDao.insertLesson(lesson)
    fun getLessonsByUserId(userId: Long): Flow<Lesson?> {
        return lessonDao.getLessonByUserId(userId)
    }
}
