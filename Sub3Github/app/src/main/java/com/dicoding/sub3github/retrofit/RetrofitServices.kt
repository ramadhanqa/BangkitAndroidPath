package com.dicoding.sub3github.retrofit

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitServices {
    private const val BASE_URL = "https://api.github.com/"
    val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
    val api_Instance = retrofit.create(ApiEndpoint::class.java)
}