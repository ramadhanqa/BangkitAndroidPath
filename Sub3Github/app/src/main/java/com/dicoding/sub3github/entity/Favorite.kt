package com.dicoding.sub3github.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Favorite(
    @PrimaryKey()
    val id : Int,
    val login : String,
    val avatar_url : String
)
