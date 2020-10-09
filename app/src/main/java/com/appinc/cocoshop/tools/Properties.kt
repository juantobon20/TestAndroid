package com.appinc.cocoshop.tools

import android.content.Context
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import java.math.BigInteger
import java.security.MessageDigest

//Metodo para ocultar el teclado
fun View.HideKeyBoard() =
    (context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager).hideSoftInputFromWindow(
        windowToken,
        0
    )

fun EditText.Empty(): Boolean {
    if (this.text.isEmpty()) {
        this.error = "Campo requerido"
        return true
    }
    return false
}

fun String.Md5(): String {
    val md = MessageDigest.getInstance("MD5")
    return BigInteger(1, md.digest(toByteArray())).toString(16).padStart(32, '0')
}