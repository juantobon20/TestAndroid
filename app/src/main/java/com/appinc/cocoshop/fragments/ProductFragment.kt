package com.appinc.cocoshop.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.appinc.cocoshop.R

class ProductFragment : Fragment() {

    companion object {
        private var instance: ProductFragment? = null
        fun GetInstance() : ProductFragment{
            if (instance == null) instance = ProductFragment()
            return instance!!
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_product, container, false)
    }
}