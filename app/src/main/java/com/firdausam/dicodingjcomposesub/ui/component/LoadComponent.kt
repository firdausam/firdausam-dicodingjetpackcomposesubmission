package com.firdausam.dicodingjcomposesub.ui.component

import androidx.compose.foundation.layout.size
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun ProgressCommon(modifier: Modifier = Modifier) {
    CircularProgressIndicator(
        modifier = modifier
            .size(width = 40.dp, height = 40.dp)
    )
}