package com.grupo5.workwatchapp.ui.employee.registeruser

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
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
import com.grupo5.workwatchapp.ui.bossinterfaces.BossUI
import com.grupo5.workwatchapp.ui.theme.WorkWatchAppTheme

class RegisterUser : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            EnterData()
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun EnterDataPreview(){
    WorkWatchAppTheme {
        EnterData()
    }
}

@Composable
fun EnterData(modifier: Modifier = Modifier, viewModel: RegisterViewModel = viewModel(factory = RegisterViewModel.Factory)) {

    val context = LocalContext.current

    val name: String by  viewModel.name.observeAsState(initial = "")
    val confirmPassword: String by  viewModel.confirmPassword.observeAsState(initial = "")
    val phone: String by  viewModel.phone.observeAsState(initial = "")
    val lastName: String by  viewModel.lastName.observeAsState(initial = "")
    val email: String by  viewModel.email.observeAsState(initial = "")
    val company: String by  viewModel.company.observeAsState(initial = "")
    val password: String by  viewModel.password.observeAsState(initial = "")
    //var isPasswordVisible by remember {mutableStateOf(false)}


    Column(modifier = modifier
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

        NameField(name) { viewModel.onRegisterChanged(it, lastName, phone, email, company, password, confirmPassword) }
        OutField(lastName) { viewModel.onRegisterChanged(name, it, phone, email, company, password, confirmPassword) }
        OutField(phone) { viewModel.onRegisterChanged(name, lastName, it, email, company, password, confirmPassword) }
        OutField(email) { viewModel.onRegisterChanged(name, lastName, phone, it, company, password, confirmPassword) }
        OutField(company) { viewModel.onRegisterChanged(name, lastName, phone, email, it, password, confirmPassword) }
        PasswordField(password) { viewModel.onRegisterChanged(name, lastName, phone, email, company, it, confirmPassword) }
        OutField(confirmPassword) { viewModel.onRegisterChanged(name, lastName, phone, email, company, password, it) }

        /*OutlinedTextField(value = lastName, onValueChange = { },
            label = { Text(text = stringResource(id = R.string.last_name))},
            singleLine = true,
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    top = dimensionResource(id = R.dimen.common_padding_default),
                    start = dimensionResource(id = R.dimen.common_padding_default),
                    end = dimensionResource(id = R.dimen.common_padding_default)
                ),

            trailingIcon = {
                if(lastName.isNotEmpty()){
                    Icon(imageVector = Icons.Filled.Clear, contentDescription = "Clean",
                        modifier = Modifier
                            .clickable {  }
                    )
                }
            }
        )

        OutlinedTextField(value = phone, onValueChange = {},
            label = { Text(text = stringResource(id = R.string.phone_number))},
            singleLine = true,
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    top = dimensionResource(id = R.dimen.common_padding_default),
                    start = dimensionResource(id = R.dimen.common_padding_default),
                    end = dimensionResource(id = R.dimen.common_padding_default)
                ),

            trailingIcon = {
                if(phone.isNotEmpty()){
                    Icon(imageVector = Icons.Filled.Clear, contentDescription = "Clean",
                        modifier = Modifier
                            .clickable { }
                    )
                }
            }
        )

        OutlinedTextField(value = email, onValueChange = {},
            label = { Text(text = stringResource(id = R.string.enter_email))},
            singleLine = true,
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    top = dimensionResource(id = R.dimen.common_padding_default),
                    start = dimensionResource(id = R.dimen.common_padding_default),
                    end = dimensionResource(id = R.dimen.common_padding_default)
                ),

            trailingIcon = {
                if(email.isNotEmpty()){
                    Icon(imageVector = Icons.Filled.Clear, contentDescription = "Clean",
                        modifier = Modifier
                            .clickable {  }
                    )
                }
            }
        )

        OutlinedTextField(value = company, onValueChange = {},
            label = { Text(text = stringResource(id = R.string.enter_prise_name))},
            singleLine = true,
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    top = dimensionResource(id = R.dimen.common_padding_default),
                    start = dimensionResource(id = R.dimen.common_padding_default),
                    end = dimensionResource(id = R.dimen.common_padding_default)
                ),

            trailingIcon = {
                if(company.isNotEmpty()){
                    Icon(imageVector = Icons.Filled.Clear, contentDescription = "Clean",
                        modifier = Modifier
                            .clickable { }
                    )
                }
            }
        )

        OutlinedTextField(value = password, onValueChange = {},
            label = { Text(text = stringResource(id = R.string.password))},
            singleLine = true,
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    top = dimensionResource(id = R.dimen.common_padding_default),
                    start = dimensionResource(id = R.dimen.common_padding_default),
                    end = dimensionResource(id = R.dimen.common_padding_default)
                ),
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


        OutlinedTextField(value = confirmPassword, onValueChange = {},
            label = { Text(text = stringResource(id = R.string.repeat_password))},
            singleLine = true,
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    top = dimensionResource(id = R.dimen.common_padding_default),
                    start = dimensionResource(id = R.dimen.common_padding_default),
                    end = dimensionResource(id = R.dimen.common_padding_default)
                ),

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
        )*/

        Row(modifier = modifier.padding(16.dp))
        {
            Button(onClick = { /*TODO*/ },
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
                val intent = Intent(context, BossUI::class.java)
                context.startActivity(intent)
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