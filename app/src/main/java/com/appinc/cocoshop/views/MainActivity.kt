package com.appinc.cocoshop.views

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.appinc.cocoshop.R
import com.appinc.cocoshop.adapters.MainAdapter
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private var currentId: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        main_container.adapter = MainAdapter(supportFragmentManager)
        main_container.offscreenPageLimit = 2
        this.InitFragments()

        btnAdd.setOnClickListener {
            if (main_container.currentItem == 0)
                startActivity(Intent(this, UsuarioActivity::class.java))
            else
                startActivity(Intent(this, VentasActivity::class.java))

        }
    }

    private fun InitFragments() {
        this.currentId = R.id.navigation_users
        bottomNavigation.setMenuResource(R.menu.menu_main)
        bottomNavigation.setItemSelected(R.id.navigation_users)

        bottomNavigation.setOnItemSelectedListener {
            if (this.currentId != it) {
                this.currentId = it

                when (this.currentId) {
                    R.id.navigation_users -> main_container.currentItem = 0
                    R.id.navigation_ventas -> main_container.currentItem = 1
                }
            }
        }
    }
}