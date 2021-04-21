package com.dicoding.sub3github.model.user

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.dicoding.sub3github.databinding.ItemUserGithubBinding

class UserViewAdapter : RecyclerView.Adapter<UserViewAdapter.UserViewHolder>() {
    private val list = ArrayList<User>()

    private var onItemClickCallback : OnItemClickCallback? = null

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }


//    class didalam
    inner class UserViewHolder(val binding: ItemUserGithubBinding) :
        RecyclerView.ViewHolder(binding.root) {
          fun bind_user(user : User){

//              panggil callback
              binding.root.setOnClickListener {
                  onItemClickCallback?.onItemClicks(user)
              }

              binding.apply {
                  Glide.with(itemView)
                      .load(user.avatar_url)
                      .centerCrop()
                      .into(imgAvatar)
                  tvId.text = user.login
                  tvUsername.text = user.login
              }
          }
        }

    fun setList(users : ArrayList<User>){
        list.clear()
        list.addAll(users)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val view = ItemUserGithubBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return UserViewHolder((view))
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        holder.bind_user(list[position])
    }

    override fun getItemCount(): Int = list.size

//    buat interface
    interface OnItemClickCallback{
        fun onItemClicks(data : User)
    }

}