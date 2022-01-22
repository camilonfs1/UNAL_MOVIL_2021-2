package com.unal.multiplayertictactoe.BackCommunicationImp.domain.Interfaces

import com.unal.webservices.BackEndCommunication.Entities.ItemsDataCollectionItems
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query


interface InterfaceService {

    @GET("resource/rfqz-psdm.json")
    suspend fun listItems(): Response<List<ItemsDataCollectionItems>>


    @GET("resource/rfqz-psdm.json")
    suspend fun listItemsMunicipio(@Query(value = "municipio") id: String): Response<List<ItemsDataCollectionItems>>

    @GET("resource/rfqz-psdm.json")
    suspend fun listItemsAplicaciones(@Query(value = "ha_usado_aplicaciones") id: String): Response<List<ItemsDataCollectionItems>>

    @GET("resource/rfqz-psdm.json")
    suspend fun listItemsEmpresa(@Query(value = "tipo_de_empresa") id: String): Response<List<ItemsDataCollectionItems>>

}