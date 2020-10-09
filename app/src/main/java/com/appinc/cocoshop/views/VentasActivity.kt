package com.appinc.cocoshop.views

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.appinc.cocoshop.App
import com.appinc.cocoshop.R
import com.appinc.cocoshop.adapters.ProductAdapter
import com.appinc.cocoshop.adapters.UsuarioAdapter
import com.appinc.cocoshop.databinding.ActivityUsuarioBinding
import com.appinc.cocoshop.databinding.ActivityVentasBinding
import com.appinc.cocoshop.models.Product
import com.appinc.cocoshop.viewModels.UsuarioVM
import com.appinc.cocoshop.viewModels.VentaVM
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_ventas.*

const val RESULT_CODE: Int = 1

class VentasActivity : AppCompatActivity() {

    private lateinit var adapter: ProductAdapter
    private var total: Double = 0.0

    private lateinit var viewModel: VentaVM

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val viewModel = ViewModelProviders.of(this).get(VentaVM::class.java)
        this.viewModel = viewModel

        viewModel.init(this)
        DataBindingUtil.setContentView<ActivityVentasBinding>(this, R.layout.activity_ventas)
            .apply {
                this.lifecycleOwner = this@VentasActivity
                this.viewModel = viewModel
            }

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
            this.viewModel.load(product)
        }
    }
}