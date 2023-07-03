package com.grupo5.workwatchapp.ui.login

import android.content.Intent
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.grupo5.workwatchapp.R
import com.grupo5.workwatchapp.ui.bossinterfaces.BossUI
import com.grupo5.workwatchapp.ui.recovery.RecoveryAccount
import com.grupo5.workwatchapp.ui.theme.WorkWatchAppTheme

// Declaration of the LogIn composable
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LogInView(viewModel: LoginViewModel) {
    val email: String by viewModel.email.observeAsState(initial = "")
    val password: String by viewModel.password.observeAsState(initial = "")
    val context = LocalContext.current

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
            ClickableText(
                text = AnnotatedString("Forgot password?"),
                onClick = {
                    val intent = Intent(context, RecoveryAccount::class.java)
                    context.startActivity(intent)
                }
            )
        }

        LoginButton(viewModel = viewModel)

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

    var isPasswordVisible by remember {
        mutableStateOf(false)
    }

    Box(Modifier.padding(8.dp)) {
        OutlinedTextField(
            value = password,
            onValueChange = { onTextFieldChanged(it) },
            label = { Text("Password") },
            visualTransformation =
            if (isPasswordVisible)
                VisualTransformation.None
            else
                PasswordVisualTransformation(),

            trailingIcon = {
                Icon(painter =
                if (isPasswordVisible)
                    painterResource(id = R.drawable.visibility_on)
                else
                    painterResource(id = R.drawable.visibility_off),
                    contentDescription = null,
                    modifier = Modifier.clickable { isPasswordVisible = !isPasswordVisible }
                )
            }
        )
    }
}

@Composable
fun LoginButton(
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
                val intent = Intent(context, BossUI::class.java)
                intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                context.startActivity(intent)
            }
            result.onFailure { exception ->
                Toast.makeText(context, exception.message ?: "", Toast.LENGTH_SHORT).show()
            }
        }
    }
}



@Preview(showSystemUi = true)
@Composable
fun LogInPreview() {

    WorkWatchAppTheme {
        LogInView(viewModel = LoginViewModel())
    }
}