package com.touhidapps.hackathonproject.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey


@Entity(tableName = DbConstraints.TABLE_TV_SHOWS)
class TvShowEntity {

    @PrimaryKey(autoGenerate = true)
    var id = 0

    @ColumnInfo(name = "c_id")
    var contentId: String? = null

    @ColumnInfo(name = "name")
    var name: String? = null

    @ColumnInfo(name = "vote")
    var vote: String? = null

    @ColumnInfo(name = "release_date")
    var releaseDate: String? = null

    @ColumnInfo(name = "img_path")
    var imgPath: String? = null

    constructor() {}

    // To insert
    @Ignore // as multiple constructor is not allowed
    constructor(contentId: String?, name: String?, vote: String?, releaseDate: String?, imgPath: String?) {
        this.contentId = contentId
        this.name = name
        this.vote = vote
        this.releaseDate = releaseDate
        this.imgPath = imgPath
    }

    // To delete
    @Ignore
    constructor(contentId: String) {
        this.contentId = contentId
    }

}