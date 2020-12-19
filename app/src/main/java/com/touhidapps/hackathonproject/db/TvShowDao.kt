package com.touhidapps.hackathonproject.db

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface TvShowDao {

        @Query("SELECT * FROM " + DbConstraints.TABLE_TV_SHOWS + " WHERE id LIKE :id")
        fun getItem(id: Int) : TvShowEntity

        @Insert
        fun insertItem(tvShow: TvShowEntity)

        @Delete
        fun deleteItem(tvShow: TvShowEntity)
}