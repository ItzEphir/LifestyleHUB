package com.ephirium.lifestylehub.feature.currentweather.location

import android.Manifest.permission
import android.annotation.SuppressLint
import android.content.Context
import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationManager
import android.os.Looper
import androidx.core.app.ActivityCompat
import androidx.core.content.getSystemService
import com.ephirium.lifestylehub.feature.currentweather.utils.hasLocationPermission
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationCallback
import com.google.android.gms.location.LocationRequest
import com.google.android.gms.location.LocationResult
import com.google.android.gms.location.LocationServices
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.launch

class DefaultLocationClient(
    private val context: Context,
    private val client: FusedLocationProviderClient,
) : LocationClient {
    @SuppressLint("MissingPermission")
    override fun getLocationUpdates(interval: Long): Flow<Location> = callbackFlow {
        if (!context.hasLocationPermission()) {
            throw LocationClient.LocationException("Missing Location Permission")
        }
        val locationManager = context.getSystemService<LocationManager>()
            ?: throw NullPointerException("Can`t get location manager")
        val isGpsEnabled = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)
        val isNetworkEnabled = locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER)
        if (!isGpsEnabled && !isNetworkEnabled) {
            throw LocationClient.LocationException("Location is disabled")
        }
        
        val request = LocationRequest.Builder(interval).build()
        val locationCallback = object : LocationCallback() {
            override fun onLocationResult(locationResult: LocationResult) {
                super.onLocationResult(locationResult)
                locationResult.locations.lastOrNull()?.let {location->
                    launch {
                        send(location)
                    }
                }
            }
        }
        client.requestLocationUpdates(request, locationCallback, Looper.getMainLooper())
        
        awaitClose{
            client.removeLocationUpdates(locationCallback)
        }
    }
    
}