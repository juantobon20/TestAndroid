package com.appinc.cocoshop.views

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.appinc.cocoshop.R
import kotlinx.android.synthetic.main.activity_splash.*

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        val handler = Handler()
        Thread {
            Thread.sleep(3000)
            handler.post {
                animation_view.pauseAnimation()
                val intent = Intent(baseContext, LoginActivity::class.java)
                startActivity(intent)
                finish()
            }
        }.start()
    }
}