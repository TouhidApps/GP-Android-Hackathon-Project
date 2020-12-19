package com.touhidapps.hackathonproject.repository

import android.app.Application
import android.view.View
import android.widget.Toast
import com.touhidapps.hackathonproject.model.DiscoverMovie
import com.touhidapps.hackathonproject.model.DiscoverTV
import com.touhidapps.hackathonproject.model.TrendingWeek
import com.touhidapps.hackathonproject.networkService.RetrofitClient
import com.touhidapps.hackathonproject.utils.invisible
import com.touhidapps.hackathonproject.utils.visible
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class VideoRepository(var application: Application) {

    fun discoverMovie(
        loadingView: View?,
        mobileNo: String,
        releaseYear: String,
        sortBy: String,
        completion: (DiscoverMovie) -> Unit
    ) {
        loadingView?.visible()
        val d = RetrofitClient.instance()?.discoverMovie(mobileNo, releaseYear, sortBy)
            ?.subscribeOn(Schedulers.newThread())
            ?.observeOn(AndroidSchedulers.mainThread())
            ?.subscribe(
                { mData ->
                    loadingView?.invisible()
                    completion(mData)
                },
                {
                    loadingView?.invisible()
                    it.printStackTrace()
                    Toast.makeText(application, "Something wrong.", Toast.LENGTH_SHORT).show()
                }
            )

    } // discoverMovie

    fun discoverTv(
        loadingView: View?,
        mobileNo: String,
        releaseYear: String,
        sortBy: String,
        completion: (DiscoverTV) -> Unit
    ) {
        loadingView?.visible()
        val d = RetrofitClient.instance()?.discoverTv(mobileNo, releaseYear, sortBy)
            ?.subscribeOn(Schedulers.newThread())
            ?.observeOn(AndroidSchedulers.mainThread())
            ?.subscribe(
                { mData ->
                    loadingView?.invisible()
                    completion(mData)
                },
                {
                    loadingView?.invisible()
                    it.printStackTrace()
                    Toast.makeText(application, "Something wrong.", Toast.LENGTH_SHORT).show()
                }
            )

    } // discoverTv

    fun trendingWeek(
        loadingView: View?,
        mobileNo: String,
        completion: (TrendingWeek) -> Unit
    ) {
        loadingView?.visible()
        val d = RetrofitClient.instance()?.trendingWeek(mobileNo)
            ?.subscribeOn(Schedulers.newThread())
            ?.observeOn(AndroidSchedulers.mainThread())
            ?.subscribe(
                { mData ->
                    loadingView?.invisible()
                    completion(mData)
                },
                {
                    loadingView?.invisible()
                    it.printStackTrace()
                    Toast.makeText(application, "Something wrong.", Toast.LENGTH_SHORT).show()
                }
            )

    } // trendingWeek


}