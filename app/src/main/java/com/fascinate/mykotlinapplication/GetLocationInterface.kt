package com.fascinate.mykotlinapplication

import android.location.Location

interface GetLocationInterface {
    fun getLatLng(location: Location, idToken: String, id: String)
}