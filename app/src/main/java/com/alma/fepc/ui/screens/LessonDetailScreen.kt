package com.alma.fepc.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.material3.Button
import com.alma.fepc.data.local.entity.LessonEntity

@Composable
fun LessonDetailScreen(lesson: LessonEntity?, onComplete: () -> Unit) {
    if (lesson == null) return

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(text = lesson.title, style = MaterialTheme.typography.headlineSmall)
        Spacer(modifier = Modifier.height(8.dp))
        Text(text = lesson.description)
        Spacer(modifier = Modifier.height(16.dp))

        if (lesson.id == 1) {
            // Contenido específico de la Lección 1
            LessonOneScreen(onCorrectAnswer = onComplete)
        } else {
            // Contenido general para otras lecciones
            Button(
                onClick = onComplete,
                enabled = !lesson.isCompleted
            ) {
                Text(text = if (lesson.isCompleted) "Completada" else "Marcar como completada")
            }
        }
    }
}

