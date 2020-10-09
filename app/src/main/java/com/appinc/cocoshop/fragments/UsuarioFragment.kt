package com.appinc.cocoshop.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.appinc.cocoshop.R

class UsuarioFragment : Fragment() {

    private lateinit var root: View

    companion object {
        private var instanceUser: UsuarioFragment? = null
        private var instanceEmployee: UsuarioFragment? = null

        fun GetInstanceUser(): UsuarioFragment {
            if (instanceUser == null) instanceUser = UsuarioFragment()
            return instanceUser!!
        }

        fun GetInstanceEmployee(): UsuarioFragment {
            if (instanceEmployee == null) instanceEmployee = UsuarioFragment()
            return instanceEmployee!!
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        this.root = inflater.inflate(R.layout.fragment_usuario, container, false)

        return this.root
    }
}