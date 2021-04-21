package com.dicoding.picodiploma.submissionandroidpemula

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast

class About : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_about)

        Toast.makeText(this, "About Me", Toast.LENGTH_LONG).show()



        //actionbar
        val actionbar = supportActionBar
        //set actionbar title
        actionbar!!.title = "About Me"
        //set back button
        actionbar.setDisplayHomeAsUpEnabled(true)
        actionbar.setDisplayHomeAsUpEnabled(true)

    }
    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}