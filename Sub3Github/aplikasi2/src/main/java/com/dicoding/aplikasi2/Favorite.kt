package com.dicoding.aplikasi2

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
class Favorite(
    val id: Int? = 0,
    val login: String? = null,
    val avatar_url: String? = null
) : Parcelable
