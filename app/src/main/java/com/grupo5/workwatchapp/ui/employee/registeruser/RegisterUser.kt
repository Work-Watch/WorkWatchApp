package com.grupo5.workwatchapp.ui.employee.registeruser

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
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
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.grupo5.workwatchapp.R
import com.grupo5.workwatchapp.ui.bossinterfaces.BossHomeView
import com.grupo5.workwatchapp.ui.bossinterfaces.BossUI
import com.grupo5.workwatchapp.ui.theme.WorkWatchAppTheme

class RegisterUser : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            EnterDataPre()
        }
    }
}
@Preview(showSystemUi = true)
@Composable
fun EnterDataPre(modifier: Modifier = Modifier){
    WorkWatchAppTheme {
        EnterData()
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EnterData(viewModel: RegisterViewModel = viewModel(factory = RegisterViewModel.Factory)) {

    val context = LocalContext.current
    val status by viewModel.status.observeAsState()

    val name: String by  viewModel.name.observeAsState(initial = "")
    val confirmPassword: String by  viewModel.confirmPassword.observeAsState(initial = "")
    val phone: String by  viewModel.phone.observeAsState(initial = "")
    val lastName: String by  viewModel.lastName.observeAsState(initial = "")
    val email: String by  viewModel.email.observeAsState(initial = "")
    val company: String by  viewModel.company.observeAsState(initial = "")
    val password: String by  viewModel.password.observeAsState(initial = "")
    val idRol by viewModel.idRol.observeAsState(initial = 1)


    Column(modifier = Modifier
        .fillMaxWidth()
        .verticalScroll(rememberScrollState())
        .padding(8.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Box(modifier = Modifier.padding(top = 10.dp)) {

            Text(
                text = stringResource(id = R.string.create_account),
                style =
                TextStyle (
                    fontSize = 30.sp
                ),
                color = colorResource(id = R.color.aqua_clear_custom),
            )
        }

        NameField(name) { viewModel.onRegisterChanged(it, lastName, phone, email, company, password, confirmPassword, idRol) }
        LastnameField(lastName) { viewModel.onRegisterChanged(name, it, phone, email, company, password, confirmPassword, idRol) }
        PhonenumberField(phone) { viewModel.onRegisterChanged(name, lastName, it, email, company, password, confirmPassword, idRol) }
        EmailField(email) { viewModel.onRegisterChanged(name, lastName, phone, it, company, password, confirmPassword, idRol) }
        CompanynameField(company) { viewModel.onRegisterChanged(name, lastName, phone, email, it, password, confirmPassword, idRol) }
        PasswordField(password) { viewModel.onRegisterChanged(name, lastName, phone, email, company, it, confirmPassword, idRol) }
        ConfirmpasswordField(confirmPassword) { viewModel.onRegisterChanged(name, lastName, phone, email, company, password, it, idRol) }

        Row(modifier = Modifier.padding(16.dp))
        {
            Button(onClick = {
                (context as? Activity)?.finish()
            },
                colors = ButtonDefaults.buttonColors(colorResource(id = R.color.red_cherry_custom)),
                modifier = Modifier
                    .padding(
                        top = dimensionResource(id = R.dimen.common_padding_default),
                        start = dimensionResource(id = R.dimen.common_padding_default),
                        end = dimensionResource(id = R.dimen.common_padding_default)
                    )
            ){
                Text(text = "Cancel")
            }

            Button(onClick = {

                if (!viewModel.validateData()) {
                    Toast.makeText(context, "Empty fields", Toast.LENGTH_SHORT).show()
                } else {
                    viewModel.onRegister()
                }
            },
                colors = ButtonDefaults.buttonColors(colorResource(id = R.color.aqua_dark_custom)),
                modifier = Modifier
                    .padding(
                        top = dimensionResource(id = R.dimen.common_padding_default),
                        start = dimensionResource(id = R.dimen.common_padding_default),
                        end = dimensionResource(id = R.dimen.common_padding_default)
                    )
            ){
                Text(text = "Register")
            }
        }
    }

    LaunchedEffect(status) {
        status?.let { status ->
            when (status) {
                is RegisterUiStates.Error -> {
                    Toast.makeText(context, "An error has occurred", Toast.LENGTH_SHORT).show()
                    viewModel.clearStatus()
                }
                is RegisterUiStates.ErrorWithMessage -> {
                    Toast.makeText(context, status.message, Toast.LENGTH_SHORT).show()
                    viewModel.clearStatus()
                }
                is RegisterUiStates.Success -> {
                    viewModel.clearStatus()
                    val intent = Intent(context, BossUI::class.java)
                    context.startActivity(intent)
                    (context as? Activity)?.finish()
                }

                else -> {}
            }
        }
    }
}

@Composable
fun NameField(name: String, onTextFieldChanged: (String) -> Unit) {
    Box(Modifier.padding(8.dp)) {
        OutlinedTextField(
            value = name,
            onValueChange = { onTextFieldChanged(it) },
            label = { Text("Name") }
        )
    }
}

@Composable
fun LastnameField(lastName: String, onTextFieldChanged: (String) -> Unit){
    Box(Modifier.padding(8.dp)) {
        OutlinedTextField(
            value = lastName,
            onValueChange = { onTextFieldChanged(it) },
            label = { Text(text = "Last name")}
        )
    }
}

@Composable
fun PhonenumberField(phone:String, onTextFieldChanged: (String) -> Unit){
    Box(Modifier.padding(8.dp)) {
        OutlinedTextField(
            value = phone,
            onValueChange = {onTextFieldChanged(it) },
            label = {Text("Phone Number")}
        )
    }
}

@Composable
fun EmailField(email:String, onTextFieldChanged: (String) -> Unit){
    Box(Modifier.padding(8.dp)) {
        OutlinedTextField(
            value = email,
            onValueChange = {onTextFieldChanged(it) },
            label = {Text("Email")}
        )
    }
}

@Composable
fun CompanynameField(company:String, onTextFieldChanged: (String) -> Unit){
    Box(Modifier.padding(8.dp)) {
        OutlinedTextField(
            value = company,
            onValueChange = {onTextFieldChanged(it) },
            label = {Text("Company Name")}
        )
    }
}

@Composable
fun PasswordField(password: String, onTextFieldChanged: (String) -> Unit) {

    var isPasswordVisible by remember { mutableStateOf(false) }

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
fun ConfirmpasswordField(confirmPassword: String, onTextFieldChanged: (String) -> Unit) {

    var isConfirmPasswordVisible by remember { mutableStateOf(false) }

    Box(Modifier.padding(8.dp)) {
        OutlinedTextField(
            value = confirmPassword,
            onValueChange = { onTextFieldChanged(it) },
            label = { Text("Confirm password") },
            visualTransformation =
            if (isConfirmPasswordVisible)
                VisualTransformation.None
            else
                PasswordVisualTransformation(),

            trailingIcon = {
                Icon(painter =
                if (isConfirmPasswordVisible)
                    painterResource(id = R.drawable.visibility_on)
                else
                    painterResource(id = R.drawable.visibility_off),
                    contentDescription = null,
                    modifier = Modifier.clickable { isConfirmPasswordVisible = !isConfirmPasswordVisible }
                )
            }
        )
    }
}


