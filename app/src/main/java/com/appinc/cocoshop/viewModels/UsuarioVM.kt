package com.appinc.cocoshop.viewModels

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.appinc.cocoshop.App
import com.appinc.cocoshop.models.Login
import com.appinc.cocoshop.rest.LoginRest
import com.appinc.cocoshop.tools.Md5
import com.appinc.cocoshop.tools.MsgBox
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class UsuarioVM : ViewModel() {

    private lateinit var msgBox: MsgBox
    private var login: Login? = null
    private var loginRest = LoginRest()

    var isModify: Boolean = false
    var nameButton: String = "Guardar"
    var nombre: String = ""
    var apellido: String = ""
    var direccion: String = ""
    var telefono: String = ""
    var usuario: String = ""
    var password: String = ""
    var isEmployee: Boolean = true
    var isActivo: Boolean = false
    private var isFinish = MutableLiveData<Boolean>()

    fun init(context: Context, rpta: Boolean) {
        this.msgBox = MsgBox(context)
        this.isModify = rpta

        if (!isModify) {
            this.login = Login()
            return
        }

        if (App.loginModify == null) return

        this.login = App.loginModify
        App.loginModify = null

        this.nombre = this.login?.usuario?.nombre!!
        this.apellido = this.login?.usuario?.apellido!!
        this.direccion = this.login?.usuario?.direccion!!
        this.telefono = this.login?.usuario?.telefono!!
        this.usuario = this.login?.userName!!
        this.password = this.login?.password!!

        this.isEmployee = this.login?.usuario?.tipo == 1
        this.isActivo = this.login?.estado!!
        this.nameButton = "Modificar"
    }

    fun onCheckTipo(boolean: Boolean, tipo: Int) {
        if (tipo == 1) this.isEmployee = boolean
        else this.isActivo = boolean
    }

    fun onUpsertClicked() {
        if (this.nombre.isEmpty()) {
            this.msgBox.Msg("Debe escribir el nombre"); return
        }
        if (this.apellido.isEmpty()) {
            this.msgBox.Msg("Debe escribir el apellido"); return
        }
        if (this.direccion.isEmpty()) {
            this.msgBox.Msg("Debe escribir la dirección"); return
        }
        if (this.telefono.isEmpty()) {
            this.msgBox.Msg("Debe escribir el teléfono"); return
        }
        if (this.usuario.isEmpty()) {
            this.msgBox.Msg("Debe escribir el usuario"); return
        }
        if (this.password.isEmpty()) {
            this.msgBox.Msg("Debe escribir la contraseña"); return
        }

        this.login?.estado = this.isActivo
        this.login?.usuario?.nombre = this.nombre
        this.login?.usuario?.apellido = this.apellido
        this.login?.usuario?.direccion = this.direccion
        this.login?.usuario?.telefono = this.telefono
        this.login?.usuario?.tipo = if (this.isEmployee) 1 else 2

        if (!this.isModify) {
            this.login?.userName = this.usuario
            this.login?.password = this.password.Md5()
        }

        if (this.isModify) {
            this.Update(); return
        }
        this.Insert()
    }

    fun Insert() {
        viewModelScope.launch {
            val rpta = withContext(Dispatchers.IO) {
                loginRest.Post(login!!)
            }
            if (rpta == null || !rpta.msgError.isNullOrEmpty()) {
                msgBox.Msg(if (rpta != null) rpta.msgError else "Error no encontrado")
                return@launch
            }

            isFinish.value = true
            GetIsFinish()
        }
    }

    fun Update() {
        viewModelScope.launch {
            val rpta = withContext(Dispatchers.IO) {
                loginRest.Put(login?.id!!, login!!)
            }
            if (rpta == null || !rpta.msgError.isNullOrEmpty()) {
                msgBox.Msg(if (rpta != null) rpta.msgError else "Error no encontrado")
                return@launch
            }

            isFinish.value = true
            GetIsFinish()
        }
    }

    fun GetIsFinish(): LiveData<Boolean> = this.isFinish
}