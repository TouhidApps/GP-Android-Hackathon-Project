package com.touhidapps.hackathonproject.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.NonNull
import androidx.recyclerview.widget.RecyclerView
import coil.api.load
import com.touhidapps.hackathonproject.databinding.RowMovieItemHorizontalBinding
import com.touhidapps.hackathonproject.model.ResultsMovie
import com.touhidapps.hackathonproject.networkService.MyApiUrl
import com.touhidapps.hackathonproject.utils.loadMyImage


class MovieAdapter(private var items: List<ResultsMovie>) : RecyclerView.Adapter<MovieAdapter.MyViewHolder>() {

    var onItemClick: ((ResultsMovie) -> Unit)? = null

    fun setItemClick(action: (ResultsMovie) -> Unit) {
        this.onItemClick = action
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding = RowMovieItemHorizontalBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {

        val item = items[position]

        holder.itemBinding.tvTitle.text = item.title
        holder.itemBinding.tvVote.text = "Vote: ${item.voteAverage}"
        holder.itemBinding.tvDate.text = "Released: ${item.releaseDate}"

        holder.itemBinding.ivThumb.loadMyImage("${MyApiUrl.BASE_URL_POSTER}${item.posterPath}", true)

    } // onBindViewHolder

    override fun getItemCount(): Int {
        return items.size
    }

    inner class MyViewHolder(@NonNull val itemBinding: RowMovieItemHorizontalBinding) :
        RecyclerView.ViewHolder(itemBinding.root) {

        init {

            onItemClick?.let {
                itemBinding.root.setOnClickListener {
                    it(items[adapterPosition])
                }
            }

        }
    }

}

