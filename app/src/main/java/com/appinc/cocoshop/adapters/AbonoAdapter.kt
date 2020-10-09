package com.appinc.cocoshop.adapters

import android.app.Activity
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.appinc.cocoshop.App
import com.appinc.cocoshop.R
import com.appinc.cocoshop.fragments.AbonosFragment
import com.appinc.cocoshop.models.Abono
import com.appinc.cocoshop.models.Product
import com.google.gson.Gson
import kotlinx.android.synthetic.main.layout_list.view.*

class AbonoAdapter(private val activity: Activity) :
    RecyclerView.Adapter<AbonoAdapter.HolderAbono>() {

    private var abonos: List<Abono> = arrayListOf()

    fun load(abonos: List<Abono>) {
        this.abonos = abonos
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HolderAbono =
        HolderAbono(
            LayoutInflater.from(activity).inflate(
                R.layout.layout_list, parent, false
            )
        )

    override fun onBindViewHolder(holder: HolderAbono, position: Int) {
        val abono = this.abonos[position]

        val namec = "${abono.usuario.nombre} ${abono.usuario.apellido}"
        holder.lbl1.text = namec
        holder.lbl2.text = App.coinFormat.format(abono.vrTotal)
        holder.img.visibility = View.GONE
        holder.lbl3.visibility = View.GONE
        holder.lbl4.visibility = View.GONE
    }

    override fun getItemCount(): Int = this.abonos.size

    class HolderAbono(view: View) : RecyclerView.ViewHolder(view) {
        val img: ImageView = view.img
        val lbl1: TextView = view.lbl1
        val lbl2: TextView = view.lbl2
        val lbl3: TextView = view.lbl3
        val lbl4: TextView = view.lbl4
    }
}