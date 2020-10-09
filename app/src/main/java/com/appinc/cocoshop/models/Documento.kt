package com.appinc.cocoshop.models

import com.appinc.cocoshop.tools.DateJson
import com.google.gson.annotations.JsonAdapter
import java.util.*

class Documento {

    var id: Int = 0
    var usuarioId: Int = 0
    @JsonAdapter(DateJson::class)
    var fecha: Date = Date()
    var vrTotal: Double = 0.0

    var items: MutableList<Item> = arrayListOf()
}