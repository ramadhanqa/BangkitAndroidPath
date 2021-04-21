package com.dicoding.sub3github

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.dicoding.sub3github.alarm.MainPreference
import com.dicoding.sub3github.databinding.ActivityPreferenceBinding

class PreferenceActivity : AppCompatActivity() {

    private var binding: ActivityPreferenceBinding? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPreferenceBinding.inflate(layoutInflater)
        setContentView(binding!!.root)
        setTitle("Set Alarm")



        supportFragmentManager
            .beginTransaction()
            .replace(R.id.content_preference, MainPreference())
            .commit()
    }

    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }

}