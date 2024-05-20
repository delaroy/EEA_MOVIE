package com.engie.eea_tech_interview.movie

import com.engie.domain.model.MovieData
import com.engie.domain.movie.MovieRepository
import com.engie.domain.util.Resource


class FakeMovieRepository : MovieRepository {
    private lateinit var movieList: List<MovieData>

    override suspend fun getMovies(apiKey: String, query: String): Resource<List<MovieData>> {
        return Resource.success(mockData())
    }

    private fun mockData(): List<MovieData> {
        this.movieList = emptyList()
        val mockList: MutableList<MovieData> = mutableListOf()
        mockList.add(
            MovieData(
                0,
                "/yriuu",
                "Good"
            )
        )

        mockList.add(
            MovieData(
                0,
                "/yriuu",
                "Good"
            )
        )

        movieList = mockList.toList()
        return movieList
    }

}
