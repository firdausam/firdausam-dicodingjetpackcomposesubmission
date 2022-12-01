package com.firdausam.dicodingjcomposesub.ui.screen.common

import androidx.compose.runtime.Composable

@Composable
fun <T : Any> ResultContent(
    data: T?,
    isLoading: Boolean,
    error: String?,
    onLoading: @Composable (() -> Unit)? = null,
    onEmpty: @Composable (() -> Unit)? = null,
    onError: @Composable ((String) -> Unit)? = null,
    onLoadData: @Composable (data: T) -> Unit,
) {
    if (isLoading) {
        onLoading?.invoke()
    } else {
        when {
            data != null && data !is Collection<*> -> onLoadData(data)
            data is Collection<*> && data.isNotEmpty() -> onLoadData(data)
            error == null -> onEmpty?.invoke()
            else -> onError?.invoke(error)
        }
    }
}