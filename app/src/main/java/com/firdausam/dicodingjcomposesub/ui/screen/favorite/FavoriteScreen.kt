package com.firdausam.dicodingjcomposesub.ui.screen.favorite

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import com.firdausam.dicodingjcomposesub.ui.ViewModelFactory
import com.firdausam.dicodingjcomposesub.ui.component.AnimeContent
import com.firdausam.dicodingjcomposesub.ui.screen.common.BaseScreen

@Composable
fun FavoriteScreen(
    navigateToDetail: (Int) -> Unit,
    modifier: Modifier = Modifier,
    viewModel: FavoriteViewModel = viewModel(
        factory = ViewModelFactory.getInstance(LocalContext.current)
    ),
) {
    BaseScreen(
        state = viewModel.state.collectAsState().value,
        modifier = modifier
    ) {
        AnimeContent(it, navigateToDetail)
    }
}