package com.grupo5.workwatchapp

import android.os.Bundle
import android.os.PersistableBundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.grupo5.workwatchapp.ui.theme.WorkWatchAppTheme

class BossUI : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)
        setContent {
            WorkWatchAppTheme {
                // A surface container using the 'background' color from the theme
                Surface(

                ) {

                }
            }

        }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BossHomeView() {
    Scaffold(
        bottomBar = {
            BottomAppBar {
                Box(modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.BottomCenter){
                    var selectedItem by remember { mutableStateOf(0) }
                    val items = listOf(null)
                    NavigationBar {
                        items.forEachIndexed { index, item ->
                            NavigationBarItem(
                                icon = { Icon(Icons.Filled.Home, contentDescription = item) },
                                label = { Text("Map") },
                                selected = selectedItem == index,
                                onClick = { selectedItem = index}
                            )
                        }
                        items.forEachIndexed { index, item ->
                            NavigationBarItem(
                                icon = { Icon(Icons.Filled.Add, contentDescription = item) },
                                label = { Text("Task") },
                                selected = selectedItem == index,
                                onClick = { selectedItem = index}
                            )
                        }
                        items.forEachIndexed { index, item ->
                            NavigationBarItem(
                                icon = { Icon(Icons.Filled.Menu, contentDescription = item) },
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
            /*TODO("Aqui tiene que ir el google maps")*/
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
    }

    /*  Box(modifier = Modifier)
      Box(modifier = Modifier ,
      contentAlignment = Alignment.BottomEnd) {
          FloatingActionButton(
              onClick = { /* do something */ }
          ) {
              Icon(Icons.Filled.Menu, "Localized description")
          }
      }
      Box(modifier = Modifier.fillMaxSize(),
          contentAlignment = Alignment.BottomCenter){
          var selectedItem by remember { mutableStateOf(0) }
          val items = listOf("Home")
          NavigationBar {
              items.forEachIndexed { index, item ->
                  NavigationBarItem(
                      icon = { Icon(Icons.Filled.Home, contentDescription = item) },
                      label = { Text("Home") },
                      selected = selectedItem == index,
                      onClick = { selectedItem = index}
                  )
              }
              items.forEachIndexed { index, item ->
                  NavigationBarItem(
                      icon = { Icon(Icons.Filled.Add, contentDescription = item) },
                      label = { Text("Home") },
                      selected = selectedItem == index,
                      onClick = { selectedItem = index}
                  )
              }
              items.forEachIndexed { index, item ->
                  NavigationBarItem(
                      icon = { Icon(Icons.Filled.Menu, contentDescription = item) },
                      label = { Text("Home") },
                      selected = selectedItem == index,
                      onClick = { selectedItem = index}
                  )
              }
          }
      }
  */
}

@Preview(showSystemUi = true)
@Composable
fun NavigationBarPreview() {
    WorkWatchAppTheme {
        BossHomeView()

    }
}