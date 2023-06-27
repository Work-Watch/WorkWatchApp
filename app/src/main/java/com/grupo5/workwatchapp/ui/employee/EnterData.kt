package com.grupo5.workwatchapp.ui.employee

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.ComposableOpenTarget
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.grupo5.workwatchapp.R
import com.grupo5.workwatchapp.ui.theme.WorkWatchAppTheme


@Preview(showBackground = true, showSystemUi = true)
@Composable
fun EnterDataPreview(){
    WorkWatchAppTheme {
        EnterData()
    }
}


@Composable
fun EnterData(modifier: Modifier = Modifier){
    Column(modifier = modifier.verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

        Text(text = stringResource(id = R.string.enter_your_data),
            style = MaterialTheme.typography.titleLarge,
            color = colorResource(id = R.color.aqua_clear_custom)

        )

        var name by remember {
            mutableStateOf("")
        }

        OutlinedTextField(value = name, onValueChange = {name = it},
                label = { Text(text = stringResource(id = R.string.enter_name))},
                singleLine = true,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(
                        top = dimensionResource(id = R.dimen.common_padding_default),
                        start = dimensionResource(id = R.dimen.common_padding_default),
                        end = dimensionResource(id = R.dimen.common_padding_default)
                    ),

                trailingIcon = {
                    if(name.isNotEmpty()){
                        Icon(imageVector = Icons.Filled.Clear, contentDescription = "Clean",
                            modifier = Modifier
                                .clickable { name = "" }
                        )
                    }
                }
            )

        var lastName by remember {
            mutableStateOf("")
        }
        OutlinedTextField(value = lastName, onValueChange = {lastName = it},
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
                            .clickable { lastName = "" }
                    )
                }
            }
        )

        var phoneNumber by remember {
            mutableStateOf("")
        }
        OutlinedTextField(value = phoneNumber, onValueChange = {phoneNumber = it},
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
                if(phoneNumber.isNotEmpty()){
                    Icon(imageVector = Icons.Filled.Clear, contentDescription = "Clean",
                        modifier = Modifier
                            .clickable { phoneNumber = "" }
                    )
                }
            }
        )

        var email by remember {
            mutableStateOf("")
        }
        OutlinedTextField(value = email, onValueChange = {email = it},
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
                            .clickable { email = "" }
                    )
                }
            }
        )

        var company by remember {
            mutableStateOf("")
        }
        OutlinedTextField(value = company, onValueChange = {company = it},
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
                            .clickable { company = "" }
                    )
                }
            }
        )

        var passwordValue by remember {
            mutableStateOf("")
        }

        var isPasswordVisible by remember {
            mutableStateOf(false)
        }

        OutlinedTextField(value = passwordValue, onValueChange = {passwordValue = it},
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

        var passwordRepeatValue by remember {
            mutableStateOf("")
        }

        OutlinedTextField(value = passwordRepeatValue, onValueChange = {passwordRepeatValue = it},
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
        )

        Row(modifier = modifier
            .padding(32.dp)
        ) {

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

            Button(onClick = { /*TODO*/ },
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
