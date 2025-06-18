package com.alma.fepc

import android.annotation.SuppressLint
import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.alma.fepc.ui.screens.LessonDetailScreen
import com.alma.fepc.ui.screens.LessonListScreen
import com.alma.fepc.viewmodel.LessonViewModel

@SuppressLint("StateFlowValueCalledInComposition")
@Composable
fun NavigationWrapper(navHostController: NavHostController) {
    val viewModel: LessonViewModel = viewModel()

    NavHost(navController = navHostController, startDestination = "list") {
        composable("list") {
            LessonListScreen(
                viewModel = viewModel,
                navigateToDetail = { lessonId ->
                    navHostController.navigate("lessonDetail/$lessonId")
                }
            )
        }
        composable(
            route = "lessonDetail/{lessonId}",
            arguments = listOf(navArgument("lessonId") { type = NavType.IntType })
        ) { backStackEntry ->
            val lessonId = backStackEntry.arguments?.getInt("lessonId")
            val lesson = viewModel.lessons.value.find { it.id == lessonId }
            if (lesson != null) {
                LessonDetailScreen(
                    lesson = lesson,
                    onComplete = {
                        viewModel.completeLesson(lesson)
                        navHostController.popBackStack()
                    }
                )
            }
        }
    }
}
