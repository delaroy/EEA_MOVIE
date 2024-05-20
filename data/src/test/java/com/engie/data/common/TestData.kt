package com.engie.data.common

import com.engie.data.model.Genre
import com.engie.data.model.GenreResult
import com.engie.data.model.Movie
import com.engie.data.model.SearchResult

object TestData {
    fun createSuccessfulMovieResponse(): SearchResult =
        SearchResult(
            results = arrayListOf(Movie(
                id = 0,
                posterPath = "",
                overview = "",
                releaseDate = "",
                originalTitle = "",
                genreIds = emptyList(),
                mediaType = "",
                originalLanguage = "",
                title = "",
                voteCount = 0,
                voteAverage = 0.0,
                hasVideo = false
            ))
        )

    fun createSuccessfulGenreResponse(): GenreResult =
        GenreResult(
            genres = arrayListOf(
                Genre(
                    id = 0,
                    name = ""
                )
            )
        )
}