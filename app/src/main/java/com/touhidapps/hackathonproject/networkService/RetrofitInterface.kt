package com.touhidapps.hackathonproject.networkService

import com.touhidapps.hackathonproject.model.*
import io.reactivex.Single
import retrofit2.http.*

interface RetrofitInterface {

    @GET(MyApiUrl.DISCOVER_MOVIE)
    fun discoverMovie(
        @Query("api_key") apiKey: String,
        @Query("primary_release_year") releaseYear: String,
        @Query("sort_by") sortBy: String
    ): Single<DiscoverMovie>

    @GET(MyApiUrl.DISCOVER_TV)
    fun discoverTv(
        @Query("api_key") apiKey: String,
        @Query("primary_release_year") releaseYear: String,
        @Query("sort_by") sortBy: String
    ): Single<DiscoverTV>

    @GET(MyApiUrl.TRENDING_WEEK)
    fun trendingWeek(
        @Query("api_key") apiKey: String
    ): Single<TrendingWeek>

    @GET(MyApiUrl.MOVIE_DETAILS + "/{id}")
    fun movieDetails(
        @Path("id") id: Int,
        @Query("api_key") apiKey: String
    ): Single<MovieDetailModel>

    @GET(MyApiUrl.TV_DETAILS + "/{id}")
    fun tvDetails(
        @Path("id") id: Int,
        @Query("api_key") apiKey: String
    ): Single<TVDetailModel>







}













