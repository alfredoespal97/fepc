package com.alma.fepc.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.alma.fepc.data.local.entity.LessonEntity

@Composable
fun LessonCard(lesson: LessonEntity, onClick: () -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clickable(enabled = lesson.isUnlocked, onClick = onClick),
        colors = CardDefaults.cardColors(if (lesson.isUnlocked) Color.White else Color.Gray)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(lesson.title, style = MaterialTheme.typography.titleMedium)
            Text(lesson.description, style = MaterialTheme.typography.bodySmall)
        }
    }
}
