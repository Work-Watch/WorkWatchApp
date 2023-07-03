package com.grupo5.workwatchapp.ui.bossinterfaces.map

import android.Manifest
import android.annotation.SuppressLint
import android.content.Context
import android.content.pm.PackageManager
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.tooling.preview.Preview
import com.google.android.gms.maps.GoogleMap
import androidx.compose.ui.viewinterop.AndroidView
import androidx.core.app.ActivityCompat
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.LifecycleObserver
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.MapView
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.grupo5.workwatchapp.ui.theme.WorkWatchAppTheme

@SuppressLint("RememberReturnType")
@Composable
fun MyMapView(onReady: (GoogleMap) -> Unit) {
    val context = LocalContext.current
    val mapView = remember { MapView(context) }
    val lifecycle = LocalLifecycleOwner.current.lifecycle
    val markers = remember { mutableStateListOf<LatLng>() }

    lifecycle.addObserver(RememberMapLifeCycle(map = mapView))

    AndroidView(factory = {
        mapView.apply {
            mapView.getMapAsync { googleMap ->
                val zoomLevel = 15f

                // Coords of the start location
                googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(LatLng(13.714581, -89.216985), zoomLevel))

                googleMap.setOnMapClickListener { latLng ->
                    markers.add(latLng)
                    googleMap.addMarker(MarkerOptions().position(latLng))
                }

                // Añadir marcadores existentes
                markers.forEach { latLng ->
                    googleMap.addMarker(MarkerOptions().position(latLng))
                }

                googleMap.uiSettings.isZoomControlsEnabled = true
                googleMap.uiSettings.isZoomGesturesEnabled = true
                googleMap.uiSettings.isMyLocationButtonEnabled = true
                googleMap.uiSettings.isMapToolbarEnabled = true

                onReady(googleMap)
            }
        }
    })
}

@Composable
fun RememberMapLifeCycle(map: MapView): LifecycleObserver {
    return remember {
        LifecycleEventObserver { _, event ->
            when (event) {
                Lifecycle.Event.ON_CREATE -> map.onCreate(Bundle())
                Lifecycle.Event.ON_START -> map.onStart()
                Lifecycle.Event.ON_RESUME -> map.onResume()
                Lifecycle.Event.ON_PAUSE -> map.onPause()
                Lifecycle.Event.ON_STOP -> map.onStop()
                Lifecycle.Event.ON_DESTROY -> map.onDestroy()
                Lifecycle.Event.ON_ANY -> throw IllegalStateException()
            }
        }
    }
}

@SuppressLint("RememberReturnType")
@Composable
fun MyMapView(context: Context, onReady: (GoogleMap, Context) -> Unit) {
    val mapView = remember { MapView(context) }
    val lifecycle = LocalLifecycleOwner.current.lifecycle
    val markers = remember { mutableStateListOf<LatLng>() }

    lifecycle.addObserver(RememberMapLifeCycle(map = mapView))

    AndroidView(factory = {
        mapView.apply {
            mapView.getMapAsync { googleMap ->
                val zoomLevel = 15f

                // Coords of the start location
                googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(LatLng(13.714581, -89.216985), zoomLevel))

                googleMap.setOnMapClickListener { latLng ->
                    markers.add(latLng)
                    googleMap.addMarker(MarkerOptions().position(latLng))
                }

                // Añadir marcadores existentes
                markers.forEach { latLng ->
                    googleMap.addMarker(MarkerOptions().position(latLng))
                }

                googleMap.uiSettings.isZoomControlsEnabled = true
                googleMap.uiSettings.isZoomGesturesEnabled = true
                googleMap.uiSettings.isMyLocationButtonEnabled = true
                googleMap.uiSettings.isMapToolbarEnabled = true

                onReady(googleMap, context)
            }
        }
    })
}

@Composable
fun MapScreen() {
    val permissions = arrayOf(
        Manifest.permission.ACCESS_FINE_LOCATION,
        Manifest.permission.ACCESS_COARSE_LOCATION
    )
    val requestCode = 42 // Puedes cambiar el número de solicitud según tus necesidades

    val locationPermissionGranted = remember { mutableStateOf(false) }

    val activity = LocalContext.current as? ComponentActivity

    ActivityCompat.requestPermissions(activity!!, permissions, requestCode)

    MyMapView(context = LocalContext.current) { googleMap, context ->
        if (ActivityCompat.checkSelfPermission(
                context,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) == PackageManager.PERMISSION_GRANTED
        ) {
            locationPermissionGranted.value = true
            googleMap.isMyLocationEnabled = true
        }
    }

    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        if (!locationPermissionGranted.value) {
            Text("Location permission denied")
        }
    }
}




@Preview(showSystemUi = true)
@Composable
fun MapPreview() {
    WorkWatchAppTheme {
        MapScreen()
    }
}

