package com.grupo5.workwatchapp.ui.theme.employee


import androidx.compose.foundation.Image

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
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
            color = colorResource(id = R.color.tertiaryColor)
        )

        Row() {
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

            OutlinedTextField(value = "", onValueChange = {},
                modifier = Modifier.size(50.dp)
            )

            OutlinedTextField(value = "", onValueChange = {},
                modifier = Modifier.size(50.dp)
            )
        }

        Button(onClick = { /*TODO*/ },
            colors = ButtonDefaults.buttonColors(colorResource(id = R.color.tertiaryColor)),
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