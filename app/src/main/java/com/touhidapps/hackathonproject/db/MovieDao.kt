package com.touhidapps.hackathonproject.db

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface MovieDao {

        @Query("SELECT * FROM " + DbConstraints.TABLE_MOVIES + " WHERE id LIKE :id")
        fun getItem(id: Int) : MovieEntity

        @Insert
        fun insertItem(tvShow: MovieEntity)

        @Delete
        fun deleteItem(tvShow: MovieEntity)

}