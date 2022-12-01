package com.firdausam.dicodingjcomposesub.ui.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Info
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.firdausam.dicodingjcomposesub.R
import com.firdausam.dicodingjcomposesub.ui.theme.DicodingJetpackComposeSubmissionTheme

@Composable
private fun MessageCommon(
    message: String,
    imageVector: ImageVector,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Icon(
            modifier = Modifier.size(
                width = 80.dp,
                height = 80.dp,
            ),
            imageVector = imageVector,
            contentDescription = "message",
            tint = Color.Red
        )
        Text(
            text = message,
            modifier = Modifier.padding(horizontal = 16.dp),
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.body2.copy(
                fontWeight = FontWeight.Bold
            )
        )
    }
}

@Composable
fun EmptyCommon(
    modifier: Modifier = Modifier,
    message: String = "",
) {
    MessageCommon(
        message = message.ifEmpty { stringResource(R.string.empty_message) },
        imageVector = Icons.Filled.Info,
        modifier = modifier
    )
}

@Composable
fun ErrorCommon(
    modifier: Modifier = Modifier,
    errorMessage: String = "",
) {
    MessageCommon(
        message = errorMessage.ifEmpty { stringResource(R.string.error_message) },
        imageVector = Icons.Filled.Clear,
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun MessageCommonPreview() {
    DicodingJetpackComposeSubmissionTheme {
        MessageCommon(message = "Please info your message here.", imageVector = Icons.Filled.Info)
    }
}