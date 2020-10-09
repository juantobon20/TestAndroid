package com.appinc.cocoshop.fragments

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.appinc.cocoshop.R
import com.appinc.cocoshop.adapters.UsuarioAdapter
import com.appinc.cocoshop.adapters.VentasAdapter
import com.appinc.cocoshop.viewModels.FragmentUserVM
import com.appinc.cocoshop.viewModels.VentaVM
import com.appinc.cocoshop.views.MainActivity
import com.appinc.cocoshop.views.VentasActivity
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_usuario.*

class VentaFragment : Fragment() {

    private lateinit var root: View
    private lateinit var adapter: VentasAdapter
    private lateinit var viewModel: VentaVM

    private lateinit var rcVentas: RecyclerView
    protected lateinit var swipeVentas: SwipeRefreshLayout

    companion object {
        private var instance: VentaFragment? = null
        fun GetInstance(): VentaFragment {
            if (instance == null) instance = VentaFragment()
            return instance!!
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        this.root = inflater.inflate(R.layout.fragment_venta, container, false)

        this.adapter = VentasAdapter(this)
        this.rcVentas = root.findViewById(R.id.rcVentas)
        this.rcVentas.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        this.rcVentas.adapter = this.adapter
        this.swipeVentas = root.findViewById(R.id.swipeVentas)
        this.swipeVentas.setOnRefreshListener {
            adapter.load(arrayListOf())
            lblRegs.text = getString(R.string.buscando)
            this.viewModel.GetAll()
        }

        this.viewModel = ViewModelProviders.of(this).get(VentaVM::class.java)
        this.viewModel.GetIsBusy().observe(viewLifecycleOwner, {
            swipeVentas.isRefreshing = it
        })
        this.viewModel.GetLoginsLiveData().observe(viewLifecycleOwner, {
            this.adapter.load(it)
            lblRegs.text = getString(R.string.ventas_registradas, it.size.toString())
        })


        // Inflate the layout for this fragment
        return root
    }
}