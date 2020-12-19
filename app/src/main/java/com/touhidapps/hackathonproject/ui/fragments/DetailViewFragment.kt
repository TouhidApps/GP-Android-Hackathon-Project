package com.touhidapps.hackathonproject.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.touhidapps.hackathonproject.databinding.FragmentDetailViewBinding
import com.touhidapps.hackathonproject.networkService.MyApiUrl
import com.touhidapps.hackathonproject.utils.MyDataType
import com.touhidapps.hackathonproject.utils.loadMyImage
import com.touhidapps.hackathonproject.viewModel.VideoViewModel

class DetailViewFragment : Fragment() {

    private lateinit var binding: FragmentDetailViewBinding

    private lateinit var videoViewModel: VideoViewModel

    var myDataType = MyDataType.MOVIES
    var contentId : Int = 0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDetailViewBinding.inflate(inflater, container, false)
        return binding.root
    } // onCreateView

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        videoViewModel = ViewModelProvider(this).get(VideoViewModel::class.java)

        initUI()
        listeners()
        observers()

        loadData()

    } // onViewCreated

    private fun initUI() {



    } // initUI

    private fun listeners() {



    } // listeners

    private fun observers() {

        videoViewModel.movieDetailModel.observeForever {

            binding.ivThumb.loadMyImage("${MyApiUrl.BASE_URL_POSTER}${it.posterPath}", true)
            binding.tvName.text = "Name: ${it.title}"
            binding.tvVote.text = "Vote: ${it.voteAverage}"
            binding.tvReleaseDate.text = "Release Date: ${it.releaseDate}"

        }

        videoViewModel.tVDetailModel.observeForever {

            binding.ivThumb.loadMyImage("${MyApiUrl.BASE_URL_POSTER}${it.posterPath}", true)
            binding.tvName.text = "Name: ${it.name}"
            binding.tvVote.text = "Vote: ${it.voteAverage}"
            binding.tvReleaseDate.text = "${it.firstAirDate}"

        }

    } // observers

    private fun loadData() {

        if (myDataType ==  MyDataType.MOVIES) {
            videoViewModel.movieDetails(binding.spinKit, contentId, MyApiUrl.API_KEY)
        } else if (myDataType ==  MyDataType.TV_SERIES) {
            videoViewModel.tvDetails(binding.spinKit, contentId, MyApiUrl.API_KEY)
        }

    } // loadData


}