package com.firdausam.dicodingjcomposesub.util

import androidx.compose.material.ScaffoldState
import androidx.compose.material.SnackbarHostState
import androidx.compose.material.SnackbarResult
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class MySnackbar(
    private val snackbarHostState: SnackbarHostState,
    private val scope: CoroutineScope,
) {
    private var snackbarJob: Job? = null

    init {
        dismiss()
    }

    fun showSnackBar(
        message: String,
        actionLabel: String? = null,
        onActionResult: ((SnackbarResult) -> Unit)? = null
    ) {
        dismiss()
        snackbarJob = scope.launch {
            val result = snackbarHostState.showSnackbar(
                message = message,
                actionLabel = actionLabel
            )
            onActionResult?.invoke(result)
        }
    }

    fun dismiss() {
        snackbarJob?.let {
            it.cancel()
            snackbarJob = null
        }
    }
}

@Composable
fun rememberMySnackbar(
    scaffoldState: ScaffoldState = rememberScaffoldState(),
    coroutineScope: CoroutineScope = rememberCoroutineScope()
): MySnackbar = remember {
    MySnackbar(scaffoldState.snackbarHostState, coroutineScope)
}