package com.alma.fepc.ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition
import kotlinx.coroutines.delay
import kotlinx.coroutines.time.delay


@Composable
fun SuccessAnimationScreen(onFinished: () -> Unit) {
    val composition by rememberLottieComposition(LottieCompositionSpec.Asset("success.json"))
    val progress by animateLottieCompositionAsState(
        composition = composition,
        iterations = 1,
        speed = 1.5f
    )

    // Lanzar navegación automática cuando termine la animación
    LaunchedEffect(progress) {
        if (progress == 1f) {
            delay(1000) // Espera breve tras animación
            onFinished()
        }
    }

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        LottieAnimation(
            composition = composition,
            progress = { progress },
            modifier = Modifier.size(250.dp)
        )
    }
}
