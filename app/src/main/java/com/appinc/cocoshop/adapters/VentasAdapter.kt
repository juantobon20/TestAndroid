package com.appinc.cocoshop.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.appinc.cocoshop.App
import com.appinc.cocoshop.R
import com.appinc.cocoshop.fragments.VentaFragment
import com.appinc.cocoshop.models.Documento
import kotlinx.android.synthetic.main.layout_list.view.*

class VentasAdapter(private val fragment: VentaFragment) :
    RecyclerView.Adapter<VentasAdapter.HolderVentas>() {

    private var documentos: List<Documento> = arrayListOf()

    fun load(documentos: List<Documento>) {
        this.documentos = documentos
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HolderVentas =
        HolderVentas(
            LayoutInflater.from(fragment.context).inflate(
                R.layout.layout_list, parent, false
            )
        )

    override fun onBindViewHolder(holder: HolderVentas, position: Int) {
        val documento = this.documentos[position]

        holder.img.setImageResource(R.drawable.ic_ventas_24dp)
        holder.lbl1.text = App.coinFormat.format(documento.vrTotal)
        holder.lbl2.text = documento.fecha.toString()
        holder.lbl3.visibility = View.GONE
        holder.lbl4.visibility = View.GONE
    }

    override fun getItemCount(): Int = this.documentos.size

    class HolderVentas(view: View) : RecyclerView.ViewHolder(view) {
        val img: ImageView = view.img
        val lbl1: TextView = view.lbl1
        val lbl2: TextView = view.lbl2
        val lbl3: TextView = view.lbl3
        val lbl4: TextView = view.lbl4
    }
}