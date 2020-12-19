package com.touhidapps.hackathonproject.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.NonNull
import androidx.recyclerview.widget.RecyclerView
import com.touhidapps.hackathonproject.databinding.RowMovieItemBinding
import com.touhidapps.hackathonproject.model.ResultsTV
import com.touhidapps.hackathonproject.networkService.MyApiUrl
import com.touhidapps.hackathonproject.utils.loadMyImage


class TVAdapter(private var items: List<ResultsTV>) : RecyclerView.Adapter<TVAdapter.MyViewHolder>() {

    var onItemClick: ((ResultsTV) -> Unit)? = null

    fun setItemClick(action: (ResultsTV) -> Unit) {
        this.onItemClick = action
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding = RowMovieItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {

        val item = items[position]

        holder.itemBinding.tvTitle.text = item.name
        holder.itemBinding.tvVote.text = "Vote: ${item.voteAverage}"
        holder.itemBinding.tvDate.text = "Released: ${item.firstAirDate}"

        holder.itemBinding.ivThumb.loadMyImage("${MyApiUrl.BASE_URL_POSTER}${item.posterPath}", true)

    } // onBindViewHolder

    override fun getItemCount(): Int {
        return items.size
    }

    inner class MyViewHolder(@NonNull val itemBinding: RowMovieItemBinding) :
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

