package com.fascinate.mykotlinapplication.ObjectClasses

import android.annotation.SuppressLint
import android.content.Context
import android.content.SharedPreferences
import android.location.Location
import android.provider.Settings.Secure


object SingletonClass {

    fun checkSession(context: Context): Boolean? {
        val deviceID = Secure.getString(context.contentResolver, Secure.ANDROID_ID)
        val pref: SharedPreferences = context.getSharedPreferences(deviceID, Context.MODE_PRIVATE)

        return pref.getBoolean("isLoggedIn", false)
    }

    @SuppressLint("HardwareIds")
    fun manageSession(token: String?, location: Location?, id: String, context: Context){
        val deviceID = Secure.getString(context.contentResolver, Secure.ANDROID_ID)
        val pref: SharedPreferences = context.getSharedPreferences(deviceID, Context.MODE_PRIVATE)
        val editor = pref.edit()
        editor.putString("userToken", token)
        editor.putString("latitude", location!!.latitude.toString())
        editor.putString("longitude", location.longitude.toString())
        editor.putString("id", id)
        editor.putBoolean("isLoggedIn", true)
        editor.apply()
    }



}