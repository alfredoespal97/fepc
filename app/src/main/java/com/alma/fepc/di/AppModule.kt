package com.alma.fepc.di

import android.app.Application
import android.content.Context
import androidx.room.Room
import com.alma.fepc.data.local.dao.LessonDao
import com.alma.fepc.data.local.db.AppDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

//    @Provides
//    @Singleton
//    fun provideDatabase(@ApplicationContext context: Context): AppDatabase =
//        Room.databaseBuilder(context, AppDatabase::class.java, "app_db")
//            .fallbackToDestructiveMigration()
//            .build()
//
//    @Provides
//    fun provideLessonDao(db: AppDatabase): LessonDao = db.lessonDao()

    @Provides
    @Singleton
    fun provideDatabase(app: Application): AppDatabase {
        return Room.databaseBuilder(
            app,
            AppDatabase::class.java,
            "lesson_database"
        ).build()
    }

    @Provides
    fun provideLessonDao(db: AppDatabase): LessonDao {
        return db.lessonDao()
    }
}
