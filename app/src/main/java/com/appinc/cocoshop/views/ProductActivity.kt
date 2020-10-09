package com.appinc.cocoshop.views

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.appinc.cocoshop.R
import com.appinc.cocoshop.adapters.ProductAdapter
import kotlinx.android.synthetic.main.activity_product.*
import kotlinx.android.synthetic.main.activity_ventas.*

class ProductActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_product)

        val rcProductos = findViewById<RecyclerView>(R.id.rcProductos)
        rcProductos.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        rcProductos.adapter = ProductAdapter(this)
    }
}