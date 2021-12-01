package com.unal.multiplayertictactoe.Interfaces


import com.unal.multiplayertictactoe.Entities.GamesDataCollectionItem
import retrofit2.Call
import retrofit2.http.GET


interface GamesService {

    @GET("games")
    fun listGames(): Call<List<GamesDataCollectionItem>>
}

//https://www.youtube.com/watch?v=7BRAB6CMlug&ab_channel=applikdos