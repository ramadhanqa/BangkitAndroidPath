package com.dicoding.moviecatalogjetpack.ui.home

import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.ActivityScenarioRule
import com.dicoding.moviecatalogjetpack.R
import com.dicoding.moviecatalogjetpack.utils.DataDummy
import org.junit.Assert.*
import org.junit.Rule
import org.junit.Test

class HomeActivityTest{
    private val dummyCourse = DataDummy.generateMovie()

    @get:Rule
    var activityRule = ActivityScenarioRule(HomeActivity::class.java)

//    Skenario Testing Instrumental
//    Menampilkan data Katalog Movie
//        - Memastikan rv_movies dalam keadaan tampil
//        - Gulir rv_movies ke posisi data terakhir
//
//    Menampilkan Data Detail Movie
//        - Memberi tindakan klik pada data pertama di rv_movies
//        - Memastikan TextView untuk tv_title tampil sesuai dengan yang diharapkan.
//        - Memastikan TextView untuk tvGenre tampil sesuai dengan yang diharapkan.
//        - Memastikan TextView untuk tvDeskripsi tampil sesuai dengan yang diharapkan.
//
//    Menampilkan data TvShows
//        - Klik TabLayout dengan teks TvShows
//        - Memastikan rv_tv dalam keadaan tampil.
//        - Gulir rv_tv ke posisi data terakhir.


    @Test
    fun loadMovie() {
        onView(withId(R.id.rv_movies)).check(matches(isDisplayed()))
        onView(withId(R.id.rv_movies)).perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(dummyCourse.size))
    }

    //    load detail Movie
    @Test
    fun loadDetailMovie() {
        onView(withId(R.id.rv_movies)).perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(0, click()))
        onView(withId(R.id.tv_tittle)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_tittle)).check(matches(withText(dummyCourse[0].title)))
        onView(withId(R.id.tvGenre)).check(matches(isDisplayed()))
        onView(withId(R.id.tvGenre)).check(matches(withText(dummyCourse[0].genre)))
        onView(withId(R.id.tvDeskripsi)).check(matches(isDisplayed()))
        onView(withId(R.id.tvDeskripsi)).check(matches(withText(dummyCourse[0].description)))
    }

    //    load recyclerview tv
    @Test
    fun loadTv() {
        onView(withText("Tv Show")).perform(click())
        onView(withId(R.id.rv_tv)).check(matches(isDisplayed()))
        onView(withId(R.id.rv_tv)).perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(dummyCourse.size))
    }

}