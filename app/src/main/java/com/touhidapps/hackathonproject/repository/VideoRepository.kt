package com.touhidapps.hackathonproject.repository

import android.app.Application
import android.view.View
import android.widget.Toast
import com.touhidapps.hackathonproject.model.*
import com.touhidapps.hackathonproject.networkService.RetrofitClient
import com.touhidapps.hackathonproject.utils.invisible
import com.touhidapps.hackathonproject.utils.visible
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class VideoRepository(var application: Application) {

    fun discoverMovie(
        loadingView: View?,
        key: String,
        releaseYear: String,
        sortBy: String,
        completion: (DiscoverMovie) -> Unit
    ) {
        loadingView?.visible()
        val d = RetrofitClient.instance()?.discoverMovie(key, releaseYear, sortBy)
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
        key: String,
        releaseYear: String,
        sortBy: String,
        completion: (DiscoverTV) -> Unit
    ) {
        loadingView?.visible()
        val d = RetrofitClient.instance()?.discoverTv(key, releaseYear, sortBy)
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
        key: String,
        completion: (TrendingWeek) -> Unit
    ) {
        loadingView?.visible()
        val d = RetrofitClient.instance()?.trendingWeek(key)
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

    fun movieDetails(
        loadingView: View?,
        id: Int,
        key: String,
        completion: (MovieDetailModel) -> Unit
    ) {
        loadingView?.visible()
        val d = RetrofitClient.instance()?.movieDetails(id, key)
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

    } // movieDetails

    fun tvDetails(
        loadingView: View?,
        id: Int,
        key: String,
        completion: (TVDetailModel) -> Unit
    ) {
        loadingView?.visible()
        val d = RetrofitClient.instance()?.tvDetails(id, key)
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

    } // tvDetails


}