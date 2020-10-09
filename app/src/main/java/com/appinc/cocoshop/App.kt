package com.appinc.cocoshop

import android.app.Application
import com.appinc.cocoshop.models.Login
import com.appinc.cocoshop.models.Product

class App : Application() {

    companion object {
        var loginModify: Login? = null
        var products : MutableList<Product> = arrayListOf()
    }

    override fun onCreate() {
        super.onCreate()

        products = arrayListOf()
        products.add(Product(1, "Manzanas", 1000.0))
    }
}