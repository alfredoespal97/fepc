package com.alma.fepc.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.alma.fepc.data.local.entity.LessonEntity
import com.alma.fepc.data.repository.LessonRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LessonViewModel @Inject constructor(
    private val repository: LessonRepository
) : ViewModel() {

    val lessons: StateFlow<List<LessonEntity>> = repository.lessons
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    init {
        viewModelScope.launch {
            repository.seedInitialLessons()
        }
    }

    fun completeLesson(lesson: LessonEntity) {
        viewModelScope.launch {
            repository.completeLesson(lesson)
        }
    }
}


