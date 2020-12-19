package com.touhidapps.hackathonproject.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase

@Database(entities = [MovieEntity::class, TvShowEntity::class], version = 1)
abstract class ContentDatabase : RoomDatabase() {

    abstract fun movieDao(): MovieDao?
    abstract fun tvShowDao(): TvShowDao?

    companion object {

        private var contentDatabase: ContentDatabase? = null

        fun getInstance(context: Context): ContentDatabase? {
            if (contentDatabase == null) {
                contentDatabase = Room.databaseBuilder(
                    context.applicationContext,
                    ContentDatabase::class.java, "my_movie.db"
                )
                    .addMigrations(MIGRATION_1_2)
//                    .allowMainThreadQueries()
                    .build()
            }
            return contentDatabase
        }

        var MIGRATION_1_2 = object: Migration(0, 1) {
            override fun migrate(database: SupportSQLiteDatabase) {
                //
            }
        }



    }
}