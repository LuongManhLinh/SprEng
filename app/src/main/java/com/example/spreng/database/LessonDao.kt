package com.example.spreng.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface LessonDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertLesson(lesson: Lesson)
//
//    @Update
//    suspend fun updateLesson(lesson: Lesson)
//
//    @Delete
//    suspend fun deleteLesson(lesson: Lesson)
//
//    @Query("SELECT * FROM lesson_table WHERE userId = :userId")
//    fun getLessonsByUserId(userId: Long): Flow<List<Lesson>>
//
    @Query("SELECT * FROM lesson_table WHERE userId = :id")
    fun getLessonByUserId(id: Long): Flow<Lesson?>
}
