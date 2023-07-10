package com.grupo5.workwatchapp.ui.bossinterfaces

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.grupo5.workwatchapp.ui.bossinterfaces.map.MapScreen
import com.grupo5.workwatchapp.ui.bossinterfaces.task.BossTasksView
import com.grupo5.workwatchapp.ui.bossinterfaces.timeline.TimelineScreen

@Composable
fun BottomNavGraph(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = BottomBarScreen.Map.route
    ) {
        composable(route = BottomBarScreen.Map.route) {
            // Lógica o contenido relacionado con la pantalla "Map"
            MapScreen()
        }
        composable(route = BottomBarScreen.Task.route){
            BossTasksView()
        }
        composable(route = BottomBarScreen.TimeLine.route){
            TimelineScreen()
        }
        // Agrega más composables para otras pantallas del bottom navigation
    }
}

