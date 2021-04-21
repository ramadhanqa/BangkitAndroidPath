package com.dicoding.moviecatalogjetpack.ui.detail

import com.dicoding.moviecatalogjetpack.utils.DataDummy
import org.junit.Test

import org.junit.Assert.*
import org.junit.Before

class DetailTvViewModelTest {

    private lateinit var viewModel: DetailTvViewModel
    private val dummyCourse = DataDummy.generateTv()[0]
    private val tvId = dummyCourse.tvId

    @Before
    fun setUp() {
        viewModel = DetailTvViewModel()
        viewModel.setSelectedTvs(tvId)
    }


    @Test
    fun getTvs() {
        viewModel.setSelectedTvs(dummyCourse.tvId)
        val courseEntity = viewModel.getTvs()
        assertNotNull(courseEntity)
        assertEquals(dummyCourse.tvId, courseEntity.tvId)
        assertEquals(dummyCourse.title, courseEntity.title)
        assertEquals(dummyCourse.genre, courseEntity.genre)
        assertEquals(dummyCourse.img_url, courseEntity.img_url)
        assertEquals(dummyCourse.description, courseEntity.description)

    }
}