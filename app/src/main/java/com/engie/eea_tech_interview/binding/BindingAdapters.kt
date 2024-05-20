package com.engie.eea_tech_interview.binding

import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.engie.eea_tech_interview.R
import java.util.*


@BindingAdapter("movieImage")
fun bindMovieImage(imageView: ImageView, path: String?) {
    path?.let {
        val poster = "https://image.tmdb.org/t/p/w500$it"

        Glide.with(imageView.context)
            .load(poster)
            .placeholder(R.drawable.load)
            .transition(DrawableTransitionOptions.withCrossFade())
            .into(imageView)
    }
}


@BindingAdapter("movieTitle")
fun bindMovieTitle(textView: TextView, title: String?) {
    title?.let {
        textView.text = it
    }
}

@BindingAdapter("movieDate")
fun bindMovieDate(textView: TextView, title: String?) {
    title?.let {
        textView.text = it
    }
}



