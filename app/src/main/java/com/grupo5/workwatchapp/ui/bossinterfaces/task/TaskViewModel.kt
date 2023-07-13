package com.grupo5.workwatchapp.ui.bossinterfaces.task

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.grupo5.workwatchapp.RetrofitApplication
import com.grupo5.workwatchapp.network.ApiResponse
import com.grupo5.workwatchapp.network.dto.task.TaskListResponse
import com.grupo5.workwatchapp.repository.TaskRepository
import kotlinx.coroutines.launch

class TaskViewModel(private val repository: TaskRepository, private val token: String): ViewModel() {

    private val _status = MutableLiveData<TaskUiStates>(TaskUiStates.Resume)

    val status: MutableLiveData<TaskUiStates>
        get() = _status

    val state = mutableStateOf<List<TaskListResponse>>(emptyList())

    private fun taskList() {
        viewModelScope.launch {
            val response = repository.taskList(token)
            val taskListResponse = response.body()?.let {
                listOf(it)
            } ?: emptyList()
            state.value = taskListResponse
            Log.d("TASK2", taskListResponse.toString())
        }
    }

    fun onTask(){
        taskList()
    }

    companion object {
        val Factory = viewModelFactory {
            initializer {
                val app = this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as RetrofitApplication
                val token = app.getToken()
                TaskViewModel(app.taskRepository, token)
            }
        }
    }

}