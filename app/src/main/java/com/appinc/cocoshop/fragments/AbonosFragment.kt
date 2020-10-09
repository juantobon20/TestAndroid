package com.appinc.cocoshop.fragments

import android.app.Activity
import android.app.AlertDialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.CheckBox
import android.widget.ImageButton
import android.widget.RadioButton
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.appinc.cocoshop.R
import com.appinc.cocoshop.adapters.AbonoAdapter
import com.appinc.cocoshop.adapters.UsuarioAdapter
import com.appinc.cocoshop.tools.MsgBox
import com.appinc.cocoshop.viewModels.AbonoVM
import com.appinc.cocoshop.viewModels.FragmentUserVM
import com.google.android.material.textfield.TextInputEditText
import kotlinx.android.synthetic.main.fragment_usuario.*

class AbonosFragment : Fragment() {

    private lateinit var root: View
    private lateinit var adapter: AbonoAdapter
    private lateinit var viewModel: AbonoVM

    private lateinit var rcAbono: RecyclerView
    protected lateinit var swipeAbono: SwipeRefreshLayout

    private var dialog: AlertDialog? = null

    companion object {
        private var instance: AbonosFragment? = null
        fun GetInstance(): AbonosFragment {
            if (instance == null) instance = AbonosFragment()
            return instance!!
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        this.root = inflater.inflate(R.layout.fragment_abonos, container, false)

        this.adapter = AbonoAdapter(activity as Activity)
        this.rcAbono = root.findViewById(R.id.rcAbono)
        this.rcAbono.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        this.rcAbono.adapter = this.adapter
        this.swipeAbono = root.findViewById(R.id.swipeAbono)
        this.swipeAbono.setOnRefreshListener {
            adapter.load(arrayListOf())
            lblRegs.text = getString(R.string.buscando)
            this.viewModel.GetAll()
        }

        this.viewModel = ViewModelProviders.of(this).get(AbonoVM::class.java)
        this.viewModel.init(activity as Activity)
        this.viewModel.GetIsBusy().observe(viewLifecycleOwner, {
            swipeAbono.isRefreshing = it
        })
        this.viewModel.GetLoginsLiveData().observe(viewLifecycleOwner, {
            this.adapter.load(it)
            lblRegs.text = getString(R.string.usuarios_registrados, it.size.toString())
        })

        return this.root
    }

    fun NewAbono() {
        if (this.dialog != null) {
            dialog?.show()
            return
        }

        val dialogView = this.activity?.layoutInflater?.inflate(
            R.layout.layout_abono,
            null
        )
        this.dialog = AlertDialog.Builder(context!!)
            .setView(dialogView)
            .setCancelable(false)
            .show()
        dialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

        dialogView?.findViewById<Button>(R.id.btnSave)?.setOnClickListener {
            var value = dialogView.findViewById<TextInputEditText>(R.id.txtAbono).text.toString()
            if (value.isEmpty()) {
                MsgBox(context!!).Msg("Debe ingresar un valor")
                return@setOnClickListener
            }

            if (value.contains(", ")) value = value.replace(",", "")
            this.viewModel.onSaveClicked(value.toDouble())

            dialogView.findViewById<TextInputEditText>(R.id.txtAbono).setText("")
            dialog?.dismiss()
        }

        dialogView?.findViewById<Button>(R.id.btnCancelar)?.setOnClickListener {
            dialogView.findViewById<TextInputEditText>(R.id.txtAbono).setText("")
            dialog?.dismiss()
        }
    }
}