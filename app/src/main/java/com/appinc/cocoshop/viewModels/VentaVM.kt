package com.appinc.cocoshop.viewModels

import android.app.Activity
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.appinc.cocoshop.models.Documento
import com.appinc.cocoshop.models.Item
import com.appinc.cocoshop.models.Login
import com.appinc.cocoshop.models.Product
import com.appinc.cocoshop.rest.DocumentoRest
import com.appinc.cocoshop.tools.MsgBox
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.util.*

class VentaVM : ViewModel() {

    private lateinit var msgBox: MsgBox

    private var documentoRest = DocumentoRest()
    private var documento = Documento()
    var items: MutableList<Item> = arrayListOf()
    private var vrTotal: Double = 0.0

    private val isBusy = MutableLiveData<Boolean>()
    private val listData = MutableLiveData<List<Documento>>()

    var documentos: List<Documento>? = arrayListOf()

    fun init(activity: Activity) {
        this.msgBox = MsgBox(activity)

        this.isBusy.value = true
        this.GetAll()
    }

    fun load(product: Product) {
        val item = Item()
        item.productoId = product.id
        item.nombre = product.nombre
        item.vrUnit = product.precio
        items.add(item)
        vrTotal += item.vrUnit
    }

    fun GetAll() {
        viewModelScope.launch {
            documentos = withContext(Dispatchers.IO) {
                documentoRest.Get()
            }
            isBusy.value = false
            listData.value = documentos
        }
    }

    fun GetLoginsLiveData(): LiveData<List<Documento>> = this.listData
    fun GetIsBusy(): LiveData<Boolean> = this.isBusy

    fun onSaveClicked() {
        if (items.size == 0) {
            msgBox.Msg("Debe seleccionar al menos un item")
            return
        }

        documento.usuarioId = 1
        documento.fecha = Date()
        documento.items = items
        documento.vrTotal = vrTotal

        viewModelScope.launch {
            val rpta = withContext(Dispatchers.IO) {
                documentoRest.Post(documento)
            }
            if (rpta == null) {
                msgBox.Msg("Error al guardar")
                return@launch
            }

            msgBox.Msg("Guardado correctamente", "", true)
        }
    }
}