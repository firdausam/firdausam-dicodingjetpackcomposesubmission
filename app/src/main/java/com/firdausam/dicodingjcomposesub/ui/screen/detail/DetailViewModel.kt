package com.firdausam.dicodingjcomposesub.ui.screen.detail

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.firdausam.dicodingjcomposesub.domain.model.DetailAnime
import com.firdausam.dicodingjcomposesub.domain.repository.AnimeRepository
import com.firdausam.dicodingjcomposesub.domain.util.Resource
import com.firdausam.dicodingjcomposesub.ui.state.AnimeDetailState
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class DetailViewModel(
    private val repository: AnimeRepository,
    savedStateHandle: SavedStateHandle,
) : ViewModel() {

    private val id: Int by lazy {
        savedStateHandle["id"] ?: -1
    }

    private val _state: MutableStateFlow<AnimeDetailState> =
        MutableStateFlow(AnimeDetailState())

    var initFavorite = true
        private set

    val state get() = _state.asStateFlow()

    val isFavorite = repository.isAnimeFavorite(id)
        .stateIn(viewModelScope, SharingStarted.Eagerly, false)

    init {
        if (id != -1) {
            getDetail(id)
        }
    }

    private fun getDetail(id: Int) {
        _state.value = _state.updateAndGet {
            it.copy(
                isLoading = true,
                error = null
            )
        }

        viewModelScope.launch {
            when (val resource = repository.getAnimeById(id)) {
                is Resource.Success -> {
                    _state.value = _state.updateAndGet {
                        it.copy(
                            anime = resource.data,
                            isLoading = false,
                            error = null
                        )
                    }
                }
                is Resource.Error -> {
                    _state.value = _state.updateAndGet {
                        it.copy(
                            anime = null,
                            isLoading = false,
                            error = resource.error
                        )
                    }
                }
            }
        }
    }

    fun changeFavorite(detailAnime: DetailAnime) {
        viewModelScope.launch {
            initFavorite = false
            if (isFavorite.value) {
                repository.removeFavorite(detailAnime.id)
            } else {
                repository.saveFavorite(detailAnime)
            }
        }
    }
}