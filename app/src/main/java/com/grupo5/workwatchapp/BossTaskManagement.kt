package com.grupo5.workwatchapp

import android.os.Bundle
import android.os.PersistableBundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.Surface
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