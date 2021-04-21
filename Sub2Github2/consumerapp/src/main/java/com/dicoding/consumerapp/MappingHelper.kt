package com.dicoding.consumerapp

import android.database.Cursor

object MappingHelper {
    fun mapCursorToArrayList(cursor:Cursor):ArrayList<User>{
        val list = ArrayList<User>()
        if (cursor!= null){
            while (cursor.moveToNext()){
                val id = cursor.getInt(cursor.getColumnIndexOrThrow(DatabaseContract.FavoriteUserColumns.ID))
                val username = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseContract.FavoriteUserColumns.USERNAME))
                val avatarUrl = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseContract.FavoriteUserColumns.AVATAR_URL))
                list.add(
                    User(
                        username,
                        id,
                        avatarUrl
                    )
                )
            }
        }
        return list
    }
}