package com.appinc.cocoshop.views

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
        main_container.offscreenPageLimit = 4
        this.InitFragments()
    }

    private fun InitFragments() {
        this.currentId = R.id.navigation_users
        bottomNavigation.setMenuResource(R.menu.menu_main_employee)
        bottomNavigation.setItemSelected(R.id.navigation_users)

        bottomNavigation.setOnItemSelectedListener {
            if (this.currentId != it) {
                this.currentId = it

                when (this.currentId) {
                    R.id.navigation_users -> main_container.currentItem = 0
                    R.id.navigation_employees -> main_container.currentItem = 1
                    R.id.navigation_products -> main_container.currentItem = 2
                    R.id.navigation_report_sale -> main_container.currentItem = 3
                }
            }
        }
    }
}