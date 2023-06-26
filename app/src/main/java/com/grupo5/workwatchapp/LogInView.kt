package com.grupo5.workwatchapp

import android.os.Bundle
import android.os.PersistableBundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.AnimatedContentScope.SlideDirection.Companion.End
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.grupo5.workwatchapp.ui.theme.WorkWatchAppTheme
import org.intellij.lang.annotations.JdkConstants.HorizontalAlignment

class LogIn : ComponentActivity(){
    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)
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

//Declaration of the LogIn composable//
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LogInView(/*user: String, password: String*/) {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(painter = painterResource(id = R.drawable.logo_de_vivo_mesa_de_trabajo_1),
            contentDescription = null,)
        Box(Modifier.padding(8.dp)){
            OutlinedTextField(value = "User",
                onValueChange = {},
                enabled = true
                )
        }
        Box(Modifier.padding(8.dp)){
            OutlinedTextField(value = "Password",
                onValueChange = {},
                enabled = true

            )
        }
        Column(Modifier.padding(8.dp)){
            Text(
                text = "Forgot password?",
                color = colorResource(R.color.aqua_clear_custom)
            )
        }
        Button(onClick = { /*TODO*/ },
            colors = ButtonDefaults.buttonColors(colorResource(id = R.color.aqua_dark_custom))
        ){
            Text(text = "Login")
        }
        Row() {
            Text(text = "Don´t have an account?")
            Text(text = " Sing Up",
                color = colorResource(id = R.color.red_cherry_custom)
            )
        }

    }
}

@Preview(showSystemUi = true)
@Composable
fun LogInPreview(){
    WorkWatchAppTheme{
        LogInView()
    }
}

