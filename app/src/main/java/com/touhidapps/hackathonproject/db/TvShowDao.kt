package com.touhidapps.hackathonproject.db

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface TvShowDao {

        @Query("SELECT * FROM " + DbConstraints.TABLE_TV_SHOWS)
        fun getAllItem() : List<TvShowEntity>

        @Query("SELECT * FROM " + DbConstraints.TABLE_TV_SHOWS + " WHERE c_id=:id")
        fun getItem(id: String) : TvShowEntity

        @Insert
        fun insertItem(tvShow: TvShowEntity)

//        @Delete
        @Query("DELETE FROM " + DbConstraints.TABLE_TV_SHOWS + " WHERE c_id=:id")
        fun deleteItem(id: Int)

}


