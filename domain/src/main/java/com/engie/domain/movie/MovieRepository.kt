package com.engie.domain.movie

import com.engie.domain.model.MovieData
import com.engie.domain.util.Resource

interface MovieRepository {

    suspend fun getMovies(apiKey: String, query: String): Resource<List<MovieData>>

}