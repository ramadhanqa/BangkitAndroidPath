package com.dicoding.sub2github.api

import com.dicoding.sub2github.data.model.DetailUserResponse
import com.dicoding.sub2github.data.model.User
import com.dicoding.sub2github.data.model.UserResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path
import retrofit2.http.Query

interface Api {
    @GET("search/users")
    @Headers("Authorization: token c38c13be53a2296c0c45a07ea8ac5abb9db8def2")
    fun getSearchUsers(
        @Query("q") query: String
    ): Call<UserResponse>

    @GET("users/{username}")
    @Headers("Authorization: token c38c13be53a2296c0c45a07ea8ac5abb9db8def2")
    fun getUserDetail(
        @Path("username") username: String
    ): Call<DetailUserResponse>

    @GET("users/{username}/followers")
    @Headers("Authorization: token c38c13be53a2296c0c45a07ea8ac5abb9db8def2")
    fun getFollowers(
        @Path("username") username: String
    ): Call<ArrayList<User>>

    @GET("users/{username}/following")
    @Headers("Authorization: token c38c13be53a2296c0c45a07ea8ac5abb9db8def2")
    fun getFollowing(
        @Path("username") username: String
    ): Call<ArrayList<User>>
}