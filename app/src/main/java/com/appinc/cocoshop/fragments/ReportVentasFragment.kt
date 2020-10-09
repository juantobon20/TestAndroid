package com.appinc.cocoshop.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.appinc.cocoshop.R

class ReportVentasFragment : Fragment() {

    companion object {
        private var instance: ReportVentasFragment? = null
        fun GetInstance() : ReportVentasFragment{
            if (instance == null) instance = ReportVentasFragment()
            return instance!!
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_report_ventas, container, false)
    }
}