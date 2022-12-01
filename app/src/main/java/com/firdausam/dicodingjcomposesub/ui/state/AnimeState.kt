package com.firdausam.dicodingjcomposesub.ui.state

import com.firdausam.dicodingjcomposesub.domain.model.Anime
import com.firdausam.dicodingjcomposesub.domain.model.DetailAnime

sealed class BaseState<T>(val data: T?, val isLoadingBase: Boolean, val errorBase: String?)

data class AnimeListState(
    val anime: List<Anime> = emptyList(),
    val isLoading: Boolean = false,
    val error: String? = null,
): BaseState<List<Anime>>(anime, isLoading, error)

data class AnimeDetailState(
    val anime: DetailAnime? = null,
    val isLoading: Boolean = false,
    val error: String? = null,
): BaseState<DetailAnime>(anime, isLoading, error)