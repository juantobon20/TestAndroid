package com.appinc.cocoshop

import android.app.Application
import com.appinc.cocoshop.models.Login

class App : Application() {

    companion object {
        var loginModify: Login? = null
    }

    override fun onCreate() {
        super.onCreate()
    }
}