package com.dicoding.moviecatalogjetpack.ui.detail


import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.dicoding.moviecatalogjetpack.data.MoviesEntity
import com.dicoding.moviecatalogjetpack.databinding.ActivityDetailMovieBinding

class DetailMovieActivity : AppCompatActivity() {


    companion object {
        const val EXTRA_COURSE = "extra_movie"
        const val EXTRA_TV = "extra_tv"
    }

    private lateinit var binding: ActivityDetailMovieBinding

    private lateinit var viewModel: DetailMovieViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityDetailMovieBinding.inflate(layoutInflater)

        setContentView(binding.root)

        val extras = intent.extras
        val tittleMovie = extras?.getString(EXTRA_COURSE)
        val tittlee = extras?.getString(DetailTvActivity.EXTRA_TV)
        setTitle(tittlee)


        viewModel = ViewModelProvider(
            this,
            ViewModelProvider.NewInstanceFactory()
        )[DetailMovieViewModel::class.java]


        if (extras != null) {
            if (tittleMovie != null) {
                viewModel.setSelectedMovie(tittleMovie)
                loadDataMovie(viewModel.getMovie())
            }
        }

    }


    private fun loadDataMovie(movieData: MoviesEntity) {
        binding.tvTittle.text = movieData.title
        binding.tvGenre.text = movieData.genre
        binding.tvDeskripsi.text = movieData.description
        Glide.with(this)
            .load(movieData.img_url)
            .into(binding.imageView2)
    }
}