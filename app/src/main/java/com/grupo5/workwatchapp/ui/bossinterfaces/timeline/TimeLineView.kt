package com.grupo5.workwatchapp.ui.bossinterfaces.timeline

import android.os.Bundle
import android.os.PersistableBundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.grupo5.workwatchapp.ui.theme.WorkWatchAppTheme

class TimeLineView : ComponentActivity(){
    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)
        setContent{
            WorkWatchAppTheme() {
                Surface() {

                }

            }
        }
    }
}
@Composable
fun TimelineScreen() {
    val activities = listOf(
        "Actividad 1",
        "Actividad 2",
        "Actividad 3",
        "Actividad 4",
        "Actividad 5"
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        activities.forEachIndexed { index, activity ->
            TimelineItem(activity, index != activities.lastIndex)
        }
    }
}

@Composable
fun TimelineItem(activity: String, showLine: Boolean) {
    Row(
        modifier = Modifier
            .padding(vertical = 8.dp)
    ) {
        Card(
            modifier = Modifier
                .wrapContentSize(),
            shape = RoundedCornerShape(8.dp),
            /*elevation = 4.dp*/
        ) {
            // Contenido de la card de actividad
            Column(modifier = Modifier
                .padding(8.dp)){
                Row() {
                    Text(text = "Task:")
                    Text(text = " Name of the assigned task")
                }
                Row() {
                    Text(text = "Name:")
                    Text(text = " Name of the assigned task")
                }
            }
        }

        Spacer(modifier = Modifier.width(16.dp))

        if (showLine) {
            LineConnector()
            Spacer(modifier = Modifier.width(16.dp))
        }
    }
}

@Composable
fun LineConnector() {
    Column(
        modifier = Modifier
            .width(2.dp)
            .fillMaxHeight()
    ) {
        val numberOfPoints = 6 // Ajusta el número de puntos según tus necesidades

        repeat(numberOfPoints) {
            Spacer(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
                    .background(color = Color.Gray, shape = CircleShape)
            )
        }
    }
}


@Preview(showSystemUi = true)
@Composable
fun TimeLinePreview(){
    WorkWatchAppTheme {
        TimelineScreen()
    }
}