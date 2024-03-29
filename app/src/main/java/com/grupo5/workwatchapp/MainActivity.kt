package com.grupo5.workwatchapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.grupo5.workwatchapp.ui.bossinterfaces.BossHomeView
import com.grupo5.workwatchapp.ui.login.LogInPreview
import com.grupo5.workwatchapp.ui.login.LogInView
import com.grupo5.workwatchapp.ui.theme.WorkWatchAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            WorkWatchAppTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    LogInView()
                }
            }
        }
    }
}

@Composable
fun MainView(/*name: String, modifier: Modifier = Modifier*/) {
    /*Column() {
        Text(
            text = "Hello $name!",
            modifier = modifier
        )
    }*/
    BossHomeView()

}

@Preview(showSystemUi = true)
@Composable
fun GreetingPreview() {
    WorkWatchAppTheme {
        MainView()
    }
}