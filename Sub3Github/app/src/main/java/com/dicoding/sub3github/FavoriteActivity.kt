package com.dicoding.sub3github

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.dicoding.sub3github.databinding.ActivityFavoriteBinding
import com.dicoding.sub3github.entity.Favorite
import com.dicoding.sub3github.entity.FavoriteDB
import com.dicoding.sub3github.model.favorite.FavoriteViewAdapter
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class FavoriteActivity : AppCompatActivity() {
    private lateinit var binding: ActivityFavoriteBinding

    val db by lazy { FavoriteDB(this) }
    private lateinit var favoriteAdapter: FavoriteViewAdapter
    private val list = ArrayList<Favorite>()



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFavoriteBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.rvFavoriteUser.setHasFixedSize(true)

        setTitle("Favorite User")

        getListFavorite()

    }

    private fun getListFavorite(): ArrayList<Favorite> {
        var listFavorite = ArrayList<Favorite>()
        CoroutineScope(Dispatchers.IO).launch {
            val favorites = db.favoriteDao().getFavorite()
            listFavorite = mapList(favorites)
            list.addAll(listFavorite)
            showRecyclerList()
        }
        return listFavorite

    }

    private fun mapList(favorites: List<Favorite>): ArrayList<Favorite> {
        val listFavorite = ArrayList<Favorite>()
        for (favorite in favorites) {
            val favoriteMapped = Favorite(
                favorite.id,
                favorite.login,
                favorite.avatar_url
            )
            listFavorite.add(favoriteMapped)
        }
        return listFavorite

    }

    private fun showRecyclerList() {
        binding.rvFavoriteUser.setHasFixedSize(true)
        binding.rvFavoriteUser.layoutManager = LinearLayoutManager(this)
        favoriteAdapter = FavoriteViewAdapter(list)
        binding.rvFavoriteUser.adapter = favoriteAdapter
        favoriteAdapter.notifyDataSetChanged()
    }

    override fun onStart() {
        super.onStart()
        CoroutineScope(Dispatchers.IO).launch {
            val favorites = db.favoriteDao().getFavorite()
            withContext(Dispatchers.Main) {
                favoriteAdapter.setData(mapList(favorites))
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.option_menu, menu)
        val favItem: MenuItem? = menu?.findItem(R.id.favorite)
        if (this == this) {
            favItem?.setVisible(false)
        }
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.setting -> {
                val i = Intent(this, PreferenceActivity::class.java)
                startActivity(i)
            }
        }
        return true
    }
}