package com.grupo5.workwatchapp.ui.bossinterfaces.task

import android.app.Activity
import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.os.Bundle
import android.widget.DatePicker
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.grupo5.workwatchapp.R
import com.grupo5.workwatchapp.ui.theme.WorkWatchAppTheme
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.dimensionResource
import java.util.Calendar

class NewTasks : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            NewTask()
        }
    }
}

@Preview(showSystemUi = true)
@Composable
fun NewTasks(modifier: Modifier = Modifier) {
    WorkWatchAppTheme {
        NewTask()
    }
}

@Composable
fun NewTask() {
    val hourStart = remember { mutableStateOf("") }
    val date = remember { mutableStateOf("") }
    val selectedTime = remember { mutableStateOf("") }

    val context = LocalContext.current

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .verticalScroll(rememberScrollState())
            .padding(16.dp).wrapContentSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(modifier = Modifier.padding(top = 16.dp)) {
            Text(
                text = stringResource(R.string.create_task),
                style = TextStyle(fontSize = 40.sp),
                color = colorResource(id = R.color.aqua_clear_custom),
            )
        }

        Spacer(modifier = Modifier.height(8.dp))
        Task()
        TimesStartButton()
        DateButton()
        DestinationLatitudeField()
        DestinationLongitudeField()


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

            },
                colors = ButtonDefaults.buttonColors(colorResource(id = R.color.aqua_dark_custom)),
                modifier = Modifier
                    .padding(
                        top = dimensionResource(id = R.dimen.common_padding_default),
                        start = dimensionResource(id = R.dimen.common_padding_default),
                        end = dimensionResource(id = R.dimen.common_padding_default)
                    )
            ){
                Text(text = "Create task")
            }
        }
    }
}



@Composable
fun Task() {

    var task by remember { mutableStateOf("") }
    Box(Modifier.padding(8.dp)) {
        OutlinedTextField(
            value = task,
            onValueChange = { task = it },
            label = { Text(text = "New task") }
        )
    }
}

@Composable
fun TimesStartButton() {
    val context = LocalContext.current
    val calendar = Calendar.getInstance()

    var showTimePickerDialog by remember { mutableStateOf(false) }
    var selectedTimeText by remember { mutableStateOf("") }

    val hour = calendar.get(Calendar.HOUR_OF_DAY)
    val minute = calendar.get(Calendar.MINUTE)

    val selectedTime by remember { mutableStateOf("") }

    val timePicker = TimePickerDialog(
        context,
        { _, selectedHour: Int, selectedMinute: Int ->
            selectedTimeText = "$selectedHour:$selectedMinute"
        }, hour, minute, true
    )

    Box(Modifier.padding(8.dp)){
        OutlinedTextField(
            value = selectedTimeText,
            onValueChange = { selectedTimeText = it },
            enabled = false,
            modifier = Modifier.clickable { timePicker.show() },
            label = { Text(text = "Hour", color = Color.Black)},
            colors = TextFieldDefaults.colors(Color.Black)
        )
    }
}

@Composable
fun DateButton() {
    val context = LocalContext.current
    val calendar = Calendar.getInstance()

    var selectedDateText by remember { mutableStateOf("") }

    // Fetching current year, month and day
    val year = calendar.get(Calendar.YEAR)
    val month = calendar.get(Calendar.MONTH)
    val dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH)

    val datePicker = DatePickerDialog(
        context,
        { _: DatePicker, selectedYear: Int, selectedMonth: Int, selectedDayOfMonth: Int ->
            selectedDateText = "$selectedDayOfMonth/${selectedMonth + 1}/$selectedYear"
        }, year, month, dayOfMonth
    )

    Box(Modifier.padding(8.dp)){
        OutlinedTextField(
            value = selectedDateText,
            onValueChange = { selectedDateText = it },
            enabled = false,
            modifier = Modifier.clickable { datePicker.show()},
            label = { Text(text = "Date", color = Color.Black)},
            colors = TextFieldDefaults.colors(Color.Black)
        )
    }

}


@Composable
fun DestinationLatitudeField(){

    var latitude by remember { mutableStateOf("") }

    Box(Modifier.padding(8.dp)) {
        OutlinedTextField(
            value = latitude,
            onValueChange = { latitude = it },
            label = { Text(text = "Latitude")}
        )
    }
}


@Composable
fun DestinationLongitudeField(){

    var longitude by remember { mutableStateOf("") }

    Box(Modifier.padding(8.dp)) {
        OutlinedTextField(
            value = longitude,
            onValueChange = { longitude = it},
            label = { Text(text = "Longitude")}
        )
    }
}