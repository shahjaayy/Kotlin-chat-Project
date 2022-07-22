package com.fascinate.mykotlinapplication

object SingletonClass {
    private var userToken: String? = null

    fun setUser(token: String?){
        userToken = token
    }

    fun getUser(): String{
        return userToken!!
    }
}