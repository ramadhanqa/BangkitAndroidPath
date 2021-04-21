package com.dicoding.moviecatalogjetpack

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.dicoding.moviecatalogjetpack.ui.home.HomeActivity

class Splash : AppCompatActivity() {
    lateinit var handler: Handler

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        handler = Handler()
        handler.postDelayed({
            val intent = Intent (this,HomeActivity::class.java)
            startActivity(intent)
            finish()
        },3000) //delay 3 detik

    }
}