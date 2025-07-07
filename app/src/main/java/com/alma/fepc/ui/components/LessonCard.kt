package com.alma.fepc.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.alma.fepc.data.local.entity.LessonEntity


@Composable
fun LessonCard(lesson: LessonEntity, onClick: () -> Unit) {
    val backgroundColor = when {
        lesson.isCompleted -> Color(0xFFA5D6A7)
        lesson.isUnlocked -> Color(0xFFFFF59D)
        else -> Color(0xFFEEEEEE)
    }

    val textColor = if (!lesson.isUnlocked) Color.Gray else Color.Black

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(140.dp)
            .clickable(enabled = lesson.isUnlocked, onClick = onClick),
        colors = CardDefaults.cardColors(containerColor = backgroundColor),
        shape = RoundedCornerShape(24.dp),
        elevation = CardDefaults.cardElevation(8.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(20.dp),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = lesson.title,
                style = MaterialTheme.typography.titleMedium.copy(
                    color = textColor,
                    fontWeight = FontWeight.Bold
                )
            )
            Text(
                text = lesson.description,
                style = MaterialTheme.typography.bodyMedium.copy(color = textColor)
            )
            Row(
                horizontalArrangement = Arrangement.End,
                modifier = Modifier.fillMaxWidth()
            ) {
                if (lesson.isCompleted) {
                    Text("✔ Completada", color = Color(0xFF2E7D32), fontWeight = FontWeight.SemiBold)
                } else if (!lesson.isUnlocked) {
                    Text("🔒 Bloquée", color = Color.Gray)
                } else {
                    Text("📖 Disponible", color = Color(0xFF6A1B9A))
                }
            }
        }
    }
}
