package com.dicoding.moviecatalogjetpack.ui.detail

import androidx.lifecycle.ViewModel
import com.dicoding.moviecatalogjetpack.data.MoviesEntity
import com.dicoding.moviecatalogjetpack.utils.DataDummy

class DetailMovieViewModel : ViewModel() {
    private lateinit var detailsId: String

    private lateinit var movie: MoviesEntity


    fun setSelectedMovie(detailsId: String) {
        this.detailsId = detailsId
    }

    fun getMovie(): MoviesEntity {

        val movieEntities = DataDummy.generateMovie()
        for (movieEntity in movieEntities) {
            if (movieEntity.movieId == detailsId) {
                movie = movieEntity
            }
        }
        return movie
    }


}
