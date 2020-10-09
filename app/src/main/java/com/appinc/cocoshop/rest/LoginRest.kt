package com.appinc.cocoshop.rest

import com.appinc.cocoshop.models.Login

class LoginRest {
    private val connection = Connection()

    fun Get(): List<Login>? = connection.iLogin.Get().execute().body()
    fun Get(id: Int): Login? = connection.iLogin.Get(id).execute().body()
    fun Post(login: Login): Login? = connection.iLogin.Post(login).execute().body()
    fun Put(id: Int, login: Login): Login? = connection.iLogin.Put(id, login).execute().body()
}