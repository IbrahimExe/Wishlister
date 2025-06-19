package com.example.gamewishlister_app

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import com.example.gamewishlister_app.databinding.YourgamesLayoutBinding
import com.example.gamewishlister_app.model.Game
import com.example.gamewishlister_app.ui.GameAdapter
import com.example.gamewishlister_app.ui.LoginActivity
import com.example.gamewishlister_app.ui.SearchGamesActivity
import com.example.gamewishlister_app.ui.GameDetailActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*

class YourGamesActivity : AppCompatActivity() {

    private lateinit var binding: YourgamesLayoutBinding
    private lateinit var adapter: GameAdapter
    private lateinit var userGamesRef: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // 1. View Binding
        binding = YourgamesLayoutBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // 2. RecyclerView + Adapter
        adapter = GameAdapter(mutableListOf()) { game ->
            // When a game card is tapped, open GameDetailActivity
            val intent = Intent(this, GameDetailActivity::class.java).apply {
                putExtra("GAME_ID", game.id)
                putExtra("GAME_NAME", game.name)
            }
            startActivity(intent)
        }

        binding.rvGames.layoutManager = GridLayoutManager(this, 2)
        binding.rvGames.adapter = adapter

        // 3. Firebase: get reference to the current user's game list
        //val uid = FirebaseAuth.getInstance().currentUser?.uid
            //?: throw IllegalStateException("User must be logged in")
        val uid = FirebaseAuth.getInstance().currentUser?.uid
            ?: "testUser"   // <-- fallback so currentUser==null won't crash

        userGamesRef = FirebaseDatabase
            .getInstance()
            .getReference("users")
            .child(uid)
            .child("games")

        // 4. Load & listen for changes in the user's games
        loadUserGames()

        // 5. Search Games button
        binding.btnSearchGames.setOnClickListener {
            startActivity(Intent(this, SearchGamesActivity::class.java))
        }

        // 6. Profile/Logout icon
        binding.imgProfile.setOnClickListener {
            FirebaseAuth.getInstance().signOut()
            startActivity(Intent(this, LoginActivity::class.java))
            finish()
        }
    }

    /**
     * Listen for changes under /users/{uid}/games and update the RecyclerView.
     */
    private fun loadUserGames() {
        userGamesRef.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                // Gather games from Firebase into a list
                val gamesList = mutableListOf<Game>()
                snapshot.children.forEach { child ->
                    child.getValue(Game::class.java)?.let { game ->
                        // ← this is where we filter
                        if (game.id.isNotBlank() && game.name.isNotBlank()) {
                            gamesList.add(game)
                        }
                    }
                }
                // Update adapter with the fetched list
                adapter.setItems(gamesList)

                binding.tvEmptyMessage.visibility =
                    if (gamesList.isEmpty()) View.VISIBLE else View.GONE
            }

            override fun onCancelled(error: DatabaseError) {
                // TODO: handle error (e.g., show a Toast)
                Toast.makeText(this@YourGamesActivity, "Error loading games", Toast.LENGTH_SHORT).show()
            }
        })
    }
}
