package com.example.gamewishlister_app.ui

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.gamewishlister_app.R
import com.example.gamewishlister_app.databinding.GamedetailLayoutBinding
import com.example.gamewishlister_app.model.Game
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*

class GameDetailActivity : AppCompatActivity()
{
    private lateinit var binding: GamedetailLayoutBinding
    private lateinit var gameRef: DatabaseReference
    private lateinit var currentGame: Game
    private lateinit var gameListener: ValueEventListener
    private var gameId: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = GamedetailLayoutBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnDetailBack.setOnClickListener { finish() }

        // 1) Read Intent extras
        gameId = intent.getStringExtra("GAME_ID") ?: run {
            finish(); return
        }
        val gameName = intent.getStringExtra("GAME_NAME") ?: "Game"

        // 2) Set title
        binding.tvDetailGameName.text = gameName

        // 3) Setup Firebase ref to this user’s game
        val uid = FirebaseAuth.getInstance().currentUser?.uid ?: "testUser"
        gameRef = FirebaseDatabase
            .getInstance()
            .getReference("users")
            .child(uid)
            .child("games")
            .child(gameId)


        // 5) Toggle played on icon tap
        binding.imgPlayedToggle.setOnClickListener {
            togglePlayed()
        }

        // 6) Save review when focus leaves the EditText
        binding.etReview.setOnFocusChangeListener { _, hasFocus ->
            if (!hasFocus) saveReview()
        }

        // 7) Remove button
        binding.btnRemove.setOnClickListener {
            removeGame()
         // removeGameFromCollection(gameId)
        }
    }

    // 4) Load the Game object
    override fun onStart() {
        super.onStart()
        // Attach a real‑time listener
        gameListener = object: ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                snapshot.getValue(Game::class.java)?.let { game ->
                    currentGame = game
                    bindGameData()
                }
            }
            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(this@GameDetailActivity, "Error loading game", Toast.LENGTH_SHORT).show()
            }
        }
        gameRef.addValueEventListener(gameListener)
    }

    override fun onStop() {
        super.onStop()
        // Remove the listener to avoid leaks
        gameRef.removeEventListener(gameListener)
    }

    override fun onPause() {
        super.onPause()
        saveReview()   // ensures review is persisted whenever you leave the screen
    }

    private fun bindGameData() {
        // a) Icon tint: green if played, white otherwise
        val tintColor = if (currentGame.played)
            getColor(R.color.steam_accent)
        else
            getColor(R.color.white)
        binding.imgPlayedToggle.setColorFilter(tintColor)

        // b) Current review
        binding.etReview.setText(currentGame.review)
    }

    private fun togglePlayed() {
        val newPlayed = !(currentGame.played)
        gameRef.child("played").setValue(newPlayed)
            .addOnSuccessListener {
                currentGame.played = newPlayed
                bindGameData()
            }
            .addOnFailureListener {
                Toast.makeText(this, "Could not update status", Toast.LENGTH_SHORT).show()
            }
    }

    private fun saveReview() {
        val newReview = binding.etReview.text.toString().trim().takeIf { it.isNotEmpty() }
        gameRef.child("review").setValue(newReview)
            .addOnSuccessListener {
                currentGame.review = newReview
            }
            .addOnFailureListener {
                Toast.makeText(this, "Could not save review", Toast.LENGTH_SHORT).show()
            }
    }

    private fun removeGame() {
        gameRef.removeValue()
            .addOnSuccessListener {
                Toast.makeText(this, "Removed from collection", Toast.LENGTH_SHORT).show()
                finish()  // go back to YourGamesActivity
            }
            .addOnFailureListener {
                Toast.makeText(this, "Could not remove game", Toast.LENGTH_SHORT).show()
            }
    }

    private fun removeGameFromCollection(gameId: String) {
        val userId = FirebaseAuth.getInstance().currentUser?.uid ?: return
        val gameRef = FirebaseDatabase.getInstance().getReference("users/$userId/games/$gameId")

        // Remove the game entry, including review and played status
        gameRef.removeValue().addOnSuccessListener {
            Toast.makeText(this, "Game removed from your collection.", Toast.LENGTH_SHORT).show()
            finish() // Close the detail screen and return
        }.addOnFailureListener {
            Toast.makeText(this, "Failed to remove game.", Toast.LENGTH_SHORT).show()
        }
    }
}
