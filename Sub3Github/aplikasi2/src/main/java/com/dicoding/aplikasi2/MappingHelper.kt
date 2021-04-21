package com.dicoding.aplikasi2

import android.database.Cursor

object MappingHelper {
    fun mapCursorToArrayList(favsCursor: Cursor?): ArrayList<Favorite> {
        val favsList = ArrayList<Favorite>()

        favsCursor?.apply {
            while (moveToNext()) {
                val id = getInt(getColumnIndexOrThrow(DatabaseContract.FavsColumn.ID))
                val login = getString(getColumnIndexOrThrow(DatabaseContract.FavsColumn.LOGIN))
                val avatar_url =
                    getString(getColumnIndexOrThrow(DatabaseContract.FavsColumn.AVATAR_URL))
                favsList.add(Favorite(id, login, avatar_url))
            }
        }
        return favsList
    }
}