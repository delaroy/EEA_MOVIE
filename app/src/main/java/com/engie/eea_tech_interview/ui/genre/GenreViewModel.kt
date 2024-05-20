package com.engie.eea_tech_interview.ui.genre

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.engie.domain.genre.GetGenreUseCase
import com.engie.domain.model.GenreData
import com.engie.domain.util.Resource
import com.engie.eea_tech_interview.BuildConfig
import com.engie.eea_tech_interview.util.Event
import com.engie.eea_tech_interview.util.LiveEventResource
import com.engie.eea_tech_interview.util.MutableLiveEventResource
import kotlinx.coroutines.launch

class GenreViewModel(
    private val getGenreUseCase: GetGenreUseCase
) : ViewModel() {

    private val _genre = MutableLiveEventResource<List<GenreData>>()
    val genre: LiveEventResource<List<GenreData>> = _genre

    fun fetchGenre() {
        _genre.value = Event(Resource.loading())
        viewModelScope.launch {
            val genreResult = getGenreUseCase(param = BuildConfig.API_KEY)

            if (genreResult.isSuccess()) {
                _genre.value = Event(Resource.success(genreResult.data))
            } else {
                _genre.value =
                    Event(Resource.error(message = genreResult.message))
            }
        }
    }
}