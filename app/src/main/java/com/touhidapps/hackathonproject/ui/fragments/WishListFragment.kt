package com.touhidapps.hackathonproject.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.touhidapps.hackathonproject.adapters.MovieAdapter
import com.touhidapps.hackathonproject.adapters.TVAdapter
import com.touhidapps.hackathonproject.databinding.FragmentWishlistBinding
import com.touhidapps.hackathonproject.db.ContentDatabase
import com.touhidapps.hackathonproject.model.ResultsMovie
import com.touhidapps.hackathonproject.model.ResultsTV
import com.touhidapps.hackathonproject.viewModel.VideoViewModel
import java.lang.Exception

class WishListFragment : Fragment() {

    private lateinit var binding: FragmentWishlistBinding

    private lateinit var videoViewModel: VideoViewModel

    private var movies = arrayListOf<ResultsMovie>()
    private var tvs = arrayListOf<ResultsTV>()

    private var movieAdapter = MovieAdapter(movies)
    private var tvAdapter = TVAdapter(tvs)

    private var contentDatabase: ContentDatabase? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentWishlistBinding.inflate(inflater, container, false)
        return binding.root
    } // onCreateView

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        videoViewModel = ViewModelProvider(this).get(VideoViewModel::class.java)

        activity?.let {
            contentDatabase = ContentDatabase.getInstance(it)
        }

        initUI()
        listeners()

        loadData()

    } // onViewCreated

    private fun initUI() {

        binding.rvPopularMovies.adapter = movieAdapter
        binding.rvPopularMovies.layoutManager = LinearLayoutManager(activity, RecyclerView.HORIZONTAL, false)

        binding.rvPopularTv.adapter = tvAdapter
        binding.rvPopularTv.layoutManager = LinearLayoutManager(activity, RecyclerView.HORIZONTAL, false)


    } // initUI

    private fun listeners() {

    }

    private fun loadData() {
        Thread {
            val movEntities = contentDatabase?.movieDao()?.getAllItem()
            val tvEntities = contentDatabase?.tvShowDao()?.getAllItem()

            // To implement in same adapter of home page
            movies.clear()
            tvs.clear()

            movEntities?.forEach {
                if (it != null) {
                    try {
                        movies.add(
                            ResultsMovie(
                                false, "", listOf(), it.contentId?.toInt() ?: 0, "",
                                "", "", 0.0, it.imgPath ?: "", it.releaseDate ?: "",
                                "${it.name}", true, it.vote?.toDouble() ?: 0.0, 0
                            )
                        )
                    } catch (e: Exception) {
                        e.printStackTrace()
                    }
                }

            }

            // To implement in same adapter of home page
            tvEntities?.forEach {
               if (it != null) {
                   try {
                       tvs.add(
                           ResultsTV(
                               "", "", listOf(),
                               it.contentId?.toInt() ?: 0, "", listOf(),
                               "", "", "",
                               0.0, it.imgPath ?: "", it.vote?.toDouble() ?: 0.0,
                               0
                           )
                       )
                   } catch (e: Exception) {
                       e.printStackTrace()
                   }
               }
            }

            activity?.runOnUiThread {
                movieAdapter.notifyDataSetChanged()
                tvAdapter.notifyDataSetChanged()
            }

        }.start()

    } // loadData


}