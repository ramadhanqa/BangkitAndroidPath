package com.dicoding.moviecatalogjetpack.ui.tvshow

//import com.dicoding.moviecatalogjetpack.ui.movies.
import org.junit.Test

import org.junit.Assert.*
import org.junit.Before

class TvViewModelTest {

    private lateinit var viewModel: TvViewModel
    @Before
    fun setUp() {
        viewModel = TvViewModel()
    }
    @Test
    fun getTv() {
        val tvEntities = viewModel.getTv()
        assertNotNull(tvEntities)
        assertEquals(10, tvEntities.size)
    }
}