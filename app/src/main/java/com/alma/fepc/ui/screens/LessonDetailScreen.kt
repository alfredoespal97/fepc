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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import com.alma.fepc.data.local.entity.LessonEntity
import com.alma.fepc.ui.components.SuccessAnimationScreen

@Composable
fun LessonDetailScreen(
    lesson: LessonEntity?,
    onComplete: () -> Unit,
    onNavigateAfterCompletion: () -> Unit
) {
    var showSuccess by remember { mutableStateOf(false) }

    if (lesson == null) return

    if (showSuccess) {
        SuccessAnimationScreen(onFinished = onNavigateAfterCompletion)
        return
    }

    Spacer(modifier = Modifier.height(24.dp))
    if (lesson.id == 1) {
        // Contenido específico de la Lección 1
        LessonOneScreen(onCorrectAnswer = onComplete)
    } else if (lesson.id == 2) {
        // Contenido específico de la Lección 2
        LessonTwoScreen(onCorrectAnswer = {
            onComplete()
            showSuccess = true
        })
    } else if (lesson.id == 3) {
        // Contenido específico de la Lección 3
        LessonThreeScreen(onCorrectAnswer = {
            onComplete()
            showSuccess = true
        })
    } else if (lesson.id == 4) {
        // Contenido específico de la Lección 4
        LessonFourScreen(onCorrectAnswer = {
            onComplete()
            showSuccess = true
        })
    } else if (lesson.id == 5) {
        // Contenido específico de la Lección 5
        LessonFiveScreen(onCorrectAnswer = {
            onComplete()
            showSuccess = true
        })
    }

    else {
        // Contenido general para otras lecciones
        Button(
            onClick = onComplete,
            enabled = !lesson.isCompleted
        ) {
            Text(text = if (lesson.isCompleted) "Completada" else "Marcar como completada")
        }
    }
}

@Composable
fun LessonDetailScreen2(
    lesson: LessonEntity?,
    onComplete: () -> Unit,
    onNavigateAfterCompletion: () -> Unit // <-- Nueva lambda para navegar
) {
    var showSuccess by remember { mutableStateOf(false) }

    if (lesson == null) return

    if (showSuccess) {
        SuccessAnimationScreen(onFinished = onNavigateAfterCompletion)
        return
    }

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
            LessonOneScreen(onCorrectAnswer = {
                onComplete()
                showSuccess = true
            })
        } else {
            Button(
                onClick = {
                    onComplete()
                    showSuccess = true
                },
                enabled = !lesson.isCompleted
            ) {
                Text(text = if (lesson.isCompleted) "Completada" else "Marcar como completada")
            }
        }
    }
}
