package com.example.gamewishlister_app.ui

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import com.example.gamewishlister_app.databinding.SearchgamesLayoutBinding
import com.example.gamewishlister_app.model.Game
import com.example.gamewishlister_app.ui.GameAdapter
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*

class SearchGamesActivity : AppCompatActivity()
{
    private lateinit var binding: SearchgamesLayoutBinding
    private lateinit var dbRef: DatabaseReference
    private lateinit var adapter: GameAdapter
    private val allGames = mutableListOf<Game>()
    private val displayGames = mutableListOf<Game>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = SearchgamesLayoutBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // 1) Setup RecyclerView
        adapter = GameAdapter(displayGames) { game ->
            addToYourGames(game)
        }
        binding.rvSearchGames.layoutManager = GridLayoutManager(this, 2)
        binding.rvSearchGames.adapter = adapter

        // 2) Back button
        binding.btnBack.setOnClickListener { finish() }

        // 3) Initialize Firebase ref
        dbRef = FirebaseDatabase.getInstance().getReference("games")
        loadAllGames()

        // 4) Live search
        binding.etSearch.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                filterGames(s.toString())
            }
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) { }
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) { }
        })
    }

    private fun loadAllGames() {
        dbRef.addListenerForSingleValueEvent(object: ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                allGames.clear()
                snapshot.children.forEach { child ->
                    child.getValue(Game::class.java)?.let { allGames.add(it) }
                }
                displayGames.clear()
                displayGames.addAll(allGames)
                adapter.notifyDataSetChanged()
            }
            override fun onCancelled(error: DatabaseError) {
                // TODO: show error
            }
        })
    }

    private fun filterGames(query: String) {
        val lower = query.lowercase()
        val filtered = allGames.filter {
            it.name.lowercase().contains(lower)
        }
        displayGames.apply {
            clear()
            addAll(filtered)
        }
        adapter.notifyDataSetChanged()
    }

    private fun addToYourGames(game: Game) {
        val uid = FirebaseAuth.getInstance().currentUser?.uid ?: "testUser"
        val userGamesRef = FirebaseDatabase
            .getInstance()
            .getReference("users")
            .child(uid)
            .child("games")

        userGamesRef
            .child(game.id)
            .setValue(game)
            .addOnSuccessListener {
                // Optionally show a toast “Added!”
                finish()  // return to YourGamesActivity; it should reload from Firebase later
            }
            .addOnFailureListener {
                // TODO: show error
            }
    }
}
