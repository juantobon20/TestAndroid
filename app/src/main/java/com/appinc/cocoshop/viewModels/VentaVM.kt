package com.appinc.cocoshop.viewModels

import android.app.Activity
import androidx.lifecycle.ViewModel
import com.appinc.cocoshop.models.Documento
import com.appinc.cocoshop.models.Item
import com.appinc.cocoshop.models.Product
import com.appinc.cocoshop.tools.MsgBox

class VentaVM : ViewModel() {

    private lateinit var msgBox: MsgBox

    private var documento = Documento()
    var items: MutableList<Item> = arrayListOf()
    private var vrTotal: Double = 0.0

    fun init(activity: Activity) {
        this.msgBox = MsgBox(activity)
    }

    fun load(product: Product) {
        val item = Item()
        item.productoId = product.id
        item.nombre = product.nombre
        item.vrUnit = product.precio

        vrTotal += item.vrUnit
    }


}