package com.dicoding.moviecatalogjetpack.ui.detail

import androidx.lifecycle.ViewModel
import com.dicoding.moviecatalogjetpack.data.TvEntity
import com.dicoding.moviecatalogjetpack.utils.DataDummy

class DetailTvViewModel : ViewModel() {
    private lateinit var detailsId: String

    private lateinit var tvs: TvEntity


    fun setSelectedTvs(detailsId: String) {
        this.detailsId = detailsId
    }


    fun getTvs(): TvEntity {

        val tvEntities = DataDummy.generateTv()
        for (tvEntity in tvEntities) {
            if (tvEntity.tvId == detailsId) {
                tvs = tvEntity
            }
        }
        return tvs
    }

}
