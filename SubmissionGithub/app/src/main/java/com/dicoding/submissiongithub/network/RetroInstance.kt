package com.dicoding.submissiongithub.network

import retrofit.GsonConverterFactory.create
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetroInstance {
    companion object {
        val baseURL = "https://api.github.com/search/"
        fun getRetroInstance(): RetroService {

            val retrofit = Retrofit.Builder()
                .baseUrl(baseURL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()

            return retrofit.create(RetroService::class.java)
        }
    }
}