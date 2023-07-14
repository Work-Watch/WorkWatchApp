package com.grupo5.workwatchapp.ui.bossinterfaces.task

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
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.grupo5.workwatchapp.network.dto.task.TaskListResponse
import com.grupo5.workwatchapp.network.dto.task.TaskRequest
import com.grupo5.workwatchapp.ui.theme.WorkWatchAppTheme


// This is the component for each team task card
@Composable
fun TaskCardView(task: TaskRequest) {
    Box(modifier = Modifier
        .fillMaxWidth()
        .padding(16.dp)){
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
                        text = task.task,
                        Modifier.padding(8.dp)
                    )
                }
                Row {
                    Text(
                        text = "Team:",
                        Modifier.padding(8.dp)
                    )
                    Text(
                        text = task.idTeam.toString(),
                        Modifier.padding(8.dp)
                    )
                }
                Row {
                    Text(
                        text = "Location:",
                        Modifier.padding(8.dp)
                    )
                    Text(
                        text = "${task.longitude}, ${task.latitude}",
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
                        imageVector = Icons.Filled.Edit,
                        contentDescription = "Edit task action",
                        tint = Color(android.graphics.Color.parseColor("#58AFB8")),
                        modifier = Modifier
                            .padding(end = 8.dp)
                            .size(26.dp)
                    )
                    Icon(
                        imageVector = Icons.Filled.Delete,
                        contentDescription = "Delete task action",
                        tint = Color(android.graphics.Color.parseColor("#EC225E")),
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
        val task = TaskRequest(
            task = "Clean PC",
            latitude = 22.67832456,
            longitude = 33.4353445,
            hourStart = "11",
            hourFinal = "11",
            date = "11/04/2023",
            idTeam = 1
        )
        TaskCardView(task = task)
    }
}
