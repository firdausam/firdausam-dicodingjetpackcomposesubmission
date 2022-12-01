package com.firdausam.dicodingjcomposesub.ui.component

import androidx.compose.foundation.layout.*
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.firdausam.dicodingjcomposesub.R
import com.firdausam.dicodingjcomposesub.ui.theme.DicodingJetpackComposeSubmissionTheme
import com.firdausam.dicodingjcomposesub.ui.theme.Gray
import com.firdausam.dicodingjcomposesub.ui.theme.Shapes

@Composable
fun AnimeItem(
    title: String,
    image: String,
    type: String,
    episodeCount: Int,
    memberCount: Int,
    score: Double,
    modifier: Modifier = Modifier
) {
    Card(modifier = modifier) {
        Row(
            modifier = Modifier.fillMaxWidth().padding(vertical = 8.dp, horizontal = 16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            AsyncImage(
                model = image,
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier.size(width = 60.dp, height = 84.dp)
                    .clip(Shapes.medium)
            )
            Spacer(
                modifier = Modifier.width(16.dp)
            )
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = title,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis,
                    style = MaterialTheme.typography.subtitle1.copy(
                        fontWeight = FontWeight.ExtraBold
                    )
                )
                Text(
                    text = "$type ($episodeCount)",
                    color = Gray,
                    style = MaterialTheme.typography.subtitle2,
                )
                Text(
                    text = stringResource(R.string.members_count, memberCount),
                    color = Gray,
                    style = MaterialTheme.typography.caption,
                )
            }
            Spacer(
                modifier = Modifier.width(16.dp)
            )
            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Icon(
                    imageVector = Icons.Default.Star,
                    contentDescription = "score",
                    tint = Color.Yellow
                )
                Text(
                    text = score.toString(),
                    style = MaterialTheme.typography.body1.copy(
                        fontWeight = FontWeight.Bold
                    ),
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun AnimeItemPreview() {
    DicodingJetpackComposeSubmissionTheme {
        AnimeItem(
            title = "Naruto",
            image = "",
            type = "TV",
            episodeCount = 800,
            memberCount = 100000,
            score = 9.0
        )
    }
}