package com.alma.fepc.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.alma.fepc.ui.components.LessonCard
import com.alma.fepc.viewmodel.LessonViewModel

@Composable
fun LessonListScreen2(
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
@Composable
fun LessonListScreen(
    viewModel: LessonViewModel = hiltViewModel(),
    navigateToDetail: (Int) -> Unit
) {
    val lessons by viewModel.lessons.collectAsState()

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Brush.verticalGradient(listOf(Color(0xFFFFF3E0), Color(0xFFFFE0B2))))
    ) {
        Column {
            Spacer(modifier = Modifier.height(24.dp))
            Text(
                text = "Mes Leçons 🍎📚",
                style = MaterialTheme.typography.headlineLarge.copy(
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFF5D4037)
                ),
                modifier = Modifier.padding(24.dp)
            )

            LazyColumn(
                contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                items(lessons) { lesson ->
                    LessonCard(lesson) {
                        if (lesson.isUnlocked) {
                            navigateToDetail(lesson.id)
                        }
                    }
                }
            }
        }
    }
}
