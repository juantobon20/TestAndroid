package com.appinc.cocoshop.views

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toolbar
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import com.appinc.cocoshop.R
import com.appinc.cocoshop.databinding.ActivityUsuarioBinding
import com.appinc.cocoshop.tools.HideKeyBoard
import com.appinc.cocoshop.tools.MsgBox
import com.appinc.cocoshop.viewModels.UsuarioVM
import kotlinx.android.synthetic.main.activity_usuario.*

class UsuarioActivity : AppCompatActivity() {

    private var isModify: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val viewModel = ViewModelProviders.of(this).get(UsuarioVM::class.java)
        DataBindingUtil.setContentView<ActivityUsuarioBinding>(this, R.layout.activity_usuario)
            .apply {
                this.lifecycleOwner = this@UsuarioActivity
                this.viewModel = viewModel
            }

        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setActionBar(toolbar)
        this.isModify =
            if (intent != null && intent.extras != null) intent.extras?.getBoolean("IsModify")!! else false
        title = getString(if (this.isModify) R.string.modificar_usuario else R.string.crear_usuario)

        viewModel.init(this, this.isModify)
        viewModel.GetIsFinish().observe(this, {
            if (it)
                MsgBox(this).Msg(if (this.isModify) "Modificado correctamente" else "Guardado correctamente", "", true)
        })

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
    }
}