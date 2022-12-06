package com.firdausam.dicodingjcomposesub.ui.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.firdausam.dicodingjcomposesub.ui.theme.DicodingJetpackComposeSubmissionTheme
import com.firdausam.dicodingjcomposesub.R

@Composable
fun Info(
    title: String,
    value: String,
    modifier: Modifier = Modifier
) {
    Row(modifier = modifier) {
        Text(
            text = stringResource(R.string.info_title, title),
            color = Color.Black,
            style = MaterialTheme.typography.caption.copy(
                fontWeight = FontWeight.SemiBold
            ),
        )
        Text(
            text = value,
            color = Color.DarkGray,
            style = MaterialTheme.typography.caption
        )
    }
}

@Composable
fun Description(
    title: String,
    value: String,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier) {
        TitleDivider(title = title)
        Text(
            text = value,
            color = Color.Black,
            style = MaterialTheme.typography.subtitle2
        )
    }
}

@Composable
fun TitleDivider(title: String) {
    Text(
        text = title,
        color = Color.Black,
        style = MaterialTheme.typography.subtitle1.copy(
            fontWeight = FontWeight.Bold
        ),
    )
    Divider(modifier = Modifier.padding(top = 2.dp, bottom = 4.dp))
}

@Composable
fun StatisticCard(
    title: String,
    value: String,
    modifier: Modifier = Modifier,
    backgroundColor: Color = MaterialTheme.colors.surface,
    contentColor: Color = contentColorFor(backgroundColor)
) {
    Card(
        modifier = modifier,
        shape = RoundedCornerShape(12.dp),
        backgroundColor = backgroundColor,
        contentColor = contentColor
    ) {
        Column(
            modifier = Modifier.padding(8.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = title,
                style = MaterialTheme.typography.body1.copy(
                    fontWeight = FontWeight.Light
                )
            )
            Text(
                text = value,
                style = MaterialTheme.typography.body1.copy(
                    fontWeight = FontWeight.Bold
                )
            )
        }
    }
}

@Preview
@Composable
fun StatisticCardPreview() {
    DicodingJetpackComposeSubmissionTheme {
        StatisticCard("Rangked", "1")
    }
}

@Preview(showBackground = true)
@Composable
fun InfoPreview() {
    DicodingJetpackComposeSubmissionTheme {
        Info(
            title = "title",
            value = "this is value this is value this is value this is value this is value"
        )
    }
}

@Preview(showBackground = true)
@Composable
fun DescriptionPreview() {
    DicodingJetpackComposeSubmissionTheme {
        Description(
            title = "title",
            value = "this is value this is value this is value this is value this is value"
        )
    }
}

@Preview
@Composable
fun TitleDividerPreview() {
    DicodingJetpackComposeSubmissionTheme {
        TitleDivider(title = "This is title")
    }
}