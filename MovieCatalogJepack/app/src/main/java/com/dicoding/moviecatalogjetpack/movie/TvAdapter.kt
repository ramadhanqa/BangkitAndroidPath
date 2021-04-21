package com.dicoding.tvscatalogjetpack.tvs

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.dicoding.moviecatalogjetpack.R
import com.dicoding.moviecatalogjetpack.data.TvEntity
import com.dicoding.moviecatalogjetpack.databinding.ItemsTvBinding
import com.dicoding.moviecatalogjetpack.ui.detail.DetailTvActivity

class TvAdapter : RecyclerView.Adapter<TvAdapter.TvViewHolder>() {
    private var listTv = ArrayList<TvEntity>()

    fun setTv(tvss: List<TvEntity>?) {
        if (tvss == null) return
        this.listTv.clear()
        this.listTv.addAll(tvss)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TvViewHolder {
        val itemsTvBinding =
            ItemsTvBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return TvViewHolder(itemsTvBinding)
    }

    override fun onBindViewHolder(holder: TvViewHolder, position: Int) {
        val tvs = listTv[position]
        holder.bind(tvs)
    }

    override fun getItemCount(): Int = listTv.size


    class TvViewHolder(private val binding: ItemsTvBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(tvs: TvEntity) {
            with(binding) {
                tvItemTitle.text = tvs.title
                tvItemDate.text = itemView.resources.getString(R.string.deadline_date, tvs.genre)
                itemView.setOnClickListener {
                    val intent = Intent(itemView.context, DetailTvActivity::class.java)
                    intent.putExtra(DetailTvActivity.EXTRA_COURSE, tvs.tvId)
                    itemView.context.startActivity(intent)
                }
                Glide.with(itemView.context)
                    .load(tvs.img_url)
                    .apply(
                        RequestOptions.placeholderOf(R.drawable.ic_loading)
                            .error(R.drawable.ic_error)
                    )
                    .into(imgPoster)

            }
        }
    }
}