package com.grupo5.workwatchapp.ui.employee.task

import android.os.Bundle
import android.os.PersistableBundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.grupo5.workwatchapp.ui.bossinterfaces.task.TaskCardView
import com.grupo5.workwatchapp.ui.theme.WorkWatchAppTheme

class EmployeeTaskManagement : ComponentActivity(){
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
fun EmployeeTasksView(){
    Column(Modifier.padding(16.dp)){
        Box(
            Modifier
                .fillMaxWidth()
                .padding(16.dp)) {
            Text(
                text = "Tasks",
                fontSize = 48.sp
            )
        }




    }

}

@Preview(showSystemUi = true)
@Composable
fun EmployeeTaskViewPreview(){
    WorkWatchAppTheme {
        EmployeeTasksView()
    }
}
