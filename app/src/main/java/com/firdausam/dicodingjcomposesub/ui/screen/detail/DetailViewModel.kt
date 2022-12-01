package com.firdausam.dicodingjcomposesub.ui.screen.detail

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.firdausam.dicodingjcomposesub.domain.repository.AnimeRepository
import com.firdausam.dicodingjcomposesub.domain.util.Resource
import com.firdausam.dicodingjcomposesub.ui.state.AnimeDetailState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.updateAndGet
import kotlinx.coroutines.launch

class DetailViewModel(
    private val repository: AnimeRepository,
    savedStateHandle: SavedStateHandle,
) : ViewModel() {
    private val _state: MutableStateFlow<AnimeDetailState> =
        MutableStateFlow(AnimeDetailState())

    val state get() = _state.asStateFlow()

    init {
        val id: Int = savedStateHandle["id"] ?: -1
        if (id != -1) {
            getDetail(id)
        }
    }

    fun getDetail(id: Int) {
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
}