/*
Agregar dependencias: Asegúrate de tener las siguientes dependencias en tu archivo build.gradle:
groovy
Copy code
implementation 'com.google.android.gms:play-services-maps:17.0.0'
implementation 'com.google.maps.android:maps-ktx:3.1.0'
implementation 'androidx.activity:activity-compose:1.3.0'
Añadir los permisos en el AndroidManifest.xml: Asegúrate de tener los siguientes permisos en tu archivo AndroidManifest.xml:
xml
Copy code
<uses-permission android:name="android.permission.ACCESS_FINE_LOCATION" />
<uses-permission android:name="android.permission.ACCESS_COARSE_LOCATION" />
Agregar una barra de búsqueda de ubicaciones: Puedes utilizar la API de Geocodificación de Google para buscar ubicaciones por dirección o coordenadas. Puedes agregar un campo de entrada de texto y un botón de búsqueda en tu interfaz de usuario. Luego, al recibir la ubicación ingresada por el usuario, puedes utilizar la API de Geocodificación para obtener las coordenadas correspondientes.

Mostrar la ubicación actual: Puedes utilizar la API de ubicación de Android para obtener la ubicación actual del dispositivo. Puedes solicitar los permisos necesarios y utilizar la clase FusedLocationProviderClient para obtener la ubicación actual. Luego, puedes mostrar esa ubicación en el mapa.

Añadir marcadores al hacer clic en el mapa: Puedes utilizar el método setOnMapClickListener para detectar los clics en el mapa. Dentro del setOnMapClickListener, puedes crear un marcador con las coordenadas del lugar donde se hizo clic y agregarlo al mapa.

Guardar las coordenadas de los marcadores en una variable: Puedes utilizar un estado mutable (mutableStateOf) para almacenar las coordenadas de los marcadores agregados. Cada vez que se agrega un marcador, puedes actualizar el estado con las nuevas coordenadas.

Zoom in y zoom out: Ya has configurado el zoom inicial con googleMap.setMinZoomPreference(zoomLevel). Para permitir que el usuario haga zoom in y zoom out, no necesitas realizar ningún cambio adicional en el código. Los controles de zoom y los gestos de zoom ya están habilitados en el código actual.

Aquí hay un ejemplo actualizado del código que cumple con los requisitos mencionados:

kotlin
Copy code
import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.app.ActivityCompat
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.LifecycleObserver
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.MapView
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.google.maps.android.ktx.add




User
por favor continua el código desde donde te quedaste
ChatGPT
Claro, aquí tienes el código continuado:

kotlin
Copy code
@SuppressLint("RememberReturnType")
@Composable
fun MyMapView(onReady: (GoogleMap) -> Unit) {
    val context = LocalContext.current
    val mapView = remember { MapView(context) }
    val lifecycle = LocalLifecycleOwner.current.lifecycle
    val markers = remember { mutableStateListOf<LatLng>() }

    lifecycle.addObserver(RememberMapLifeCycle(map = mapView))

    AndroidView(factory = {
        mapView.apply {
            mapView.getMapAsync { googleMap ->
                val zoomLevel = 15f
                googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(LatLng(13.714581, -89.216985), zoomLevel))

                googleMap.setOnMapClickListener { latLng ->
                    markers.add(latLng)
                    googleMap.addMarker(MarkerOptions().position(latLng))
                }

                // Añadir marcadores existentes
                markers.forEach { latLng ->
                    googleMap.addMarker(MarkerOptions().position(latLng))
                }

                googleMap.uiSettings.isZoomControlsEnabled = true
                googleMap.uiSettings.isZoomGesturesEnabled = true
                googleMap.uiSettings.isMyLocationButtonEnabled = true
                googleMap.uiSettings.isMapToolbarEnabled = true

                onReady(googleMap)
            }
        }
    })
}

@Composable
fun RememberMapLifeCycle(map: MapView): LifecycleObserver {
    return remember {
        LifecycleEventObserver { _, event ->
            when (event) {
                Lifecycle.Event.ON_CREATE -> map.onCreate(Bundle())
                Lifecycle.Event.ON_START -> map.onStart()
                Lifecycle.Event.ON_RESUME -> map.onResume()
                Lifecycle.Event.ON_PAUSE -> map.onPause()
                Lifecycle.Event.ON_STOP -> map.onStop()
                Lifecycle.Event.ON_DESTROY -> map.onDestroy()
                Lifecycle.Event.ON_ANY -> throw IllegalStateException()
            }
        }
    }
}

@Composable
fun MapScreen() {
    val permissions = arrayOf(
        Manifest.permission.ACCESS_FINE_LOCATION,
        Manifest.permission.ACCESS_COARSE_LOCATION
    )
    val requestCode = 42 // Puedes cambiar el número de solicitud según tus necesidades

    val locationPermissionGranted = remember { mutableStateOf(false) }

    val activity = LocalContext.current as? ComponentActivity

    ActivityCompat.requestPermissions(activity!!, permissions, requestCode)

    MyMapView { googleMap ->
        if (ActivityCompat.checkSelfPermission(
                LocalContext.current,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) == PackageManager.PERMISSION_GRANTED
        ) {
            locationPermissionGranted.value = true
            googleMap.isMyLocationEnabled = true
        }
    }

    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        if (!locationPermissionGranted.value) {
            Text("Location permission denied")
        }
    }
}

@Preview(showSystemUi = true)
@Composable
fun MapPreview() {
    WorkWatchAppTheme {
        MapScreen()
    }
}
En este código, se ha agregado una función MapScreen que es el punto de entrada para la pantalla del mapa. Se solicitan los permisos de ubicación mediante requestPermissions y se actualiza el estado locationPermissionGranted en función del resultado de la solicitud.

Luego, se llama a MyMapView y se pasa una función onReady para obtener la referencia de GoogleMap. Dentro de MyMapView, se agregan marcadores cuando se hace clic en el mapa y se muestran los marcadores existentes. Además, se habilitan los controles de zoom, el botón de ubicación y la barra de herramientas del mapa.

En la función MapScreen, se muestra un mensaje si los permisos de ubicación no están concedidos
*/

