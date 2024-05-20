package com.engie.data.api

import com.engie.data.model.GenreResult
import com.engie.data.model.SearchResult
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieApiService {
    @GET("search/movie")
    suspend fun getMovies(
        @Query("api_key") apiKey: String,
        @Query("query") query: String,
    ): Response<SearchResult>

    @GET("genre/movie/list")
    suspend fun getGenre(
        @Query("api_key") apiKey: String
    ): Response<GenreResult>
}