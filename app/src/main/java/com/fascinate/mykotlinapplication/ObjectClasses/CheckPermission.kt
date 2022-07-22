package com.fascinate.mykotlinapplication.ObjectClasses

import android.Manifest
import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.content.pm.PackageManager
import android.location.LocationManager
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat

object CheckPermission {

    @SuppressLint("StaticFieldLeak")
    fun checkPermission(myContext: Context?){
        val locationManager: LocationManager = myContext!!.getSystemService(AppCompatActivity.LOCATION_SERVICE) as LocationManager
        if (!checkLocationPermission(myContext))
            requestLocationPermission(myContext)
        else
            if (locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER))
                //Permission granted and GPS ON
                Toast.makeText(myContext, "Permission Granted...", Toast.LENGTH_SHORT).show()
            else
                //Permission granted and GPS Off
                NoGpsAlert.showSettingsAlert(myContext)
    }

    private fun checkLocationPermission(myContext: Context): Boolean {
        return ContextCompat.checkSelfPermission(myContext, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED
    }

    private fun requestLocationPermission(myContext: Context) {
        ActivityCompat.requestPermissions(myContext as Activity, arrayOf(Manifest.permission.ACCESS_FINE_LOCATION), 123)
    }

    fun handlePermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray, myContext: Context?){
        if (requestCode == 123)
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                val locationManager: LocationManager = myContext!!.getSystemService(AppCompatActivity.LOCATION_SERVICE) as LocationManager
                if (locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER))
                    Toast.makeText(myContext, "Permission Granted...", Toast.LENGTH_SHORT).show()
                else
                    NoGpsAlert.showSettingsAlert(myContext)
            }
            else
                Toast.makeText(myContext, "Permission Denied...", Toast.LENGTH_SHORT).show()

    }
}