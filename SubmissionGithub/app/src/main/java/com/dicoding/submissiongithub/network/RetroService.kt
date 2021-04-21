package com.dicoding.submissiongithub.network

import android.telecom.Call
import com.dicoding.submissiongithub.RecyclerData
import retrofit2.http.GET
import retrofit2.http.Query

interface RetroService {
//users?q=a
    @GET("users")
    fun getDataFromAPI(@Query("q")query: String): Call<RecyclerData>
}