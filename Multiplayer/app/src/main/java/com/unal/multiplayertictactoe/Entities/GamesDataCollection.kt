package com.unal.multiplayertictactoe.Entities

class GamesDataCollection : ArrayList<GamesDataCollectionItem>()

data class GamesDataCollectionItem(
    val token: String,
    val Game: Game,
)

data class Game(
    val token: String,
    val players: Players,
    val gameHistory: String,
    val status: String
)

data class Players(
    val creator: String,
    val joined: String,
)

