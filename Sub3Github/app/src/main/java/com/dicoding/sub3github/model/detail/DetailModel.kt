package com.dicoding.sub3github.model.detail

data class DetailModel(
    val login : String,
    val id : Int,
    val avatar_url : String ,
    val followers_url : String,
    val following_url : String,
    val name : String,
    val followers : Int,
    val following : Int,
    val public_repos : Int
)
