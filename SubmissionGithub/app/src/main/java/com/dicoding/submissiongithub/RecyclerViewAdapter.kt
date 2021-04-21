package com.dicoding.submissiongithub

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class RecyclerViewAdapter : RecyclerView.Adapter<RecyclerViewAdapter.MyViewHolder>() {

    val items = ArrayList<String>()

    fun setListData(data: RecyclerData){
        this.items
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerViewAdapter.MyViewHolder {
        val inflater = LayoutInflater.from(parent.context).inflate(R.layout.item_row_user,parent,false)
        return  MyViewHolder(inflater)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount(): Int {
        return items.size
    }

    class MyViewHolder(view: View): RecyclerView.ViewHolder(view){

        var tvTitle: TextView = itemView.findViewById(R.id.tvTitle)


        fun  bind(tittle: String){

            tvTitle.text = tittle

        }
    }

}
