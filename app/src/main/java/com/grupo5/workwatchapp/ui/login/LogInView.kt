package com.grupo5.workwatchapp.ui.login

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.compose.rememberNavController
import com.grupo5.workwatchapp.R
import com.grupo5.workwatchapp.ui.employee.EnterData
import com.grupo5.workwatchapp.ui.theme.WorkWatchAppTheme

class LogIn : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            WorkWatchAppTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    NavigationScreen()
                }
            }
        }
    }
}

// Declaration of the LogIn composable
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LogInView(navController: NavController, viewModel: LoginViewModel) {
    val email: String by viewModel.email.observeAsState(initial = "")
    val password: String by viewModel.password.observeAsState(initial = "")

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = R.drawable.logo_de_vivo_mesa_de_trabajo_1),
            contentDescription = null
        )

        EmailField(email) { viewModel.onLoginChanged(it, password) }
        PasswordField(password) { viewModel.onLoginChanged(email, it) }

        Column(Modifier.padding(8.dp)) {
            Text(
                text = "Forgot password?",
                color = colorResource(R.color.aqua_clear_custom)
            )
        }

        LoginButton(navController = navController, viewModel = viewModel)

        Button(
            onClick = {
                navController.navigate(ScreenLogin.Boss.route)
            },
            colors = ButtonDefaults.buttonColors(colorResource(id = R.color.aqua_dark_custom))
        ) {
            Text(text = "Prueba")
        }

        Row() {
            Text(text = "Don't have an account?")
            Text(
                text = " Sign Up",
                color = colorResource(id = R.color.red_cherry_custom)
            )
        }
    }
}

@Composable
fun EmailField(email: String, onTextFieldChanged: (String) -> Unit) {
    Box(Modifier.padding(8.dp)) {
        OutlinedTextField(
            value = email,
            onValueChange = { onTextFieldChanged(it) },
            label = { Text("Email") }
        )
    }
}

@Composable
fun PasswordField(password: String, onTextFieldChanged: (String) -> Unit) {
    Box(Modifier.padding(8.dp)) {
        OutlinedTextField(
            value = password,
            onValueChange = { onTextFieldChanged(it) },
            label = { Text("Password") }
        )
    }
}

@Composable
fun LoginButton(
    navController: NavController,
    viewModel: LoginViewModel
) {
    val errorMessage by viewModel.loginResult.observeAsState()

    val context = LocalContext.current

    Button(
        onClick = {
            if (!viewModel.isValidate()) {
                Toast.makeText(context, "Empty fields", Toast.LENGTH_SHORT).show()
            } else {
                viewModel.onLogin()
            }
        },
        colors = ButtonDefaults.buttonColors(colorResource(id = R.color.aqua_dark_custom))
    ) {
        Text(text = "Login")
    }

    LaunchedEffect(errorMessage) {
        errorMessage?.let { result ->
            result.onSuccess {
                navController.navigate(ScreenLogin.Boss.route)
            }
            result.onFailure { exception ->
                Toast.makeText(context, exception.message ?: "", Toast.LENGTH_SHORT).show()
            }
        }
    }
}

@Composable
fun NavigationScreen() {
    val viewModel = remember { LoginViewModel() }
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = ScreenLogin.Home.route
    ) {
        navigation(
            route = ScreenLogin.Home.route,
            startDestination = ScreenLogin.Home.route
        ) {
            composable(ScreenLogin.Home.route) {
                LogInView(navController = navController, viewModel = viewModel)
            }
            composable(ScreenLogin.Boss.route) {
                EnterData()
            }
        }
    }
}


@Preview(showSystemUi = true)
@Composable
fun LogInPreview() {
    val navController = rememberNavController()

    WorkWatchAppTheme {
        LogInView(navController = navController, viewModel = LoginViewModel())
    }
}


