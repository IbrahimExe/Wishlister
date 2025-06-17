package com.example.gamewishlister_app.ui

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.gamewishlister_app.YourGamesActivity
import com.example.gamewishlister_app.databinding.LoginLayoutBinding
import com.google.firebase.auth.FirebaseAuth

class LoginActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth
    private lateinit var binding: LoginLayoutBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Initialize View Binding
        binding = LoginLayoutBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Initialize Firebase Auth
        auth = FirebaseAuth.getInstance()
        // Sign in anonymously immediately:
        auth.signInAnonymously()
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    // now we have a non-null auth.currentUser.uid
                    Toast.makeText(this, "Signed in Annonymously.", Toast.LENGTH_SHORT).show()
                } else {
                    //Toast.makeText(this, "Authentication failed.", Toast.LENGTH_SHORT).show()
                }
            }

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