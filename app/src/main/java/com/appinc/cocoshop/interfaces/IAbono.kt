package com.appinc.cocoshop.interfaces

import com.appinc.cocoshop.models.Abono
import com.appinc.cocoshop.models.Documento
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface IAbono {

    @GET("Abono")
    fun Get(): Call<List<Abono>>

    @POST("Abono")
    fun Post(@Body abono: Abono): Call<Abono>
}