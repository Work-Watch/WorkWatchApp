package com.grupo5.workwatchapp.ui.employee

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.padding
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.ContentAlpha
import androidx.compose.material.LocalContentColor
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.grupo5.workwatchapp.ui.theme.WorkWatchAppTheme

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun BossHomeView() {
    val navController = rememberNavController()
    Scaffold(
        bottomBar = {BottomBar(navController = navController)}
    ) {contentPadding ->
        Box(modifier = Modifier.padding(contentPadding)){
        BottomNavGraph(navController = navController)
    }
    }
}

@Composable
fun BottomBar(navController: NavHostController){
    val screens = listOf(
        BottomBarScreen.Map,
        BottomBarScreen.Task,
        BottomBarScreen.TimeLine,
    )
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination

    BottomNavigation {
        screens.forEach{screen ->
            AddItem(
                screen = screen,
                currentDestination = currentDestination,
                navController =navController
            )
        }
    }
}

@Composable
fun RowScope.AddItem(
    screen: BottomBarScreen,
    currentDestination: NavDestination?,
    navController: NavHostController
){
    BottomNavigationItem(
        label = {
            Text(text = screen.title)
        },
        icon = {
            Icon(
                imageVector = screen.icon,
                contentDescription = "Navigation icon"
            )
        },
        selected = currentDestination?.hierarchy?.any{
            it.route == screen.route
        } == true,
        unselectedContentColor = LocalContentColor.current.copy(alpha = ContentAlpha.disabled),
        onClick = {
            navController.navigate(screen.route){
                popUpTo(navController.graph.findStartDestination().id)
                launchSingleTop = true
            }
        }
    )
}
@Preview(showSystemUi = true)
@Composable
fun NavigationBarPreview() {
    WorkWatchAppTheme {
        BossHomeView()

    }
}

/*Scaffold(
        bottomBar = {
            BottomAppBar {
                Box(modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.BottomCenter){
                    var selectedItem by remember { mutableStateOf(0) }
                    val items = listOf(null)
                    NavigationBar {
                        items.forEachIndexed { index, item ->
                            NavigationBarItem(
                                icon = { Icon(Icons.Filled.LocationOn, contentDescription = item) },
                                label = { Text("Map") },
                                selected = selectedItem == index,
                                onClick = { selectedItem = index}
                            )
                        }
                        items.forEachIndexed { index, item ->
                            NavigationBarItem(
                                icon = { Icon(Icons.Outlined.CheckCircle, contentDescription = item) },
                                label = { Text("Task") },
                                selected = selectedItem == index,
                                onClick = { selectedItem = index}
                            )
                        }
                        items.forEachIndexed { index, item ->
                            NavigationBarItem(
                                icon = { Icon(Icons.Filled.DateRange, contentDescription = item) },
                                label = { Text("Timeline") },
                                selected = selectedItem == index,
                                onClick = { selectedItem = index}
                            )
                        }
                    }
                }
            }
        }
    ) { contentPadding ->
        Box(modifier = Modifier.padding(contentPadding)){
            Box() {
                /*TODO("Aqui tiene que ir el google maps")*/
            }
            Box(modifier = Modifier.fillMaxSize() ,
                contentAlignment = Alignment.BottomEnd) {
                FloatingActionButton(
                    onClick = { /* do something */ },
                    Modifier.padding(16.dp)
                ) {
                    Icon(Icons.Filled.Menu, "Localized description")
                }
            }
        }
    }*/