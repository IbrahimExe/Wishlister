package com.example.gamewishlister_app.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.gamewishlister_app.R
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

            // Mapping game.id or game.name to its drawable resource:
            val imageRes = when (game.id) {
                "1" -> R.drawable.cyberpunk2077
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
                "20" -> R.drawable.unless
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
            // Load it into the background ImageView:
            binding.imgGameThumbnail.setImageResource(imageRes)

            val playedTint = if (game.played) {
                binding.root.context.getColor(R.color.steam_accent)
            } else {
                binding.root.context.getColor(R.color.white)
            }
            binding.imgPlayedSmall.setColorFilter(playedTint)

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
