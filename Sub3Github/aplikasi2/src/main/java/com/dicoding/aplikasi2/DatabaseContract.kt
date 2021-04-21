package com.dicoding.aplikasi2

import android.net.Uri
import android.provider.BaseColumns

object DatabaseContract {
    const val AUTHORITY = "com.dicoding.sub3github"
    const val SCHEME = "content"

    class FavsColumn : BaseColumns {

        companion object {
            const val TABLE_NAME = "favorite_user"
            const val ID = "id"
            const val LOGIN = "login"
            const val AVATAR_URL = "avatar_url"

            val CONTENT_URI: Uri = Uri.Builder().scheme(SCHEME)
                .authority(AUTHORITY)
                .appendPath(TABLE_NAME)
                .build()
        }

    }
}