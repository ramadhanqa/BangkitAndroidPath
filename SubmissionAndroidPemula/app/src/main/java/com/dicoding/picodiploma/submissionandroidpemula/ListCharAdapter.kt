package com.dicoding.picodiploma.submissionandroidpemula

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions

class ListCharAdapter(private val listChar:ArrayList<Character>) : RecyclerView.Adapter<ListCharAdapter.ListViewHolder>(){
    class ListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

            var tvName: TextView = itemView.findViewById(R.id.tv_item_name)
            var tvDetail: TextView = itemView.findViewById(R.id.tv_item_detail)

            var imgPhoto: ImageView = itemView.findViewById(R.id.img_item)

    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): ListViewHolder {
        val view:View = LayoutInflater.from(viewGroup.context).inflate(R.layout.item_char,viewGroup,false)
        return ListViewHolder(view)
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val char = listChar[position]
        Glide.with(holder.itemView.context)
            .load(char.photo)
            .apply(RequestOptions().override(55,55))
            .into(holder.imgPhoto)

        holder.tvName.text = char.username
        holder.tvDetail.text = char.name
        holder.itemView.setOnClickListener(){
            val intent = Intent( holder.itemView.context, DetailActivity::class.java)
            intent.putExtra("Bebas", char)
            holder.itemView.context.startActivity(intent)

        }


    }

    override fun getItemCount(): Int {
        return listChar.size
    }


}