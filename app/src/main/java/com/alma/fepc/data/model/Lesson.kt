package com.alma.fepc.data.model

data class Lesson(
    val id: Int,
    val title: String,
    val description: String,
    var isUnlocked: Boolean = false,
    var isCompleted: Boolean = false
)
