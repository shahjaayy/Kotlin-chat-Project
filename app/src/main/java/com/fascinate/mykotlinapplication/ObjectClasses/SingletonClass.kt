package com.fascinate.mykotlinapplication.ObjectClasses

import android.location.Location

object SingletonClass {
    private var userToken: String? = null
    private var location: Location? = null
    private var id: String? = null

    fun setUser(token: String?){
        userToken = token
    }

    fun setID(id: String?){
        this.id = id
    }

    fun setLocation(location: Location?){
        location?.latitude = location!!.latitude
        location.longitude = location.longitude
    }

    fun getLocation(): Location{
        return location!!
    }

    fun getUser(): String{
        return userToken!!
    }

    fun getID(): String{
        return id!!
    }
}