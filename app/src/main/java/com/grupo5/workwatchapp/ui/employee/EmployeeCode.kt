package com.grupo5.workwatchapp.ui.employee


import androidx.compose.foundation.Image

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.grupo5.workwatchapp.R
import com.grupo5.workwatchapp.ui.theme.WorkWatchAppTheme


@Preview(showBackground = true, showSystemUi = true)
@Composable
fun EmployeeCodePreview(){
    WorkWatchAppTheme {
        EmployeeCode()
    }
}

@Composable
fun EmployeeCode(modifier: Modifier = Modifier){
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Image(painter = painterResource(id = R.drawable.logo_de_vivo_mesa_de_trabajo_1), contentDescription = "")

        Text(text = stringResource(id = R.string.enter_code),
            style = MaterialTheme.typography.titleLarge,
            color = colorResource(id = R.color.aqua_clear_custom)
        )

        Row() {

            var firstNumber by remember{
                mutableStateOf("")
            }

            var secondNumber by remember {
                mutableStateOf("")
            }

            var threeNumber by remember {
                mutableStateOf("")
            }

            var fourthNumber by remember {
                mutableStateOf("")
            }

            var fifthNumber by remember {
                mutableStateOf("")
            }

            var sixthNumber by remember {
                mutableStateOf("")
            }

            OutlinedTextField(value = firstNumber, onValueChange = {firstNumber = it},
                modifier = Modifier.size(50.dp)
            )



            OutlinedTextField(value = secondNumber, onValueChange = {},
                modifier = Modifier.size(50.dp)
            )

            OutlinedTextField(value = "", onValueChange = {},
                modifier = Modifier.size(50.dp)
            )

            OutlinedTextField(value = "", onValueChange = {},
                modifier = Modifier.size(50.dp)
            )

            OutlinedTextField(value = "", onValueChange = {},
                modifier = Modifier.size(50.dp)
            )

            OutlinedTextField(value = "", onValueChange = {},
                modifier = Modifier.size(50.dp)
            )
        }

        Button(onClick = { /*TODO*/ },
            colors = ButtonDefaults.buttonColors(colorResource(id = R.color.aqua_dark_custom)),
            modifier = Modifier
                .padding(
                    top = dimensionResource(id = R.dimen.common_padding_default),
                    start = dimensionResource(id = R.dimen.common_padding_default),
                    end = dimensionResource(id = R.dimen.common_padding_default)
                )

        )   {
            Text(text = "Ready")
        }

    }
}


