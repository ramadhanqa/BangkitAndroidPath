package com.dicoding.sub3github.retrofit

import com.dicoding.sub3github.model.detail.DetailModel
import com.dicoding.sub3github.model.user.User
import com.dicoding.sub3github.model.user.UserModel
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiEndpoint {
//    karena datanya berupa objek
    @GET("search/users")
    @Headers("Authorization: token c38c13be53a2296c0c45a07ea8ac5abb9db8def2")
    fun getSearchs(
        @Query("q")query: String
    ):Call<UserModel>
//karena datanya berupa objek
    @GET("users/{username}")
    @Headers("Authorization: token c38c13be53a2296c0c45a07ea8ac5abb9db8def2")
    fun getDetails(
        @Path("username")username : String
    ): Call<DetailModel>

//    karna datanya berupa array
    @GET("users/{username}/followers")
    @Headers("Authorization: token c38c13be53a2296c0c45a07ea8ac5abb9db8def2")
    fun getFollowers(
        @Path("username")username: String
    ): Call<ArrayList<User>>

//    karna datanya berupa array
    @GET("users/{username}/following")
    @Headers("Authorization: token c38c13be53a2296c0c45a07ea8ac5abb9db8def2")
    fun getFollowing(
        @Path("username")username: String
    ): Call<ArrayList<User>>
}