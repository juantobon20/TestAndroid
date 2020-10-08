package com.appinc.cocoshop.views

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.appinc.cocoshop.R
import com.appinc.cocoshop.tools.Empty
import com.appinc.cocoshop.tools.HideKeyBoard
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        layout.setOnTouchListener { v, _ ->
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

        btnIngresar.setOnClickListener {
            if (txtUser.Empty() || txtPass.Empty()) return@setOnClickListener

            startActivity(Intent(this, MainActivity::class.java))
        }
    }
}