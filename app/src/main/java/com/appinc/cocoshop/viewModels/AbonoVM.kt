package com.appinc.cocoshop.viewModels

import android.app.Activity
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.appinc.cocoshop.models.Abono
import com.appinc.cocoshop.models.Documento
import com.appinc.cocoshop.rest.AbonoRest
import com.appinc.cocoshop.tools.MsgBox
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.util.*

class AbonoVM : ViewModel() {
    private lateinit var msgBox: MsgBox
    private var abonoRest = AbonoRest()

    var abonos: List<Abono>? = arrayListOf()
    private val isBusy = MutableLiveData<Boolean>()
    private val listData = MutableLiveData<List<Abono>>()

    fun init(activity: Activity) {
        this.msgBox = MsgBox(activity)
    }

    fun GetAll() {
        viewModelScope.launch {
            abonos = withContext(Dispatchers.IO) {
                abonoRest.Get()
            }
            isBusy.value = false
            listData.value = abonos
        }
    }

    fun GetLoginsLiveData(): LiveData<List<Abono>> = this.listData
    fun GetIsBusy(): LiveData<Boolean> = this.isBusy

    fun onSaveClicked(double: Double) {
        if (double == 0.0) {
            msgBox.Msg("Debe colocar el valor a abonar")
            return
        }

        val abono = Abono()
        abono.usuarioId = 1
        abono.vrTotal = double

        viewModelScope.launch {
            val rpta = withContext(Dispatchers.IO) {
                abonoRest.Post(abono)
            }
            if (rpta == null) {
                msgBox.Msg("Error al guardar")
                return@launch
            }

            msgBox.Msg("Guardado correctamente", "")
        }
    }
}