package com.grupo5.workwatchapp.ui.BossInterfaces.Task

import android.os.Bundle
import android.os.PersistableBundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.grupo5.workwatchapp.R
import com.grupo5.workwatchapp.ui.theme.WorkWatchAppTheme

class AddTaskView : ComponentActivity(){
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
fun addTaskView(){

    // Header
    Column(modifier = Modifier.fillMaxSize()){
        Box(modifier = Modifier
            .clip(
                RoundedCornerShape(
                    bottomEnd = 16.dp,
                    bottomStart = 16.dp))
        ){
            Column(modifier = Modifier
                .fillMaxWidth()
                .background(
                    color = Color(android.graphics.Color.parseColor("#269199"))
                )
            ){
                Icon(imageVector = Icons.Default.ArrowBack,
                    contentDescription = "Back action",
                    Modifier
                        .padding(16.dp)
                        .size(32.dp),
                    tint = Color.White
                )
                Box(Modifier.padding(16.dp)){
                    Text(text = "Add Task",
                        fontSize = 46.sp,
                        color = Color.White
                    )
                }
            }
        }

        // Body
        Box(modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
            //.background(Color(R.color.red_cherry_custom))
            .clip(RoundedCornerShape(16.dp)),
            contentAlignment = Alignment.Center
            ){

            val textFieldValue = remember { mutableStateOf("")}

            Column(modifier = Modifier
                .fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally) {
                OutlinedTextField(
                    value = textFieldValue.value,
                    onValueChange = { textFieldValue.value = it },
                    placeholder = { Text(text = "New task assignment")},
                    enabled = true,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(180.dp)
                        .padding(16.dp)
                        .background(Color.LightGray)
                        /*.border(
                            width = 1.dp,
                            color = Color.Transparent)*/
                )


                // Dropdown menu
                val context = LocalContext.current
                var expanded by remember { mutableStateOf(false) }
                Box(modifier = Modifier
                    .padding(16.dp)) {

                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(color = Color.LightGray),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Box(modifier = Modifier
                            .padding(12.dp)) {
                            Text("Select team")
                        }
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .wrapContentSize(Alignment.TopEnd)

                        ) {

                            IconButton(onClick = { expanded = !expanded }) {
                                Icon(
                                    imageVector = Icons.Default.MoreVert,
                                    contentDescription = "More"
                                )
                            }

                            DropdownMenu(
                                expanded = expanded,
                                onDismissRequest = { expanded = false }
                            ) {
                                DropdownMenuItem(
                                    text = { Text("Load") },
                                    onClick = {
                                        Toast.makeText(context, "Load", Toast.LENGTH_SHORT).show()
                                    }
                                )
                                DropdownMenuItem(
                                    text = { Text("Save") },
                                    onClick = {
                                        Toast.makeText(context, "Save", Toast.LENGTH_SHORT).show()
                                    }
                                )
                            }
                        }
                    }
                }

                // Here goes the GPS picker
                Button(onClick = { /*TODO*/ },
                    colors = ButtonDefaults.buttonColors(colorResource(id = R.color.aqua_dark_custom))
                ){
                    Text(text = "Save")
                }
            }
        }
    }
}



@Preview(showSystemUi = true)
@Composable
fun addTaskViewPreview(){
    WorkWatchAppTheme {
        addTaskView()

    }
}