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

//    load recyclerview Movie
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