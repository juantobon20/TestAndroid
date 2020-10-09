package com.appinc.cocoshop.models

class Login {

    var id: Int = 0
    var usuarioId: Int = 0
    var userName: String = ""
    var password: String = ""
    var estado: Boolean = false

    val msgError: String? = ""
    val usuario: Usuario = Usuario()
}