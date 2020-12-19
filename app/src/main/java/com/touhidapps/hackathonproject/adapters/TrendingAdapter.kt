package com.touhidapps.hackathonproject.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.NonNull
import androidx.recyclerview.widget.RecyclerView
import com.touhidapps.hackathonproject.databinding.RowMovieItemBinding
import com.touhidapps.hackathonproject.databinding.RowMovieItemVerticalBinding
import com.touhidapps.hackathonproject.model.ResultsTrendingWeek
import com.touhidapps.hackathonproject.networkService.MyApiUrl
import com.touhidapps.hackathonproject.utils.loadMyImage


class TrendingAdapter(private var items: List<ResultsTrendingWeek>) : RecyclerView.Adapter<TrendingAdapter.MyViewHolder>() {

    var onItemClick: ((ResultsTrendingWeek) -> Unit)? = null

    fun setItemClick(action: (ResultsTrendingWeek) -> Unit) {
        this.onItemClick = action
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding = RowMovieItemVerticalBinding.inflate(LayoutInflater.from(parent.context), parent, false)
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

    inner class MyViewHolder(@NonNull val itemBinding: RowMovieItemVerticalBinding) :
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

