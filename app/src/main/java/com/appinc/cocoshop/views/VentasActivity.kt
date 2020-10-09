package com.appinc.cocoshop.views

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.appinc.cocoshop.App
import com.appinc.cocoshop.R
import com.appinc.cocoshop.adapters.ProductAdapter
import com.appinc.cocoshop.adapters.UsuarioAdapter
import com.appinc.cocoshop.models.Product
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_ventas.*

const val RESULT_CODE: Int = 1

class VentasActivity : AppCompatActivity() {

    private lateinit var adapter: ProductAdapter
    private var total: Double = 0.0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ventas)

        this.adapter = ProductAdapter(this, true)
        val rcVentas = findViewById<RecyclerView>(R.id.rcVentas)
        rcVentas.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        rcVentas.adapter = adapter

        btnAdd.setOnClickListener {
            startActivityForResult(Intent(this, ProductActivity::class.java), RESULT_CODE)
        }

        this.CalculeTotal()
    }

    private fun CalculeTotal() {
        lblTotal.text = getString(R.string.valor_total, App.coinFormat.format(this.total))
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == RESULT_CODE && resultCode == RESULT_OK) {
            val product = Gson().fromJson(data?.extras?.getString("Producto"), Product::class.java)
            this.adapter.loadProduct(product)

            this.total += product.precio
            this.CalculeTotal()
        }
    }
}