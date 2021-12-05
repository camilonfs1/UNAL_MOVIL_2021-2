package com.unal.multiplayertictactoe.Interfaces


import com.unal.multiplayertictactoe.Entities.GamesDataCollectionItem
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.POST


interface GamesService {

    @GET("/")
    fun listGames(): Call<List<GamesDataCollectionItem>>

    @POST("/join_game")
    fun JoinGame(): Call<List<GamesDataCollectionItem>>

    @POST("/create_game")
    fun CreateGame(): Call<List<GamesDataCollectionItem>>

    @POST("/Game")
    fun postGame(): Call<List<GamesDataCollectionItem>>

    @GET("/Game")
    fun getGame(): Call<List<GamesDataCollectionItem>>


}

//https://www.youtube.com/watch?v=7BRAB6CMlug&ab_channel=applikdos