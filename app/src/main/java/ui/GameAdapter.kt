package com.example.gamewishlister_app.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.gamewishlister_app.databinding.ItemGameBinding
import com.example.gamewishlister_app.model.Game

class GameAdapter(
    private val games: MutableList<Game>,
    private val onClick: (Game) -> Unit
) : RecyclerView.Adapter<GameAdapter.GameViewHolder>() {

    inner class GameViewHolder(val binding: ItemGameBinding)
        : RecyclerView.ViewHolder(binding.root) {
        fun bind(game: Game) {
            binding.tvGameName.text = game.name
            binding.root.setOnClickListener { onClick(game) }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GameViewHolder {
        val binding = ItemGameBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return GameViewHolder(binding)
    }

    override fun onBindViewHolder(holder: GameViewHolder, position: Int) {
        holder.bind(games[position])
    }

    override fun getItemCount() = games.size

    fun setItems(newList: List<Game>) {
        games.clear()
        games.addAll(newList)
        notifyDataSetChanged()
    }

    fun addItem(game: Game) {
        games.add(0, game)
        notifyItemInserted(0)
    }
}
