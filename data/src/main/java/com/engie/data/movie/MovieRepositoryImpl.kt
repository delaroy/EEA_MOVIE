package com.engie.data.movie

import com.engie.data.api.MovieApiService
import com.engie.data.util.CoroutineDispatcherProvider
import com.engie.data.util.safeApiCall
import com.engie.domain.model.MovieData
import com.engie.domain.movie.MovieRepository
import com.engie.domain.util.Resource
import kotlinx.coroutines.withContext

class MovieRepositoryImpl(
    private val api: MovieApiService,
    dispatcher: CoroutineDispatcherProvider,
    private val mapper: MovieMapper
): MovieRepository {

    private val ioDispatcher = dispatcher.io()
    override suspend fun getMovies(apiKey: String, query: String): Resource<List<MovieData>>  =
        withContext(ioDispatcher) {
            val data = safeApiCall {
                api.getMovies(apiKey = apiKey, query = query)
            }

            if (data.isError())
                return@withContext Resource.error(data.message)

            return@withContext Resource.success(data.data?.let { mapper.mapModelList(it.results) })
        }
}