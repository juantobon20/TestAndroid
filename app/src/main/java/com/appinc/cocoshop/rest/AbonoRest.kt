package com.appinc.cocoshop.rest

import com.appinc.cocoshop.models.Abono
import com.appinc.cocoshop.models.Documento

class AbonoRest {

    private val connection = Connection()

    fun Get(): List<Abono>? = connection.iAbono.Get().execute().body()
    fun Post(abono: Abono): Abono? =
        connection.iAbono.Post(abono).execute().body()
}