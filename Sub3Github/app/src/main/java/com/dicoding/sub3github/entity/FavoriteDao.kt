package com.dicoding.sub3github.entity

import android.database.Cursor
import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface FavoriteDao {
    @Insert
    suspend fun addFavorite(favorite:Favorite)

    @Update
    suspend fun updateFavorite(favorite:Favorite)

    @Delete
    suspend fun deleteFavorite(favorite:Favorite)

    @Query("DELETE FROM favorite WHERE favorite.id = :id")
    suspend fun deleteFavoritebyId(id:Int):Int

    @Query("SELECT count(*) FROM favorite WHERE favorite.login = :login")
    suspend fun getFavoritebyId(login:String):String

    @Query("Select * from favorite")
    suspend fun getFavorite(): List<Favorite>

    @Query("Select * from favorite")
    fun getFavoriteall(): Cursor

}