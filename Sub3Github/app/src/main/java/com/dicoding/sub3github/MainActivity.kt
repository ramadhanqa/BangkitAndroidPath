package com.dicoding.sub3github

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.KeyEvent
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.dicoding.sub3github.databinding.ActivityMainBinding
import com.dicoding.sub3github.entity.FavoriteDB
import com.dicoding.sub3github.model.user.User
import com.dicoding.sub3github.model.user.UserViewAdapter
import com.dicoding.sub3github.model.user.UserViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {


    private lateinit var binding : ActivityMainBinding
    private lateinit var adapter : UserViewAdapter
    private lateinit var viewModel : UserViewModel

    val db by lazy { FavoriteDB(this) }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)



        adapter = UserViewAdapter()
        adapter.notifyDataSetChanged()
        adapter.setOnItemClickCallback(object : UserViewAdapter.OnItemClickCallback{
            override fun onItemClicks(data: User) {
//                intent data
                Intent(this@MainActivity,DetailActivity::class.java).also {
                    it.putExtra(DetailActivity.EXTRA_ID,data.id)
                    it.putExtra(DetailActivity.EXTRA_USERNAME, data.login)
                    it.putExtra(DetailActivity.EXTRA_IMG, data.avatar_url)
                    Toast.makeText(this@MainActivity,"Anda memilih "+data.login,Toast.LENGTH_SHORT).show()
                    startActivity(it)
                }
            }
        })
        viewModel = ViewModelProvider(this, ViewModelProvider.NewInstanceFactory()).get(UserViewModel::class.java)

        binding.apply {
            rvGithubUser.layoutManager = LinearLayoutManager(this@MainActivity)
            rvGithubUser.setHasFixedSize(true)
            rvGithubUser.adapter = adapter
            viewModel.setSearchs("a")

            etSearch.setOnKeyListener { view, i, keyEvent ->
//              TOMBOL SEARCH
                if (keyEvent.action == KeyEvent.ACTION_DOWN && i == KeyEvent.KEYCODE_ENTER){
                    cariUser()
                    return@setOnKeyListener true
                }
                return@setOnKeyListener false
            }
        }
        viewModel.getSearchs().observe(this,{
            if (it!=null){
                adapter.setList(it)
                showLoads(false)
            }
        })
    }

    private fun cariUser(){
        binding.apply {
            val query = etSearch.text.toString()
                showLoads(true)
                viewModel.setSearchs(query)
        }
    }

    private fun showLoads(stats: Boolean){
        if (stats){
            binding.pgBar.visibility = View.VISIBLE
        }else{
            binding.pgBar.visibility = View.GONE
        }
    }

    override fun onStart() {
        super.onStart()
        CoroutineScope(Dispatchers.IO).launch {
            val favorites = db.favoriteDao().getFavorite()
            Log.d("MainActivity","respon $favorites")
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.option_menu,menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.setting->{
                val i = Intent(this,PreferenceActivity::class.java)
                startActivity(i)
            }
            R.id.favorite->{
                val i = Intent(this,FavoriteActivity::class.java)
                startActivity(i)
            }
        }
        return true
    }
}