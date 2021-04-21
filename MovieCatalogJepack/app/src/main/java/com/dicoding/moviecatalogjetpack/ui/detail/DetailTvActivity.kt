package com.dicoding.moviecatalogjetpack.ui.detail

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.dicoding.moviecatalogjetpack.data.TvEntity
import com.dicoding.moviecatalogjetpack.databinding.ActivityDetailTvBinding

class DetailTvActivity : AppCompatActivity() {
    companion object {
        const val EXTRA_COURSE = "extra_movie"
        const val EXTRA_TV = "extra_movie"
    }

    private lateinit var binding: ActivityDetailTvBinding

    private lateinit var viewModel: DetailTvViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailTvBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val extras = intent.extras
        val tittleTv = extras?.getString(EXTRA_COURSE)
        val tittlee = extras?.getString(EXTRA_TV)
        setTitle(tittleTv)

        viewModel = ViewModelProvider(
            this,
            ViewModelProvider.NewInstanceFactory()
        )[DetailTvViewModel::class.java]

        if (extras != null) {


            if (tittleTv != null) {

                viewModel.setSelectedTvs(tittleTv)
                loadDataTvs(viewModel.getTvs())

            }
        }


    }

    private fun loadDataTvs(tvs: TvEntity) {
        binding.tvTittle.text = tvs.title
        binding.tvGenre.text = tvs.genre
        binding.tvDeskripsi.text = tvs.description
        Glide.with(this)
            .load(tvs.img_url)
            .into(binding.imageView2)
    }
}