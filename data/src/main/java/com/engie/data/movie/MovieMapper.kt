package com.engie.data.movie

import com.engie.data.model.Movie
import com.engie.data.util.Mapper
import com.engie.domain.model.MovieData

class MovieMapper :
    Mapper<Movie, MovieData> {
    override fun to(domain: MovieData): Movie =
        domain.run {
            Movie(
                id = id,
                posterPath = posterPath,
                overview = overview,
                releaseDate = releaseDate,
                originalTitle = originalTitle,
                genreIds = genreIds,
                mediaType = mediaType,
                originalLanguage = originalLanguage,
                title = title,
                voteCount = voteCount,
                voteAverage = voteAverage,
                hasVideo = hasVideo
            )
        }

    override fun from(entity: Movie): MovieData =
        entity.run {
            MovieData(
                id = id,
                posterPath = posterPath,
                overview = overview,
                releaseDate = releaseDate,
                originalTitle = originalTitle,
                genreIds = genreIds,
                mediaType = mediaType,
                originalLanguage = originalLanguage,
                title = title,
                voteCount = voteCount,
                voteAverage = voteAverage,
                hasVideo = hasVideo
            )
        }

}