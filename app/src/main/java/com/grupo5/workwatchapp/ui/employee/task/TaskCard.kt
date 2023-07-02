package com.grupo5.workwatchapp.ui.employee.task

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Face
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.grupo5.workwatchapp.ui.theme.WorkWatchAppTheme


// This is the component for each team task card
@Composable
fun TaskCardView() {
    Box(modifier = Modifier
        .fillMaxWidth()
        .padding(16.dp)
        .wrapContentSize()){
        Card(
            Modifier
                .fillMaxWidth()
                ) {
            // Card content
            Column(modifier = Modifier
                .background(color = Color.LightGray)) {
                Icon(
                    imageVector = Icons.Filled.Close,
                    contentDescription = "Localized description",
                    Modifier.padding(8.dp)
                )
                Row {
                    Text(
                        text = "Task:",
                        Modifier.padding(8.dp)
                    )
                    Text(
                        text = "Description of the task",
                        Modifier.padding(8.dp)
                    )
                }
                Row {
                    Text(
                        text = "Team:",
                        Modifier.padding(8.dp)
                    )
                    Text(
                        text = "Team ID",
                        Modifier.padding(8.dp)
                    )
                }
                Row {
                    Text(
                        text = "Location:",
                        Modifier.padding(8.dp)
                    )
                    Text(
                        text = "Address of the place",
                        Modifier.padding(8.dp)
                    )
                }
                Row {
                    Text(
                        text = "Status:",
                        Modifier.padding(8.dp)
                    )
                    Text(
                        text = "Active or inactive",
                        Modifier.padding(8.dp)
                    )
                }
                Row(
                    Modifier
                        .fillMaxWidth()
                        .padding(8.dp),
                    horizontalArrangement = Arrangement.End
                ) {
                    Icon(
                        imageVector = Icons.Filled.Check,
                        contentDescription = "Edit task action",
                        tint = Color(android.graphics.Color.parseColor("#58AFB8")),
                        modifier = Modifier
                            .padding(end = 8.dp)
                            .size(26.dp)
                    )
                }
            }
        }
    }
}




@Preview(showSystemUi = true)
@Composable
fun taskCardViewPreview(){
    WorkWatchAppTheme {
        TaskCardView()
        
    }
}