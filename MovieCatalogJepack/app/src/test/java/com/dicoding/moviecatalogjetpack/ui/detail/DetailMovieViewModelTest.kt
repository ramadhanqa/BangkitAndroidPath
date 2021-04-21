package com.dicoding.moviecatalogjetpack.ui.detail

import com.dicoding.moviecatalogjetpack.utils.DataDummy
import org.junit.Test

import org.junit.Assert.*
import org.junit.Before

class DetailMovieViewModelTest {

    private lateinit var viewModel: DetailMovieViewModel
    private val dummyCourse = DataDummy.generateMovie()[0]
    private val movieId = dummyCourse.movieId

    @Before
    fun setUp() {
        viewModel = DetailMovieViewModel()
        viewModel.setSelectedMovie(movieId)
    }


    @Test
    fun getMovie() {
        viewModel.setSelectedMovie(dummyCourse.movieId)
        val courseEntity = viewModel.getMovie()
        assertNotNull(courseEntity)
        assertEquals(dummyCourse.movieId, courseEntity.movieId)
        assertEquals(dummyCourse.title, courseEntity.title)
        assertEquals(dummyCourse.genre, courseEntity.genre)
        assertEquals(dummyCourse.img_url, courseEntity.img_url)
        assertEquals(dummyCourse.description, courseEntity.description)
    }
}