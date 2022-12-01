package com.firdausam.dicodingjcomposesub.ui.screen.common

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.firdausam.dicodingjcomposesub.ui.component.EmptyCommon
import com.firdausam.dicodingjcomposesub.ui.component.ErrorCommon
import com.firdausam.dicodingjcomposesub.ui.component.ProgressCommon
import com.firdausam.dicodingjcomposesub.ui.state.BaseState

@Composable
fun <T: Any> BaseScreen(
    state: BaseState<T>,
    modifier: Modifier = Modifier,
    onLoading: @Composable (() -> Unit)? = null,
    onEmpty: @Composable (() -> Unit)? = null,
    onError: @Composable ((String) -> Unit)? = null,
    onLoadData: @Composable (data: T) -> Unit
) {
    Box(modifier = modifier.fillMaxSize()) {
        val error = state.errorBase
        ResultContent(
            data = state.data,
            isLoading = state.isLoadingBase,
            error = error,
            onLoading = onLoading ?: { BaseLoad() },
            onEmpty = onEmpty ?: { BaseEmpty() },
            onError = onError ?: { BaseError(it) },
            onLoadData = onLoadData
        )
    }
}

@Composable
private fun BoxScope.BaseLoad() {
    ProgressCommon(Modifier.align(Alignment.Center))
}

@Composable
private fun BoxScope.BaseEmpty() {
    EmptyCommon(modifier = Modifier.align(Alignment.Center))
}

@Composable
private fun BoxScope.BaseError(error: String) {
    ErrorCommon(modifier = Modifier.align(Alignment.Center), error)
}