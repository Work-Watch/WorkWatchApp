package com.grupo5.workwatchapp.ui.bossinterfaces.task

import android.content.Intent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.grupo5.workwatchapp.R
import com.grupo5.workwatchapp.ui.theme.WorkWatchAppTheme

@Composable
fun BossTasksView(viewModel: TaskViewModel = viewModel(factory = TaskViewModel.Factory)) {
    val context = LocalContext.current

    Column(Modifier.padding(16.dp)) {
        Box(
            Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Text(
                text = "Task",
                fontSize = 48.sp,
                color = Color.Black
            )
        }
        Box(
            modifier = Modifier.fillMaxWidth(),
            contentAlignment = Alignment.BottomCenter
        ) {
            Button(
                onClick = {
                    val intent = Intent(context, NewTaskScreen::class.java)
                    context.startActivity(intent)
                },
                modifier = Modifier.fillMaxWidth(),
                colors = ButtonDefaults.buttonColors(colorResource(id = R.color.aqua_clear_custom))
            ) {
                Text(text = "Add task")
            }
        }


    }
}

/*
@Composable
fun PokemonListScreen() {
    val viewModel: TaskViewModel = viewModel(factory = TaskViewModel.Factory)
    LazyColumn(
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        item {
            Text(
                "Task",
                style = MaterialTheme.typography.titleMedium
            )
        }
        items(viewModel.state.value) { pokemon ->
            CardPreview(pokemon = pokemon) { name ->
                viewModel.toggleFavorite(name)
            }
        }
    }
}
*/

@Preview(showSystemUi = true)
@Composable
fun BossTaskViewPreview(){
    WorkWatchAppTheme {
        BossTasksView()
    }
}