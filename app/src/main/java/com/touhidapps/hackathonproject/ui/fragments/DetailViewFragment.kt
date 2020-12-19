package com.touhidapps.hackathonproject.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.touhidapps.hackathonproject.databinding.FragmentDetailViewBinding
import com.touhidapps.hackathonproject.db.ContentDatabase
import com.touhidapps.hackathonproject.db.MovieEntity
import com.touhidapps.hackathonproject.db.TvShowEntity
import com.touhidapps.hackathonproject.model.MovieDetailModel
import com.touhidapps.hackathonproject.model.TVDetailModel
import com.touhidapps.hackathonproject.networkService.MyApiUrl
import com.touhidapps.hackathonproject.utils.MyDataType
import com.touhidapps.hackathonproject.utils.loadMyImage
import com.touhidapps.hackathonproject.viewModel.VideoViewModel

class DetailViewFragment : Fragment() {

    private lateinit var binding: FragmentDetailViewBinding

    private lateinit var videoViewModel: VideoViewModel

    var myDataType = MyDataType.MOVIES
    var contentId: Int = 0

    private var isWishListed: Boolean = false

    private var contentDatabase: ContentDatabase? = null
    private var movieDetailModel: MovieDetailModel? = null
    private var tVDetailModel: TVDetailModel? = null

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

        activity?.let {
            contentDatabase = ContentDatabase.getInstance(it)
        }

        initUI()
        listeners()
        observers()

        loadData()

    } // onViewCreated

    private fun initUI() {


        if (myDataType == MyDataType.MOVIES) {
            binding.tvMainTitle.text = "Movie Detail"
        } else {
            binding.tvMainTitle.text = "TV Series Detail"
        }


    } // initUI


    private fun listeners() {

        binding.btnWishList.setOnClickListener {

            if (isWishListed) {
                // Add to Wishlist
                binding.btnWishList.text = "Add To Wishlist"
                if (myDataType == MyDataType.MOVIES) {
                    movieDetailModel?.id?.let {
                        Thread {
                            contentDatabase?.movieDao()?.deleteItem(it)
                        }.start()
                    }
                } else {
                    tVDetailModel?.id?.let {
                        Thread {
                            contentDatabase?.tvShowDao()?.deleteItem(it)
                        }.start()
                    }
                }

            } else {
                // Remove from Wishlist
                binding.btnWishList.text = "Remove From Wishlist"
                if (myDataType == MyDataType.MOVIES) {
                    Thread {
                        contentDatabase?.movieDao()?.insertItem(
                            MovieEntity(
                                contentId = "${movieDetailModel?.id}",
                                name = "${movieDetailModel?.title}",
                                vote = "${movieDetailModel?.voteAverage}",
                                releaseDate = "${movieDetailModel?.releaseDate}",
                                imgPath = "${movieDetailModel?.posterPath}"
                            )
                        )
                    }.start()

                } else {
                    Thread {
                        contentDatabase?.tvShowDao()?.insertItem(
                            TvShowEntity(
                                contentId = "${tVDetailModel?.id}",
                                name = "${tVDetailModel?.name}",
                                vote = "${tVDetailModel?.voteAverage}",
                                releaseDate = "${tVDetailModel?.firstAirDate}",
                                imgPath = "${tVDetailModel?.posterPath}"
                            )
                        )
                    }.start()
                }

            }

        }


    } // listeners

    private fun observers() {

        videoViewModel.movieDetailModel.observeForever {

            this.movieDetailModel = it

            binding.ivThumb.loadMyImage("${MyApiUrl.BASE_URL_POSTER}${it.posterPath}", true)
            binding.tvName.text = "Name: ${it.title}"
            binding.tvVote.text = "Vote: ${it.voteAverage}"
            binding.tvReleaseDate.text = "Release Date: ${it.releaseDate}"

            checkForLabel()

        }

        videoViewModel.tVDetailModel.observeForever {

            this.tVDetailModel = it

            binding.ivThumb.loadMyImage("${MyApiUrl.BASE_URL_POSTER}${it.posterPath}", true)
            binding.tvName.text = "Name: ${it.name}"
            binding.tvVote.text = "Vote: ${it.voteAverage}"
            binding.tvReleaseDate.text = "${it.firstAirDate}"

            checkForLabel()

        }

    } // observers


    //
    private fun checkForLabel() {
        if (myDataType == MyDataType.MOVIES) {
            movieDetailModel?.id?.let {
                Thread {
                    val movEnt = contentDatabase?.movieDao()?.getItem(it)
                    isWishListed = movEnt != null
                    labelChangeOfWishListed()
                }.start()

            }
        } else {
            tVDetailModel?.id?.let {
                Thread {
                    val tvEnt = contentDatabase?.tvShowDao()?.getItem("${it}")
                    isWishListed = tvEnt != null
                    labelChangeOfWishListed()
                }.start()
            }
        }
    }

    private fun labelChangeOfWishListed() {
        activity?.runOnUiThread {
            if (isWishListed) {
                binding.btnWishList.text = "Remove From Wishlist"
            } else {
                binding.btnWishList.text = "Add To Wishlist"
            }
        }
    }

    private fun loadData() {

        if (myDataType == MyDataType.MOVIES) {
            videoViewModel.movieDetails(binding.spinKit, contentId, MyApiUrl.API_KEY)
        } else if (myDataType == MyDataType.TV_SERIES) {
            videoViewModel.tvDetails(binding.spinKit, contentId, MyApiUrl.API_KEY)
        }

    } // loadData


}