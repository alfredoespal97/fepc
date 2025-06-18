package com.alma.fepc.ui.components

data class ImageQuestion(
    val questionText: String,
    val options: List<ImageOption>,
    val correctAnswerIndex: Int
)

data class ImageOption(
    val label: String,
    val imageResId: Int // Usamos recursos drawable
)
