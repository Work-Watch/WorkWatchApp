package com.grupo5.workwatchapp.ui.bossinterfaces

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Place
import androidx.compose.material.icons.outlined.CheckCircle
import androidx.compose.ui.graphics.vector.ImageVector

sealed class BottomBarScreen(
    val route: String,
    val title: String,
    val icon: ImageVector
){
    object Map : BottomBarScreen(
        route = "map",
        title = "Map",
        icon = Icons.Default.Place
    )
    object Task : BottomBarScreen(
        route = "task",
        title = "Tasks",
        icon = Icons.Outlined.CheckCircle
    )
    object TimeLine : BottomBarScreen(
        route = "timeline",
        title = "Time line",
        icon = Icons.Default.DateRange
    )
}
