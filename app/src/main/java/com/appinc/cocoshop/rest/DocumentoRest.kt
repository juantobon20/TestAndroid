package com.appinc.cocoshop.rest

import com.appinc.cocoshop.models.Documento
import com.appinc.cocoshop.models.Login

class DocumentoRest {

    private val connection = Connection()

    fun Get(): List<Documento>? = connection.iDocumento.Get().execute().body()
    fun Post(documento: Documento): Documento? =
        connection.iDocumento.Post(documento).execute().body()
}