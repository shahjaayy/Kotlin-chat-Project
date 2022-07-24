package com.fascinate.mykotlinapplication.ObjectClasses

import android.content.Context
import android.content.SharedPreferences
import android.provider.Settings
import android.util.Log
import com.fascinate.mykotlinapplication.IfAnyOtherLoggedInInterface
import com.google.firebase.database.*
import com.google.firebase.database.ktx.getValue

object IfAnyOneLoggedIn {

    fun checkNow(context: Context, ifAnyOtherLoggedInInterface: IfAnyOtherLoggedInInterface)
    {
        val deviceID = Settings.Secure.getString(context.contentResolver, Settings.Secure.ANDROID_ID)
        val pref: SharedPreferences = context.getSharedPreferences(deviceID, Context.MODE_PRIVATE)

        val myRef: DatabaseReference = FirebaseDatabase.getInstance().getReference("Users")

        myRef.addValueEventListener(object: ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                if(snapshot.exists())
                {
                    val email: String? = pref.getString("id", null)
                    val newEmail = email?.replace(".", "").toString()

                    if(snapshot.child(newEmail).exists())
                    {
                        if(snapshot.child(newEmail).child("loggedIn").exists())
                        {
                            val getID = snapshot.child(newEmail).child("loggedIn").child("deviceId").getValue<String>()
                            val getStatus: Boolean? = snapshot.child(newEmail).child("loggedIn").child("deviceId").child("status").getValue<Boolean>()

                            if(deviceID != getID && getStatus == true)
                            {
                                ifAnyOtherLoggedInInterface.ifAny(true)
                                return
                            }
                        }
                    }
                }
                ifAnyOtherLoggedInInterface.ifAny(false)

            }

            override fun onCancelled(error: DatabaseError) {
                Log.e("IfAnyOneLoggedInClass", error.message)
            }

        })
    }
}