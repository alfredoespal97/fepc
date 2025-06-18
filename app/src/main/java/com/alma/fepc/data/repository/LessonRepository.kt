package com.alma.fepc.data.repository

import com.alma.fepc.data.local.dao.LessonDao
import com.alma.fepc.data.local.entity.LessonEntity
import kotlinx.coroutines.flow.first
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class LessonRepository @Inject constructor(
    private val dao: LessonDao
) {
    val lessons = dao.getAllLessons()

    suspend fun completeLesson(lesson: LessonEntity) {
        val updated = lesson.copy(isCompleted = true)
        dao.updateLesson(updated)

        // desbloquear siguiente lección
        val nextId = lesson.id + 1
        val currentLessons = dao.getAllLessons().first()
        currentLessons.find { it.id == nextId }?.let {
            dao.updateLesson(it.copy(isUnlocked = true))
        }
    }

    suspend fun seedInitialLessons() {
        if (dao.getAllLessons().first().isEmpty()) {
            val initial = listOf(
                LessonEntity(1, "Leçon 1", "Épeler les noms des fruits", true),
                LessonEntity(2, "Leçon 2", "L’alphabet des fruits "),
                LessonEntity(3, "Leçon 3", "Écoute et épèle"),
                LessonEntity(4, "Leçon 4", "Le jeu du pendu"),
                LessonEntity(5, "Leçon 5", "Active tes neurones")
            )
            dao.insertAll(initial)
        }
    }
}

