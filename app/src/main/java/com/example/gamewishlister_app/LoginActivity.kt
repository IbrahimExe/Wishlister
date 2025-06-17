package com.example.gamewishlister_app.ui

import com.example.gamewishlister_app.R
import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.example.gamewishlister_app.databinding.ActivityLoginBinding
import com.google.firebase.auth.FirebaseAuth
import com.example.gamewishlister_app.YourGamesActivity

class LoginActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth
    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Initialize View Binding
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Initialize Firebase Auth
        auth = FirebaseAuth.getInstance()

        binding.btnSteamLogin.setOnClickListener { goToMain() }

        binding.btnEmailLogin.setOnClickListener { goToMain() }

        binding.btnNativeLogin.setOnClickListener { goToMain() }
        
    }

    private fun goToMain() {
        // after “login” bring the user to YourGamesActivity
        startActivity(Intent(this, YourGamesActivity::class.java))
        finish()
    }
}