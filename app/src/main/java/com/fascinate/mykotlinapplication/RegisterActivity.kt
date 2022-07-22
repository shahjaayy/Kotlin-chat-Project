package com.fascinate.mykotlinapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.fascinate.mykotlinapplication.databinding.ActivityRegisterBinding

class RegisterActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRegisterBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        binding.tvLoginAsGuest.setOnClickListener {
            guestLogin()
        }
        binding.tvLoginAsGmail.setOnClickListener{
            gmailLogin()
        }

    }

    private fun gmailLogin() {

    }

    private fun guestLogin() {

    }
}