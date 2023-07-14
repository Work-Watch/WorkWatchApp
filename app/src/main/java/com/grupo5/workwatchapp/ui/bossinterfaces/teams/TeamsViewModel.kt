package com.grupo5.workwatchapp.ui.bossinterfaces.teams

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.grupo5.workwatchapp.RetrofitApplication
import com.grupo5.workwatchapp.network.dto.teams.TeamsListResponse
import com.grupo5.workwatchapp.repository.TeamsRepository
import kotlinx.coroutines.launch

class TeamsViewModel(private val repository: TeamsRepository, private val token: String): ViewModel() {

    private val _status = MutableLiveData<TeamsUiStates>(TeamsUiStates.Resume)

    val status: MutableLiveData<TeamsUiStates>
        get() = _status


    val state = mutableStateOf<List<TeamsListResponse>>(emptyList())

    private fun teamsList(){
        viewModelScope.launch {
            val response = repository.getTeamsList(token)
            val teamsListResponse = response.body()?.let {
                listOf(it)
            } ?: emptyList()
            state.value = teamsListResponse
            Log.d("Teams", teamsListResponse.toString())//ojo
        }
    }

    fun onTeams(){
        teamsList()
    }

    companion object{
        val Factory = viewModelFactory {
            initializer {
                val app = this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as RetrofitApplication
                val token = app.getToken()
                TeamsViewModel(app.teamsRepository, token)

            }
        }
    }
}