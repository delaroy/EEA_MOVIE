package com.engie.domain.movie

import com.engie.domain.model.MovieData
import com.engie.domain.model.MovieRequestParam
import com.engie.domain.util.Resource
import com.engie.domain.util.SuspendUseCase

class GetMoviesUseCase(private val movieRepository: MovieRepository) :
    SuspendUseCase<MovieRequestParam, Resource<List<MovieData>>>() {

    override suspend fun invoke(param: MovieRequestParam): Resource<List<MovieData>> =
        movieRepository.getMovies(apiKey = param.apiKey, query = param.query)
}