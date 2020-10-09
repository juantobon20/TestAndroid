package com.appinc.cocoshop.adapters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.appinc.cocoshop.fragments.ProductFragment
import com.appinc.cocoshop.fragments.ReportVentasFragment
import com.appinc.cocoshop.fragments.UsuarioFragment

class MainAdapter(fm: FragmentManager) :
    FragmentPagerAdapter(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

    override fun getCount(): Int = 4

    override fun getItem(position: Int): Fragment {
        return when (position) {
            1 -> UsuarioFragment.GetInstanceEmployee()
            2 -> ProductFragment.GetInstance()
            3 -> ReportVentasFragment.GetInstance()
            else -> UsuarioFragment.GetInstanceUser()
        }
    }
}