package com.unal.multiplayertictactoe.BackCommunicationImp

import android.widget.Toast
import com.unal.multiplayertictactoe.Entities.GamesDataCollectionItem
import com.unal.multiplayertictactoe.Interfaces.GamesService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class BackCommunication {


    fun sendChange(position:String){

    }

    fun getPossition():String{

        val gamesService :  GamesService = RestEngine.getRestEngine().create(GamesService::class.java)
        val result: Call<List<GamesDataCollectionItem>> = gamesService.listGames()

        result.enqueue(object : Callback<List<GamesDataCollectionItem>>{
            override fun onFailure(call: Call<List<GamesDataCollectionItem>>, t: Throwable) {
                System.out.println("Error")
            }

            override fun onResponse(
                call: Call<List<GamesDataCollectionItem>>,
                response: Response<List<GamesDataCollectionItem>>
            ) {
                System.out.println("Peticion OK")
            }
        })


        //---------------
        var x = "1,1"
        return (x)
    }

}