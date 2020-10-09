package com.appinc.cocoshop.models

import java.util.*

class Documento {

    var id: Int = 0
    var usuarioId: Int = 0
    var fecha: Date = Date()
    var vrTotal: Double = 0.0

    var items: MutableList<Item> = arrayListOf()
}