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
        // Set game name
        binding.tvDetailGameName.text = currentGame.name

        // Set review text (if available)
        binding.etReview.setText(currentGame.review ?: "")

        // Tint toggle icon
        val tintColor = if (currentGame.played)
            getColor(R.color.steam_accent)
        else
            getColor(R.color.white)
        binding.imgPlayedToggle.setColorFilter(tintColor)

        // Set the background image just like in GameAdapter.kt:
        val imageRes = when (currentGame.id) {
            "1" -> R.drawable.unless
            "2" -> R.drawable.eldenring
            "3" -> R.drawable.hades
            "4" -> R.drawable.minecraft
            "5" -> R.drawable.stardewvalley
            "6" -> R.drawable.godofwar
            "7" -> R.drawable.hollowknight
            "8" -> R.drawable.celeste
            "9" -> R.drawable.doometernal
            "10" -> R.drawable.thewitcher3
            "11" -> R.drawable.helldivers2
            "12" -> R.drawable.repo
            "13" -> R.drawable.subnautica
            "14" -> R.drawable.marvelrivals
            "15" -> R.drawable.expedition33
            "16" -> R.drawable.deltarune
            "17" -> R.drawable.destiny2
            "18" -> R.drawable.overwatch2
            "19" -> R.drawable.blueprince
            "20" -> R.drawable.cyberpunk2077
            "21" -> R.drawable.balatro
            "22" -> R.drawable.outlast
            "23" -> R.drawable.buldursgate3
            "24" -> R.drawable.valorant
            "25" -> R.drawable.rainbowsixsiege
            "26" -> R.drawable.residentevilvillage
            "27" -> R.drawable.sekiro
            "29" -> R.drawable.reddeadredemption2
            "37" -> R.drawable.spiderman
            "38" -> R.drawable.oriandthewillofthewisps
            "40" -> R.drawable.marioodyssey
            "42" -> R.drawable.cuphead
            "54" -> R.drawable.papersplease
            "57" -> R.drawable.thebindingofisaac
            "64" -> R.drawable.riskofrain2
            "97" -> R.drawable.factorio
            "98" -> R.drawable.satisfactory

            else -> R.drawable.placeholderart
        }
        // Set it as the background image
        binding.imgCoverFull.setImageResource(imageRes)
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
