package com.touhidapps.hackathonproject.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.touhidapps.hackathonproject.R
import com.touhidapps.hackathonproject.adapters.MovieAdapter
import com.touhidapps.hackathonproject.adapters.TVAdapter
import com.touhidapps.hackathonproject.adapters.TrendingAdapter
import com.touhidapps.hackathonproject.databinding.ActivityMainBinding
import com.touhidapps.hackathonproject.model.ResultsMovie
import com.touhidapps.hackathonproject.model.ResultsTV
import com.touhidapps.hackathonproject.model.ResultsTrendingWeek
import com.touhidapps.hackathonproject.networkService.MyApiUrl
import com.touhidapps.hackathonproject.ui.fragments.DetailViewFragment
import com.touhidapps.hackathonproject.utils.MyDataType
import com.touhidapps.hackathonproject.viewModel.VideoViewModel


class MainActivity : AppCompatActivity() {

    private val TAG = "MainActivity"

    private lateinit var binding: ActivityMainBinding

    private lateinit var videoViewModel: VideoViewModel

    private lateinit var detailViewFragment: DetailViewFragment

    private var movies = arrayListOf<ResultsMovie>()
    private var tvs = arrayListOf<ResultsTV>()
    private var trendingWeeks = arrayListOf<ResultsTrendingWeek>()

    private var movieAdapter = MovieAdapter(movies)
    private var tvAdapter = TVAdapter(tvs)
    private var trendingAdapter = TrendingAdapter(trendingWeeks)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        videoViewModel = ViewModelProvider(this).get(VideoViewModel::class.java)

        detailViewFragment = DetailViewFragment()

        initUI()

        listeners()

        observers()

        loadData()

    } // onCreate

    private fun initUI() {

        binding.rvPopularMovies.adapter = movieAdapter
        binding.rvPopularMovies.layoutManager = LinearLayoutManager(this, RecyclerView.HORIZONTAL, false)

        binding.rvPopularTv.adapter = tvAdapter
        binding.rvPopularTv.layoutManager = LinearLayoutManager(this, RecyclerView.HORIZONTAL, false)

        binding.rvTrendingWeek.adapter = trendingAdapter
        binding.rvTrendingWeek.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)

    } // initUI

    private fun listeners() {

        movieAdapter.setItemClick {

            detailViewFragment.myDataType = MyDataType.MOVIES
            detailViewFragment.contentId = it.id

            supportFragmentManager
                .beginTransaction()
                .addToBackStack("detailViewFragment")
                .setCustomAnimations(
                    R.anim.enter,
                    R.anim.exit,
                    R.anim.pop_enter,
                    R.anim.pop_exit
                )
                .add(
                    R.id.frameLayout,
                    detailViewFragment
                )
                .commit()

        }

        tvAdapter.setItemClick {

            detailViewFragment.myDataType = MyDataType.TV_SERIES
            detailViewFragment.contentId = it.id

            supportFragmentManager
                .beginTransaction()
                .addToBackStack("detailViewFragment")
                .setCustomAnimations(
                    R.anim.enter,
                    R.anim.exit,
                    R.anim.pop_enter,
                    R.anim.pop_exit
                )
                .add(
                    R.id.frameLayout,
                    detailViewFragment
                )
                .commit()

        }

        trendingAdapter.setItemClick {
            Toast.makeText(this, "No event", Toast.LENGTH_SHORT).show()
        }

    } // listeners

    private fun observers() {

        videoViewModel.discoverMovie.observeForever {

            movies.clear()
            movies.addAll(it.results)
            movieAdapter.notifyDataSetChanged()

        }

        videoViewModel.discoverTv.observeForever {

            tvs.clear()
            tvs.addAll(it.results)
            tvAdapter.notifyDataSetChanged()

        }

        videoViewModel.trendingWeek.observeForever {

            trendingWeeks.clear()
            trendingWeeks.addAll(it.results)
            trendingAdapter.notifyDataSetChanged()

        }

    } // observers


    private fun loadData() {

        videoViewModel.discoverMovie(binding.spinKit, MyApiUrl.API_KEY, "2020", "vote_average.desc")
        videoViewModel.discoverTv(binding.spinKit, MyApiUrl.API_KEY, "2020", "vote_average.desc")
        videoViewModel.trendingWeek(binding.spinKit, MyApiUrl.API_KEY)

    } // loadData







}
