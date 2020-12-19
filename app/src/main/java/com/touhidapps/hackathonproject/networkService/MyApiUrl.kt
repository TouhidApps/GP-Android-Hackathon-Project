package com.touhidapps.hackathonproject.networkService

import com.touhidapps.hackathonproject.utils.toBase64Decode


object MyApiUrl {

    init {
//        System.loadLibrary("native-lib") // এটা App.kt থেকে রিলিংকার দিয়ে লোড করা হয়েছে
    }

    private external fun baseUrlFromJNI()      : String
    private external fun apiKeyFromJNI()      : String

    val API_KEY              = apiKeyFromJNI()
    val BASE_URL             = baseUrlFromJNI().toBase64Decode()

    const val BASE_URL_POSTER      = "https://image.tmdb.org/t/p/w342"

    private const val V1 = "3/"

    const val DISCOVER_MOVIE = V1 + "discover/movie"
    const val DISCOVER_TV = V1 + "discover/tv"
    const val TRENDING_WEEK = V1 + "trending/all/week"



}








