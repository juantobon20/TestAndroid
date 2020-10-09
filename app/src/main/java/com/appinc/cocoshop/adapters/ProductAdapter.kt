package com.appinc.cocoshop.adapters

import android.app.Activity
import android.app.Activity.RESULT_OK
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.appinc.cocoshop.App
import com.appinc.cocoshop.R
import com.appinc.cocoshop.models.Login
import com.appinc.cocoshop.models.Product
import com.google.gson.Gson
import kotlinx.android.synthetic.main.layout_list.view.*

class ProductAdapter(private val activity: Activity, private val isVenta: Boolean = false) :
    RecyclerView.Adapter<ProductAdapter.HolderProduct>() {

    private var products: MutableList<Product> = arrayListOf()

    init {
        if (!isVenta) this.products = App.products
    }

    fun loadProduct(product: Product) {
        this.products.add(product)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HolderProduct =
        HolderProduct(
            LayoutInflater.from(activity).inflate(
                R.layout.layout_list, parent, false
            )
        )

    override fun onBindViewHolder(holder: HolderProduct, position: Int) {
        val product = this.products[position]

        holder.lbl1.text = product.nombre
        holder.lbl2.visibility = View.GONE
        holder.lbl3.text = App.coinFormat.format(product.precio)
        holder.lbl4.visibility = View.GONE
        holder.img.setImageResource(R.drawable.ic_frutas_24dp)
        holder.card.setOnClickListener {
            if (!isVenta) {
                val intent = Intent()
                intent.putExtra("Producto", Gson().toJson(products[position]))
                activity.setResult(RESULT_OK, intent)
                activity.finish()
            }
        }
    }

    override fun getItemCount(): Int = this.products.size

    class HolderProduct(view: View) : RecyclerView.ViewHolder(view) {
        val card: CardView = view.Card
        val img: ImageView = view.img
        val lbl1: TextView = view.lbl1
        val lbl2: TextView = view.lbl2
        val lbl3: TextView = view.lbl3
        val lbl4: TextView = view.lbl4
    }
}