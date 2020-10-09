package com.appinc.cocoshop.fragments

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.appinc.cocoshop.R
import com.appinc.cocoshop.views.MainActivity
import com.appinc.cocoshop.views.VentasActivity
import kotlinx.android.synthetic.main.activity_main.*

class VentaFragment : Fragment() {

    private lateinit var root: View

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

        (activity as MainActivity).btnAdd.setOnClickListener {
            activity?.startActivity(Intent(activity, VentasActivity::class.java))
        }
        // Inflate the layout for this fragment
        return root
    }
}