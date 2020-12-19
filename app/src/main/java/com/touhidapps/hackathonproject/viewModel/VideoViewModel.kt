package com.touhidapps.hackathonproject.viewModel

import android.app.Application
import android.view.View
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.touhidapps.hackathonproject.model.DiscoverMovie
import com.touhidapps.hackathonproject.model.DiscoverTV
import com.touhidapps.hackathonproject.model.TrendingWeek
import com.touhidapps.hackathonproject.repository.VideoRepository

class VideoViewModel(application: Application) : AndroidViewModel(application) {

    private var videoRepository = VideoRepository(application)

    var discoverMovie = MutableLiveData<DiscoverMovie>()
    var discoverTv = MutableLiveData<DiscoverTV>()
    var trendingWeek = MutableLiveData<TrendingWeek>()

    fun discoverMovie(loadingView: View?, apiKey: String, releaseYear: String, sortBy: String) {

        videoRepository.discoverMovie(loadingView, apiKey, releaseYear, sortBy) {
            discoverMovie.value = it
        }

    } // discoverMovie

    fun discoverTv(loadingView: View?, apiKey: String, releaseYear: String, sortBy: String) {

        videoRepository.discoverTv(loadingView, apiKey, releaseYear, sortBy) {
            discoverTv.value = it
        }

    } // discoverTv

    fun trendingWeek(loadingView: View?, apiKey: String) {

        videoRepository.trendingWeek(loadingView, apiKey) {
            trendingWeek.value = it
        }

    } // trendingWeek


}