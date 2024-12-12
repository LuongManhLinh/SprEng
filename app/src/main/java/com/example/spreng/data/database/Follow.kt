package com.example.spreng.data.database

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = "follow_table",
    primaryKeys = ["followerId", "followedId"], // Đảm bảo rằng mỗi kết hợp follower và followed là duy nhất
    foreignKeys = [
        ForeignKey(
            entity = User::class,
            parentColumns = ["id"],
            childColumns = ["followerId"],
            onDelete = ForeignKey.CASCADE
        ),
        ForeignKey(
            entity = User::class,
            parentColumns = ["id"],
            childColumns = ["followedId"],
            onDelete = ForeignKey.CASCADE
        )
    ],
    indices = [
        Index(value = ["followerId"]),
        Index(value = ["followedId"])
    ]
)
data class Follow(
    val followerId: Long, // ID của người theo dõi
    val followedId: Long  // ID của người được theo dõi
)

