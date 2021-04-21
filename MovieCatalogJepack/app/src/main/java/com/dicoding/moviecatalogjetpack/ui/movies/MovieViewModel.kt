package com.dicoding.moviecatalogjetpack.ui.movies

import androidx.lifecycle.ViewModel
import com.dicoding.moviecatalogjetpack.data.MoviesEntity
import com.dicoding.moviecatalogjetpack.utils.DataDummy

class MovieViewModel : ViewModel() {
    fun getMovie(): List<MoviesEntity> = DataDummy.generateMovie()
}