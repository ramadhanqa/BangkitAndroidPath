package com.dicoding.sub3github

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.dicoding.sub3github.databinding.ActivityDetailBinding
import com.dicoding.sub3github.entity.Favorite
import com.dicoding.sub3github.entity.FavoriteDB
import com.dicoding.sub3github.model.detail.DetailViewModel
import com.dicoding.sub3github.model.fragment.SectionPageAdapter
import kotlinx.android.synthetic.main.activity_detail.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class DetailActivity : AppCompatActivity() {

    val db by lazy { FavoriteDB(this) }

    companion object {
        const val EXTRA_USERNAME = "extra_username"
        const val EXTRA_IMG = "extra_img"
        const val EXTRA_ID = "extra_id"
    }

    var username = ""
    var avatar_url = ""
    var id = 0
    private lateinit var binding: ActivityDetailBinding

    private lateinit var viewModel: DetailViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        username = intent.getStringExtra(EXTRA_USERNAME).toString()
        avatar_url = intent.getStringExtra(EXTRA_IMG).toString()
        id = intent.getIntExtra(EXTRA_ID, 0)
//        fragment
        val mBundle = Bundle()
        mBundle.putString(EXTRA_USERNAME, username)
        mBundle.putString(EXTRA_IMG, avatar_url)




        viewModel = ViewModelProvider(
            this,
            ViewModelProvider.NewInstanceFactory()
        ).get(DetailViewModel::class.java)
        if (username != null) {
            viewModel.setDetails(username)
        }
        viewModel.getDetailUser().observe(this, {
            if (it != null) {
                binding.apply {
                    tvName.text = it.name

                    tvFollowers.text = it.followers.toString()
                    tvFollowing.text = it.following.toString()
                    tvRepos.text = it.public_repos.toString()
                    setTitle(it.login)
                    Glide.with(this@DetailActivity)
                        .load(it.avatar_url)
                        .centerCrop()
                        .into(imgAvatar)

                }
            }
        })
        val sectionPageAdapter = SectionPageAdapter(this, supportFragmentManager, mBundle)
        binding.apply {
            vpTabs.adapter = sectionPageAdapter
            tabLayout.setupWithViewPager(vpTabs)

        }
        var statusFavorite = true
        var fab = binding.faFavorite
        fab.setOnClickListener {
            CoroutineScope(Dispatchers.IO).launch {
                val count = db.favoriteDao().getFavoritebyId(username)
                if (count == 0.toString() && statusFavorite) {
                    fa_favorite.setImageDrawable(
                        ContextCompat.getDrawable(
                            applicationContext,
                            R.drawable.ic_red_favorite
                        )
                    )
                    statusFavorite = false
                    addFavorite()
                    runOnUiThread {
                        Toast.makeText(this@DetailActivity,username+" telah difavoritkan",Toast.LENGTH_LONG).show()
                    }
                } else {
                    fa_favorite.setImageDrawable(
                        ContextCompat.getDrawable(
                            applicationContext,
                            R.drawable.ic_baseline_favorite_border_24
                        )
                    )
                    statusFavorite = true
                    db.favoriteDao().deleteFavoritebyId(id)
                    runOnUiThread {
                        Toast.makeText(this@DetailActivity,username+" telah hapus dari favorit",Toast.LENGTH_LONG).show()
                    }

                }
            }
        }


    }

    private fun addFavorite() {
        CoroutineScope(Dispatchers.IO).launch {
            db.favoriteDao().addFavorite(Favorite(id, username, avatar_url))
        }
    }


    private fun setStatusFavorite(statusFavorite: Boolean) {
        if (statusFavorite)
            fa_favorite.setImageDrawable(
                ContextCompat.getDrawable(
                    applicationContext,
                    R.drawable.ic_baseline_favorite_border_24
                )
            )
        else
            fa_favorite.setImageDrawable(
                ContextCompat.getDrawable(
                    applicationContext,
                    R.drawable.ic_red_favorite
                )
            )
    }


    override fun onStart() {
        super.onStart()
        CoroutineScope(Dispatchers.IO).launch {
            var statusFavorite = false
            val count = db.favoriteDao().getFavoritebyId(username)
            Log.d("testing", count)
            if (count == 0.toString()) {
                statusFavorite = !statusFavorite
                setStatusFavorite(statusFavorite)
            } else {
                statusFavorite == statusFavorite
                setStatusFavorite(statusFavorite)
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