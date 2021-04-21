package com.dicoding.moviecatalogjetpack.ui.tvshow

import androidx.lifecycle.ViewModel
import com.dicoding.moviecatalogjetpack.data.MoviesEntity
import com.dicoding.moviecatalogjetpack.data.TvEntity
import com.dicoding.moviecatalogjetpack.utils.DataDummy

class TvViewModel : ViewModel() {
    fun getTv(): List<TvEntity> = DataDummy.generateTv()
}