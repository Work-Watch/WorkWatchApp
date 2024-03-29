package com.grupo5.workwatchapp.ui.bossinterfaces

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.OnBackPressedCallback
import androidx.activity.compose.LocalOnBackPressedDispatcherOwner
import androidx.activity.compose.setContent
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
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.grupo5.workwatchapp.R
import com.grupo5.workwatchapp.ui.theme.WorkWatchAppTheme

class BossUI : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            WorkWatchAppTheme {
                BossHomeView()
            }
        }
    }

    private var pressedTime: Long = 0
    override fun onBackPressed() {
                if (pressedTime + 2000 > System.currentTimeMillis()) {
            super.onBackPressed()
            finish()
        } else {
            Toast.makeText(getBaseContext(), "Press back again to exit", Toast.LENGTH_SHORT).show();
        }
        pressedTime = System.currentTimeMillis();
    }
}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun BossHomeView() {
    val navController = rememberNavController()
    Scaffold(
        bottomBar = {
            BottomBar(navController = navController)
        }, contentColor = Color.White

    ) {contentPadding ->
        Box(modifier = Modifier.padding(contentPadding)){
        BottomNavGraph(navController = navController)
        }
    }
}

@Composable
fun DisableBackButtonHandler() {
    val lifecycleOwner = LocalLifecycleOwner.current
    val backDispatcher = LocalOnBackPressedDispatcherOwner.current?.onBackPressedDispatcher

    DisposableEffect(key1 = backDispatcher, key2 = lifecycleOwner) {
        val callback = object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                // Dejar vacío o agregar lógica personalizada
            }
        }

        backDispatcher?.addCallback(lifecycleOwner, callback)

        // Se debe eliminar el callback cuando la vista se destruye
        val lifecycleObserver = LifecycleEventObserver { _, event ->
            if (event == Lifecycle.Event.ON_DESTROY) {
                callback.remove()
            }
        }

        lifecycleOwner.lifecycle.addObserver(lifecycleObserver)

        onDispose {
            callback.remove()
            lifecycleOwner.lifecycle.removeObserver(lifecycleObserver)
        }
    }
}


@Composable
fun BottomBar(navController: NavHostController){
    val screens = listOf(
        BottomBarScreen.Map,
        BottomBarScreen.Task,
        BottomBarScreen.Teams,
    )
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination

    BottomNavigation(modifier = Modifier, backgroundColor = colorResource(id = R.color.teal_700)) {
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