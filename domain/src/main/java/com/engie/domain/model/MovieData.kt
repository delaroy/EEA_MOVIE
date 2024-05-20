package com.engie.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class MovieData(
    val id: Int? = null,
    val posterPath: String? = null,
    val overview: String? = null,
    val releaseDate: String? = null,
    val originalTitle: String? = null,
    val genreIds: List<Int>? = null,
    val mediaType: String? = null,
    val originalLanguage: String? = null,
    val title: String? = null,
    val voteCount: Int? = null,
    val voteAverage: Double? = null,
    val hasVideo: Boolean? = null,
): Parcelable