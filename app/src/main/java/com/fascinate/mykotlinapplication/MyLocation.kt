package com.fascinate.mykotlinapplication
import android.annotation.SuppressLint
import android.content.Context
import android.location.Location
import android.os.Looper
import com.fascinate.mykotlinapplication.ObjectClasses.SingletonClass
import com.google.android.gms.location.LocationCallback
import com.google.android.gms.location.LocationRequest
import com.google.android.gms.location.LocationResult
import com.google.android.gms.location.LocationServices
import com.google.android.gms.tasks.OnCompleteListener

class MyLocation {
    @SuppressLint("MissingPermission")

    companion object{
        fun getLastLatLng(
            context: Context,
            getLocationInterface: GetLocationInterface,
            idToken: String,
            id: String
        ){
            val fusedLocationClient = LocationServices.getFusedLocationProviderClient(context)
            fusedLocationClient.lastLocation
                .addOnCompleteListener { task ->
                    val location = task.result
                    if (location != null) {
                        getLocationInterface.getLatLng(location, idToken, id)

                        SingletonClass.manageSession(idToken, location, id, context)

                    } else {
                        val locationRequest = LocationRequest()
                            .setInterval(1000)
                            .setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY)
                            .setFastestInterval(1000)
                            .setNumUpdates(1)
                        val locationCallback: LocationCallback = object : LocationCallback() {
                            override fun onLocationResult(locationResult: LocationResult) {
                                val location1 = locationResult.lastLocation
                                getLocationInterface.getLatLng(location1, idToken, id)

                                SingletonClass.manageSession(idToken, location, id, context)
                            }
                        }
                        Looper.myLooper()?.let {
                            fusedLocationClient.requestLocationUpdates(
                                locationRequest,
                                locationCallback,
                                it
                            )
                        }
                    }
                }
        }
    }
}