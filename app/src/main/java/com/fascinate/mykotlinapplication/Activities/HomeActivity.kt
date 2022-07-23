package com.fascinate.mykotlinapplication.Activities

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.Settings
import android.view.View
import com.fascinate.mykotlinapplication.R
import com.fascinate.mykotlinapplication.databinding.ActivityHomeBinding

class HomeActivity : AppCompatActivity() {
    private lateinit var binding: ActivityHomeBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
    }

    fun logout(view: View) {
        val deviceID = Settings.Secure.getString(contentResolver, Settings.Secure.ANDROID_ID)
        val pref: SharedPreferences = getSharedPreferences(deviceID, Context.MODE_PRIVATE)
        val editor = pref.edit()
        editor.clear()
        editor.apply()

        intent = Intent(this, RegisterActivity::class.java)
        startActivity(intent)
        finish()

    }
}