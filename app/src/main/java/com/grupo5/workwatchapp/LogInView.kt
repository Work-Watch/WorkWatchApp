package com.grupo5.workwatchapp

import android.os.Bundle
import android.os.PersistableBundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import com.grupo5.workwatchapp.ui.theme.WorkWatchAppTheme

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
                        //LogInView()
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
        OutlinedTextField(value = "Usuario",
            onValueChange = {},
            enabled = true
            )
        OutlinedTextField(value = "Contraseña",
            onValueChange = {},
            enabled = true,
            //Modifier.padding(8.dp)
            //Icon(imageVector = Icons.Default, contentDescription = null)
        )
        Text(text = "Forgot your password?",
        color = Color(R.color.tertiaryColor))
        Button(onClick = { /*TODO*/ }) {
            Text(text = "Iniciar sesión")
        }
        Row() {
            Text(text = "Don´t have an account?")
            Text(text = "Sing Up",
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

