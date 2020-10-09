package com.appinc.cocoshop.views

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.viewpager.widget.ViewPager
import com.appinc.cocoshop.R
import com.appinc.cocoshop.adapters.MainAdapter
import com.appinc.cocoshop.fragments.AbonosFragment
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private var currentId: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        main_container.adapter = MainAdapter(supportFragmentManager)
        main_container.setOnPageChangeListener(object : ViewPager.OnPageChangeListener {
            override fun onPageScrolled(
                position: Int,
                positionOffset: Float,
                positionOffsetPixels: Int
            ) {
            }

            override fun onPageSelected(position: Int) {
                val res = when (position) {
                    1 -> R.id.navigation_ventas
                    2 -> R.id.navigation_abonos
                    else -> R.id.navigation_users
                }
                bottomNavigation.setItemSelected(res)
            }

            override fun onPageScrollStateChanged(state: Int) {
            }

        })
        main_container.offscreenPageLimit = 3
        this.InitFragments()

        btnAdd.setOnClickListener {
            when (main_container.currentItem) {
                0 ->
                    startActivity(Intent(this, UsuarioActivity::class.java))
                1 ->
                    startActivity(Intent(this, VentasActivity::class.java))
                2 ->
                    AbonosFragment.GetInstance().NewAbono()
            }

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
                    R.id.navigation_abonos -> main_container.currentItem = 2
                }
            }
        }
    }
}