package com.alma.fepc.data.local.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.alma.fepc.data.local.dao.LessonDao
import com.alma.fepc.data.local.entity.LessonEntity

@Database(entities = [LessonEntity::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun lessonDao(): LessonDao
}