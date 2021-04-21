package com.dicoding.sub3github.model.favorite

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.dicoding.sub3github.DetailActivity
import com.dicoding.sub3github.R
import com.dicoding.sub3github.databinding.ItemFavoriteBinding
import com.dicoding.sub3github.entity.Favorite

class FavoriteViewAdapter(private val listFavorite: ArrayList<Favorite>) :
    RecyclerView.Adapter<FavoriteViewAdapter.ListViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
//        val binding = ItemFavoriteBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        val binding =
            LayoutInflater.from(parent.context).inflate(R.layout.item_favorite, parent, false)
        return ListViewHolder(binding)
    }

    override fun getItemCount(): Int = listFavorite.size
    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        holder.bind(listFavorite[position])
    }

    inner class ListViewHolder(val bindings: View) : RecyclerView.ViewHolder(bindings) {
        private val binding = ItemFavoriteBinding.bind(bindings)
        fun bind(favorite: Favorite) {
            with(binding) {
                Glide.with(itemView.context)
                    .load(favorite.avatar_url)
                    .apply(RequestOptions().override(55, 55))
                    .into(imgAvatar)
                tvId.text = favorite.login
                tvUsername.text = favorite.login
                bindings.setOnClickListener {
                    Intent(bindings.context, DetailActivity::class.java).also {
                        it.putExtra(DetailActivity.EXTRA_ID, favorite.id)
                        it.putExtra(DetailActivity.EXTRA_USERNAME, favorite.login)
                        it.putExtra(DetailActivity.EXTRA_IMG, favorite.avatar_url)
                        bindings.context.startActivity(it)
                    }
                }
            }
        }

    }

    fun setData(list: ArrayList<Favorite>) {
        listFavorite.clear()
        listFavorite.addAll(list)
        notifyDataSetChanged()
    }
}
