package com.firdausam.dicodingjcomposesub.ui.screen.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.firdausam.dicodingjcomposesub.domain.repository.AnimeRepository
import com.firdausam.dicodingjcomposesub.domain.util.Resource
import com.firdausam.dicodingjcomposesub.ui.state.AnimeListState
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class HomeViewModel(
    private val repository: AnimeRepository
) : ViewModel() {
    private val _state: MutableStateFlow<AnimeListState> =
        MutableStateFlow(AnimeListState())
    val state get() = _state.asStateFlow()

    private var searchJob: Job? = null
    private val keyword = MutableStateFlow("")

    init {
        viewModelScope.launch {
            keyword.collect {
                loadAnime(it)
            }
        }
    }

    fun search(key: String) {
        searchJob?.cancel()
        if (key.isNotEmpty()) {
            searchJob = viewModelScope.launch {
                delay(500)
                keyword.value = key
            }
        }
    }


    private fun loadAnime(keyword: String = "") {
        viewModelScope.launch {
            _state.value = _state.updateAndGet {
                it.copy(
                    isLoading = true,
                    error = null
                )
            }

            when (val result = repository.getAnimeBySearch(keyword)) {
                is Resource.Success -> {
                    _state.value = _state.updateAndGet {
                        it.copy(
                            anime = result.data,
                            isLoading = false,
                            error = null
                        )
                    }
                }
                is Resource.Error -> {
                    _state.value = _state.updateAndGet {
                        it.copy(
                            anime = emptyList(),
                            isLoading = false,
                            error = result.error
                        )
                    }
                }
            }
        }
    }

    override fun onCleared() {
        searchJob?.cancel()
        searchJob = null
        super.onCleared()
    }

}