package com.alma.fepc.ui.screens

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import com.alma.fepc.ui.components.LessonCard
import com.alma.fepc.viewmodel.LessonViewModel

@Composable
fun LessonListScreen(
    viewModel: LessonViewModel = hiltViewModel(),
    navigateToDetail: (Int) -> Unit
) {
    val lessons by viewModel.lessons.collectAsState()

    LazyColumn {
        items(lessons) { lesson ->
            LessonCard(lesson) {
                if (lesson.isUnlocked) {
                    navigateToDetail(lesson.id)
                }
            }
        }
    }
}