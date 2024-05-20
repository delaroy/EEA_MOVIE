package com.engie.eea_tech_interview.ui.movie

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.engie.domain.model.MovieData
import com.engie.domain.model.MovieRequestParam
import com.engie.domain.movie.GetMoviesUseCase
import com.engie.domain.util.Resource
import com.engie.eea_tech_interview.BuildConfig
import com.engie.eea_tech_interview.util.Event
import com.engie.eea_tech_interview.util.LiveEventResource
import com.engie.eea_tech_interview.util.MutableLiveEventResource
import kotlinx.coroutines.launch

class MovieFragmentViewModel(
    private val getMoviesUseCase: GetMoviesUseCase
) : ViewModel() {

    private val _movies = MutableLiveEventResource<List<MovieData>>()
    val movies: LiveEventResource<List<MovieData>> = _movies

    init {
        fetchMovies(search = "James Bond")
    }

    fun fetchMovies(search: String) {
        _movies.value = Event(Resource.loading())
        viewModelScope.launch {
            val movieResult = getMoviesUseCase(param = MovieRequestParam(apiKey = BuildConfig.API_KEY, query = search))

            if (movieResult.isSuccess()) {
                _movies.value = Event(Resource.success(movieResult.data))
            } else {
                _movies.value =
                    Event(Resource.error(message = movieResult.message))
            }
        }
    }
}
