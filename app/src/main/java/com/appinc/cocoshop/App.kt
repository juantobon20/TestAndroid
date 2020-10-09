package com.appinc.cocoshop

import android.app.Application
import com.appinc.cocoshop.models.Login
import com.appinc.cocoshop.models.Product
import java.text.NumberFormat
import java.util.*

class App : Application() {

    companion object {
        var loginModify: Login? = null
        var products : MutableList<Product> = arrayListOf()
        lateinit var coinFormat: NumberFormat
    }

    override fun onCreate() {
        super.onCreate()

        coinFormat = NumberFormat.getCurrencyInstance(Locale("es", "US"))
        coinFormat.maximumFractionDigits = 2

        products = arrayListOf()
        products.add(Product(1, "Manzanas", 1000.0))
        products.add(Product(1, "Uvas", 500.0))
        products.add(Product(1, "Mangos", 1500.0))
        products.add(Product(1, "Peras", 2500.0))
    }
}