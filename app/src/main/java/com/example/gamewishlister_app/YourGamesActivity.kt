package com.example.gamewishlister_app

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import com.example.gamewishlister_app.databinding.YourgamesLayoutBinding
import com.example.gamewishlister_app.model.Game
import com.example.gamewishlister_app.ui.GameAdapter
import com.example.gamewishlister_app.ui.LoginActivity
import com.example.gamewishlister_app.ui.SearchGamesActivity
import com.google.firebase.auth.FirebaseAuth

class YourGamesActivity : AppCompatActivity()
{
    private lateinit var binding: YourgamesLayoutBinding
    private lateinit var adapter: GameAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = YourgamesLayoutBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // 1) Prepare placeholder data
        val placeholderGames = mutableListOf(
            Game("1", "Half‑Life"),
            Game("2", "Portal"),
            Game("3", "The Witcher 3"),
            Game("4", "Stardew Valley")
        )

        // 2) Setup RecyclerView
        adapter = GameAdapter(placeholderGames) { game ->
            // TODO: launch GameDetailActivity, passing the Game
        }
        binding.rvGames.layoutManager = GridLayoutManager(this, 2)
        binding.rvGames.adapter = adapter

        // 3) Button listeners
        binding.btnSearchGames.setOnClickListener {
            startActivity(Intent(this, SearchGamesActivity::class.java))
        }
        binding.imgProfile.setOnClickListener {
            // sign out
            FirebaseAuth.getInstance().signOut()
            startActivity(Intent(this, LoginActivity::class.java))
            finish()
        }
    }
}