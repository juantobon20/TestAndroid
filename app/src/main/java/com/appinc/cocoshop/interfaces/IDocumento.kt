package com.appinc.cocoshop.interfaces

import com.appinc.cocoshop.models.Documento
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface IDocumento {

    @GET("Documento")
    fun Get(): Call<List<Documento>>

    @POST("Documento")
    fun Post(@Body documento: Documento): Call<Documento>
}