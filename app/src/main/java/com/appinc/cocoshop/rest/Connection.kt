package com.appinc.cocoshop.rest

import android.os.StrictMode
import com.appinc.cocoshop.interfaces.IAbono
import com.appinc.cocoshop.interfaces.IDocumento
import com.appinc.cocoshop.interfaces.ILogin
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class Connection {

    fun <S> createService(serviceClass: Class<S>?): S {
        val policy = StrictMode.ThreadPolicy.Builder().permitAll().build()
        StrictMode.setThreadPolicy(policy)

        val client = OkHttpClient.Builder().readTimeout(60, TimeUnit.SECONDS)
            .connectTimeout(60, TimeUnit.SECONDS).build()

        val retrofit =
            Retrofit.Builder().baseUrl("http://35.224.231.248:97/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .client(client).build()
        return retrofit.create(serviceClass!!)
    }

    val iLogin = this.createService(ILogin::class.java)
    val iDocumento = this.createService(IDocumento::class.java)
    val iAbono = this.createService(IAbono::class.java)
}