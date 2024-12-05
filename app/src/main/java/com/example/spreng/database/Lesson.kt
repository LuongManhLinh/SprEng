package com.example.spreng.database

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(
    tableName = "lesson_table",
    foreignKeys = [
        ForeignKey(
            entity = User::class,
            parentColumns = ["id"],
            childColumns = ["userId"],
            onDelete = ForeignKey.CASCADE
        )
    ]
)
data class Lesson(
//    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    @PrimaryKey val userId: Long,               // Khóa ngoại liên kết với bảng User
    val lessonIsCompleteNumber: Int,          // Số bài học
    val exp: Int,                   // Kinh nghiệm đạt được
    val streak: Int,                // Chuỗi chiến thắng liên tiếp
    val rank: String,               // Hạng của người dùng
    val top3Count: Int              // Số lần vào top 3
)