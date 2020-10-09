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
import com.appinc.cocoshop.viewModels.FragmentUserVM
import com.appinc.cocoshop.views.MainActivity
import com.appinc.cocoshop.views.UsuarioActivity
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_usuario.*

class UsuarioFragment : Fragment() {

    private lateinit var root: View
    private lateinit var adapter: UsuarioAdapter
    private lateinit var viewModel: FragmentUserVM

    private lateinit var rcUser: RecyclerView
    protected lateinit var swipeUser: SwipeRefreshLayout

    companion object {
        private var instance: UsuarioFragment? = null

        fun GetInstance(): UsuarioFragment {
            if (instance == null) instance = UsuarioFragment()
            return instance!!
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        this.root = inflater.inflate(R.layout.fragment_usuario, container, false)

        this.adapter = UsuarioAdapter(this)
        this.rcUser = root.findViewById(R.id.rcUser)
        this.rcUser.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        this.rcUser.adapter = this.adapter
        this.swipeUser = root.findViewById(R.id.swipeUsers)
        this.swipeUser.setOnRefreshListener {
            adapter.load(arrayListOf())
            lblRegs.text = getString(R.string.buscando)
            this.viewModel.GetAsyncAll()
        }

        this.viewModel = ViewModelProviders.of(this).get(FragmentUserVM::class.java)
        this.viewModel.GetIsBusy().observe(viewLifecycleOwner, {
            swipeUser.isRefreshing = it
        })
        this.viewModel.GetLoginsLiveData().observe(viewLifecycleOwner, {
            this.adapter.load(it)
            lblRegs.text = getString(R.string.usuarios_registrados, it.size.toString())
        })

        return this.root
    }
}