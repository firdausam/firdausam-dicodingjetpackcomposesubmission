package com.firdausam.dicodingjcomposesub.ui.screen.home

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.firdausam.dicodingjcomposesub.domain.model.Anime
import com.firdausam.dicodingjcomposesub.ui.ViewModelFactory
import com.firdausam.dicodingjcomposesub.ui.component.AnimeItem
import com.firdausam.dicodingjcomposesub.ui.component.SearchBar
import com.firdausam.dicodingjcomposesub.ui.screen.common.BaseScreen

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    viewModel: HomeViewModel = viewModel(
        factory = ViewModelFactory.getInstance(LocalContext.current)
    ),
    navigateToDetail: (Int) -> Unit
) {
    Column(modifier = modifier) {
        SearchBar(
            viewModel.query.collectAsState().value,
            onValueChange = viewModel::search,
            modifier = Modifier.background(MaterialTheme.colors.primary)
        )
        BaseScreen(
            state = viewModel.state.collectAsState().value
        ) {
            AnimeContent(it, navigateToDetail)
        }
    }
}

@Composable
fun AnimeContent(
    anime: List<Anime>,
    navigateToDetail: (Int) -> Unit
) {
    val listState = rememberLazyListState()
    LazyColumn(
        state = listState,
        contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(anime) { item ->
            with(item) {
                AnimeItem(
                    title = title,
                    image = image,
                    type = type,
                    episodeCount = episodes,
                    memberCount = members,
                    score = score,
                    modifier = Modifier.clickable {
                        navigateToDetail(item.id)
                    }
                )
            }
        }
    }
}