package com.alma.fepc.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "lessons")
data class LessonEntity(
    @PrimaryKey val id: Int,
    val title: String,
    val description: String,
    val isUnlocked: Boolean = false,
    val isCompleted: Boolean = false
)