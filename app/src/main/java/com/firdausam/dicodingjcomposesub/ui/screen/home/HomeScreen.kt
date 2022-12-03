package com.firdausam.dicodingjcomposesub.ui.screen.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import com.firdausam.dicodingjcomposesub.ui.ViewModelFactory
import com.firdausam.dicodingjcomposesub.ui.component.AnimeContent
import com.firdausam.dicodingjcomposesub.ui.component.SearchBar
import com.firdausam.dicodingjcomposesub.ui.screen.common.BaseScreen
import com.firdausam.dicodingjcomposesub.util.BackPressHandler

@Composable
fun HomeScreen(
    navigateToDetail: (Int) -> Unit,
    modifier: Modifier = Modifier,
    viewModel: HomeViewModel = viewModel(
        factory = ViewModelFactory.getInstance(LocalContext.current)
    ),
) {
    val query = viewModel.query.collectAsState().value
    Column(modifier = modifier) {
        SearchBar(
            query,
            onValueChange = viewModel::search,
            modifier = Modifier.background(MaterialTheme.colors.primary)
        )
        BaseScreen(
            state = viewModel.state.collectAsState().value
        ) {
            AnimeContent(it, navigateToDetail)
        }
    }

    BackPressHandler(query.isNotEmpty()) {
        viewModel.search("")
    }
}