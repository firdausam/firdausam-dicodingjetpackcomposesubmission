package com.firdausam.dicodingjcomposesub.ui.screen.favorite

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.firdausam.dicodingjcomposesub.domain.repository.AnimeRepository
import com.firdausam.dicodingjcomposesub.ui.state.AnimeListState
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn

class FavoriteViewModel(repository: AnimeRepository) : ViewModel() {
    val state = repository.getFavoritesAnime().map { data ->
        AnimeListState(
            anime = data,
            isLoading = false,
            error = null
        )
    }.stateIn(viewModelScope, SharingStarted.Eagerly, AnimeListState())
}