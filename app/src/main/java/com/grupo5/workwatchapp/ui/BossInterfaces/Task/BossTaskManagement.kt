package com.grupo5.workwatchapp.ui.BossInterfaces.Task

import android.os.Bundle
import android.os.PersistableBundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.grupo5.workwatchapp.R
import com.grupo5.workwatchapp.ui.theme.WorkWatchAppTheme

class BossTaskManagement : ComponentActivity(){
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
fun bossTasksView(){

    Column(Modifier.padding(16.dp)){
        Box(
            Modifier
                .fillMaxWidth()
                .padding(16.dp)) {
            Text(
                text = "Task",
                fontSize = 48.sp
            )
        }
        Box(modifier = Modifier.fillMaxWidth(),
            contentAlignment = Alignment.BottomCenter){
            Button(
                onClick = { /*TODO*/ },
                modifier = Modifier.fillMaxWidth(),
                colors = ButtonDefaults.buttonColors(colorResource(id = R.color.aqua_clear_custom))
            ) {
            Text(text = "Add task")
            }
        }

        /* TODO("Implemetar la equivalencia de recycleview
            en compose para usar "taskCardView()" de manera
            recursiva") */
        
        taskCardView()

    }

}

@Preview(showSystemUi = true)
@Composable
fun taskViewPreview(){
    WorkWatchAppTheme {
        bossTasksView()
    }
}
