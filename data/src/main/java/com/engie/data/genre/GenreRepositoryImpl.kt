package com.engie.data.genre

import com.engie.data.api.MovieApiService
import com.engie.data.util.CoroutineDispatcherProvider
import com.engie.data.util.safeApiCall
import com.engie.domain.genre.GenreRepository
import com.engie.domain.model.GenreData
import com.engie.domain.util.Resource
import kotlinx.coroutines.withContext

class GenreRepositoryImpl(
    private val api: MovieApiService,
    dispatcher: CoroutineDispatcherProvider,
    private val mapper: GenreMapper
): GenreRepository {

    private val ioDispatcher = dispatcher.io()

    override suspend fun getGenre(apikey: String): Resource<List<GenreData>> =
        withContext(ioDispatcher) {
            val data = safeApiCall {
                api.getGenre(apiKey = apikey)
            }

            if (data.isError())
                return@withContext Resource.error(data.message)

            return@withContext Resource.success(data.data?.let { mapper.mapModelList(it.genres) })
        }
}