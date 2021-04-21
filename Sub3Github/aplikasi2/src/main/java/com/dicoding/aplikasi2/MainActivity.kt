package com.dicoding.aplikasi2

import android.database.ContentObserver
import android.os.Bundle
import android.os.Handler
import android.os.HandlerThread
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.dicoding.aplikasi2.DatabaseContract.FavsColumn.Companion.CONTENT_URI
import com.dicoding.aplikasi2.databinding.ActivityMainBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var adapter: FavoriteViewAdapter

    companion object {
        private const val EXTRA_STATE = "EXTRA_STATE"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.rvFavoriteUser.setHasFixedSize(true)

        binding.rvFavoriteUser.layoutManager = LinearLayoutManager(this)
        binding.rvFavoriteUser.setHasFixedSize(true)
        adapter = FavoriteViewAdapter(ArrayList())
        binding.rvFavoriteUser.adapter = adapter
        val handlerThread = HandlerThread("DataObserver")
        handlerThread.start()
        val handler = Handler(handlerThread.looper)

        val myObserver = object : ContentObserver(handler) {
            override fun onChange(self: Boolean) {
                Log.d("aaa", "aaaa2")
                loadFavAsync()
            }
        }
        contentResolver.registerContentObserver(CONTENT_URI, true, myObserver)

        if (savedInstanceState == null) {
            loadFavAsync()
        } else {
            savedInstanceState.getParcelableArrayList<Favorite>(EXTRA_STATE)
                ?.also { adapter.setData(it) }
        }


    }

    private fun loadFavAsync() {
        GlobalScope.launch(Dispatchers.Main) {
            binding.pgBar.visibility = View.VISIBLE
            val deferredFavs = async(Dispatchers.IO) {
                val cursor = contentResolver?.query(CONTENT_URI, null, null, null, null)
                MappingHelper.mapCursorToArrayList(cursor)
            }
            val favs = deferredFavs.await()
            binding.pgBar.visibility = View.INVISIBLE
            if (favs.size > 0) {
                adapter.setData(favs)
            } else {
                adapter.setData(ArrayList())
            }
        }
    }

    override fun onStart() {
        super.onStart()
        loadFavAsync()
    }
}