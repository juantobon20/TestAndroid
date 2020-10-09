package com.appinc.cocoshop.adapters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.appinc.cocoshop.fragments.AbonosFragment
import com.appinc.cocoshop.fragments.VentaFragment
import com.appinc.cocoshop.fragments.UsuarioFragment

class MainAdapter(fm: FragmentManager) :
    FragmentPagerAdapter(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

    override fun getCount(): Int = 3

    override fun getItem(position: Int): Fragment {
        return when (position) {
            1 -> VentaFragment.GetInstance()
            2 -> AbonosFragment.GetInstance()
            else -> UsuarioFragment.GetInstance()
        }
    }
}