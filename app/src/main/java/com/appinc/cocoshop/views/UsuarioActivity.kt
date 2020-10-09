package com.appinc.cocoshop.views

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toolbar
import com.appinc.cocoshop.App
import com.appinc.cocoshop.R
import com.appinc.cocoshop.models.Login
import com.appinc.cocoshop.tools.HideKeyBoard
import kotlinx.android.synthetic.main.activity_usuario.*

class UsuarioActivity : AppCompatActivity() {

    private lateinit var login: Login

    private var isModify: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_usuario)

        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setActionBar(toolbar)

        this.isModify =
            if (intent != null && intent.extras != null) intent.extras?.getBoolean("IsModify")!! else false
        title = getString(if (this.isModify) R.string.modificar_usuario else R.string.crear_usuario)

        layout.setOnTouchListener { v, _ ->
            if (txtNombre.isFocused) {
                txtNombre.clearFocus()
                txtNombre.HideKeyBoard()
            }
            if (txtApellido.isFocused) {
                txtApellido.clearFocus()
                txtApellido.HideKeyBoard()
            }
            if (txtDireccion.isFocused) {
                txtDireccion.clearFocus()
                txtDireccion.HideKeyBoard()
            }
            if (txtTelefono.isFocused) {
                txtTelefono.clearFocus()
                txtTelefono.HideKeyBoard()
            }
            if (txtUser.isFocused) {
                txtUser.clearFocus()
                txtUser.HideKeyBoard()
            }
            if (txtPass.isFocused) {
                txtPass.clearFocus()
                txtUser.HideKeyBoard()
            }
            v.HideKeyBoard()
            v.performClick()
            return@setOnTouchListener true
        }

        if (this.isModify) this.WriteDataModify()
    }

    private fun WriteDataModify() {
        if (App.loginModify == null) return

        this.login = App.loginModify!!
        txtNombre.setText(this.login.usuario.nombre)
        txtApellido.setText(this.login.usuario.apellido)
        txtTelefono.setText(this.login.usuario.telefono)
        txtDireccion.setText(this.login.usuario.direccion)
        txtUser.setText(this.login.userName)
        txtUser.isEnabled = false
        txtPass.setText(this.login.password)
        txtPass.isEnabled = false

        if (this.login.usuario.tipo == 1) rbEmpleado.isChecked = true
        else rbUsuario.isChecked = true
        if (this.login.estado) rbActivo.isChecked = true
        else rbInactivo.isChecked = true

    }
}