package com.appinc.cocoshop.adapters

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
import com.appinc.cocoshop.fragments.UsuarioFragment
import com.appinc.cocoshop.models.Login
import com.appinc.cocoshop.views.UsuarioActivity
import kotlinx.android.synthetic.main.layout_list.view.*

class UsuarioAdapter(private val fragment: UsuarioFragment) :
    RecyclerView.Adapter<UsuarioAdapter.HolderUser>() {

    private var logins: List<Login> = arrayListOf()

    fun load(logins: List<Login>) {
        this.logins = logins
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HolderUser = HolderUser(
        LayoutInflater.from(fragment.context).inflate(
            R.layout.layout_list, parent, false
        )
    )

    override fun onBindViewHolder(holder: HolderUser, position: Int) {
        val login = this.logins[position]

        val nameC = "${login.usuario.nombre} ${login.usuario.apellido}"
        holder.lbl1.text = nameC
        holder.lbl2.text = login.userName
        holder.lbl3.text = if (login.usuario.tipo == 1) "Empleado" else "Usuario"
        holder.lbl4.text = if (login.estado) "Activo" else "Inactivo"
        holder.img.setImageResource(R.drawable.ic_user)
        holder.card.setOnClickListener {
            App.loginModify = this.logins[position]

            val intent = Intent(fragment.context, UsuarioActivity::class.java)
            intent.putExtra("IsModify", true)
            fragment.activity?.startActivity(intent)
        }
    }

    override fun getItemCount(): Int = this.logins.size

    class HolderUser(view: View) : RecyclerView.ViewHolder(view) {
        val card: CardView = view.Card
        val img: ImageView = view.img
        val lbl1: TextView = view.lbl1
        val lbl2: TextView = view.lbl2
        val lbl3: TextView = view.lbl3
        val lbl4: TextView = view.lbl4
    }
}