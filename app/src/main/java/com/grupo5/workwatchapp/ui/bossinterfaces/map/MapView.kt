package com.grupo5.workwatchapp.ui.bossinterfaces.map

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.google.maps.android.compose.GoogleMap
import com.grupo5.workwatchapp.ui.theme.WorkWatchAppTheme

@Composable
fun MapView(){
    GoogleMap(modifier = Modifier)
}

@Preview(showSystemUi = true)
@Composable
fun MapPreview(){
    WorkWatchAppTheme {
        MapView()
    }
}