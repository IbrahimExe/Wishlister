package com.example.gamewishlister_app.model

data class Game(
    val id: String ="",
    val name: String ="",
    var played: Boolean = false,
    var review: String? = null,
)