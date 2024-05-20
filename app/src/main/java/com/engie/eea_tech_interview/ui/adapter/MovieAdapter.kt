package com.engie.eea_tech_interview.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.engie.domain.model.MovieData
import com.engie.eea_tech_interview.R
import com.engie.eea_tech_interview.databinding.MovieCardBinding


class MovieAdapter(private val movieDetails: MovieDetailClick) :
    ListAdapter<MovieData, ViewHolder>(DiffCallBack()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            MovieCardBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)
        item.let {
            holder.apply {
                bind(item)
                itemView.tag = item
            }
        }
        val thumbImg = holder.itemView.findViewById(R.id.thumbnail) as ImageView
        thumbImg.transitionName = item.posterPath ?: "/picsum.photos/500"

        holder.itemView.setOnClickListener {
            movieDetails.clickOnItem(
                item, thumbImg
            )
        }

    }
}

class ViewHolder(private val binding: MovieCardBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(item: MovieData) {
        binding.apply {
            model = item
            executePendingBindings()
        }
    }
}

class DiffCallBack : DiffUtil.ItemCallback<MovieData>() {

    override fun areItemsTheSame(oldItem: MovieData, newItem: MovieData): Boolean =
        oldItem.id == newItem.id

    override fun areContentsTheSame(
        oldItem: MovieData,
        newItem: MovieData
    ): Boolean =
        oldItem == newItem
}

interface MovieDetailClick {
    fun clickOnItem(data: MovieData, thumb: ImageView)
}