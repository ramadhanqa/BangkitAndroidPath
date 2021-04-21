package com.dicoding.aplikasi2

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.dicoding.aplikasi2.databinding.ItemFavoriteBinding


class FavoriteViewAdapter(private val listFavorite: ArrayList<Favorite>) :
    RecyclerView.Adapter<FavoriteViewAdapter.ListViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
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
                Log.d("tes",imgAvatar.toString())
                tvId.text = favorite.login
                tvUsername.text = favorite.login
            }
        }

    }

    fun setData(list: ArrayList<Favorite>) {
        listFavorite.clear()
        listFavorite.addAll(list)
        notifyDataSetChanged()
    }
}
