package com.appinc.cocoshop.interfaces

import com.appinc.cocoshop.models.Login
import retrofit2.Call
import retrofit2.http.*

interface ILogin {

    @GET("Login")
    fun Get(): Call<List<Login>>

    @GET("Login/{id}")
    fun Get(@Path("id") id: Int): Call<Login>

    @POST("Login")
    fun Post(@Body Login: Login): Call<Login>

    @PUT("Login/{id}")
    fun Put(@Path("id") id: Int, @Body Login: Login): Call<Login>

    @DELETE("Login/{id}")
    fun Delete(@Path("id") id: Int): Call<Boolean>
}