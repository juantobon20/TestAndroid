package com.appinc.cocoshop.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.appinc.cocoshop.R

class VentaFragment : Fragment() {

    companion object {
        private var instance: VentaFragment? = null
        fun GetInstance() : VentaFragment{
            if (instance == null) instance = VentaFragment()
            return instance!!
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_venta, container, false)
    }
}