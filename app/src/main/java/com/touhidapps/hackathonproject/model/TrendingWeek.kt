package com.touhidapps.hackathonproject.model

import com.google.gson.annotations.SerializedName

   
data class TrendingWeek (

   @SerializedName("page") var page : Int,
   @SerializedName("results") var results : List<ResultsTrendingWeek>,
   @SerializedName("total_pages") var totalPages : Int,
   @SerializedName("total_results") var totalResults : Int

)


data class ResultsTrendingWeek (

   @SerializedName("video") var video : Boolean,
   @SerializedName("vote_average") var voteAverage : Double,
   @SerializedName("overview") var overview : String,
   @SerializedName("release_date") var releaseDate : String,
   @SerializedName("vote_count") var voteCount : Int,
   @SerializedName("adult") var adult : Boolean,
   @SerializedName("backdrop_path") var backdropPath : String,
   @SerializedName("title") var title : String,
   @SerializedName("genre_ids") var genreIds : List<Int>,
   @SerializedName("id") var id : Int,
   @SerializedName("original_language") var originalLanguage : String,
   @SerializedName("original_title") var originalTitle : String,
   @SerializedName("poster_path") var posterPath : String,
   @SerializedName("popularity") var popularity : Double,
   @SerializedName("media_type") var mediaType : String

)

