package com.firdausam.dicodingjcomposesub.ui.screen.detail

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Star
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.MutableCreationExtras
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import com.firdausam.dicodingjcomposesub.R
import com.firdausam.dicodingjcomposesub.ui.ViewModelFactory
import com.firdausam.dicodingjcomposesub.ui.component.*
import com.firdausam.dicodingjcomposesub.ui.screen.common.BaseScreen
import com.firdausam.dicodingjcomposesub.ui.theme.BlackOpacity
import com.firdausam.dicodingjcomposesub.ui.theme.DicodingJetpackComposeSubmissionTheme
import com.firdausam.dicodingjcomposesub.ui.theme.Shapes

@Composable
fun DetailScreen(
    id: Int,
    onBackClick: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: DetailViewModel = viewModel(
        factory = ViewModelFactory.getInstance(LocalContext.current),
        extras = MutableCreationExtras().apply { set(ViewModelFactory.KeyId, id) }
    )
) {
    Column {
        TopAppBar(
            navigationIcon = {
                IconButton(onClick = onBackClick) {
                    Icon(
                        imageVector = Icons.Default.ArrowBack,
                        contentDescription = "back"
                    )
                }
            },
            title = {
                Text(stringResource(R.string.detail))
            },
        )
        BaseScreen(
            state = viewModel.state.collectAsState().value,
            modifier = modifier.weight(1f)
        ) { result ->
            with(result) {
                AnimeDetailContent(
                    title = title,
                    image = image,
                    score = score,
                    ranked = ranked,
                    popularity = popularity,
                    members = members,
                    info = toInfo(),
                    descriptions = toDescriptions(),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 12.dp)
                )
            }
        }
    }
}

@Composable
fun AnimeDetailContent(
    title: String,
    image: String,
    score: Double,
    ranked: Int,
    popularity: Int,
    members: Int,
    info: List<InfoItem>,
    descriptions: List<InfoItem>,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier.verticalScroll(rememberScrollState())) {
        Spacer(modifier = Modifier.height(12.dp))
        Text(
            text = title,
            style = MaterialTheme.typography.h6.copy(
                fontWeight = FontWeight.ExtraBold
            )
        )
        Spacer(modifier = Modifier.height(12.dp))
        TitleDivider(title = stringResource(R.string.information))
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(
                modifier = Modifier
                    .weight(1f)
                    .align(Alignment.Top)
            ) {
                info.forEach {
                    Info(title = stringResource(it.title), value = it.value)
                }
            }
            Spacer(
                modifier = Modifier.width(8.dp)
            )
            Box(
                modifier = Modifier
                    .size(width = 140.dp, height = 196.dp)
                    .clip(Shapes.medium)
            ) {
                AsyncImage(
                    model = image,
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .size(width = 140.dp, height = 196.dp)
                        .clip(Shapes.medium)
                )

                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center,
                    modifier = Modifier
                        .fillMaxWidth()
                        .align(Alignment.BottomCenter)
                        .background(BlackOpacity)
                        .padding(vertical = 4.dp, horizontal = 8.dp)
                        .clip(Shapes.medium)
                ) {
                    Icon(
                        imageVector = Icons.Default.Star,
                        contentDescription = "score",
                        tint = Color.Yellow
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(
                        text = score.toString(),
                        color = Color.White,
                        style = MaterialTheme.typography.body1.copy(
                            fontWeight = FontWeight.Bold
                        ),
                    )
                }
            }
        }
        Spacer(modifier = Modifier.height(12.dp))
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            StatisticCard(
                title = stringResource(id = R.string.ranked),
                value = "#$ranked",
                modifier = Modifier
                    .weight(1f)
                    .padding(horizontal = 8.dp),
                backgroundColor = MaterialTheme.colors.primary,
                contentColor = Color.White
            )
            StatisticCard(
                title = stringResource(id = R.string.popularity),
                value = "#$popularity",
                modifier = Modifier
                    .weight(1f)
                    .padding(horizontal = 8.dp),
                backgroundColor = MaterialTheme.colors.primary,
                contentColor = Color.White
            )
            StatisticCard(
                title = stringResource(id = R.string.members),
                value = members.toString(),
                modifier = Modifier
                    .weight(1f)
                    .padding(horizontal = 8.dp),
                backgroundColor = MaterialTheme.colors.primary,
                contentColor = Color.White
            )
        }
        Spacer(modifier = Modifier.height(12.dp))
        descriptions.forEach {
            Description(title = stringResource(it.title), value = it.value)
            Spacer(modifier = Modifier.height(16.dp))
        }
    }
}

@Preview(
    showBackground = true,
    device = Devices.PIXEL_4
)
@Composable
fun AnimeDetailContentPreview() {
    DicodingJetpackComposeSubmissionTheme {
        AnimeDetailContent(
            title = "Lorem Ipsum Dolor Sit Amet",
            image = "image",
            score = 10.0,
            ranked = 1,
            popularity = 1,
            members = 100000,
            info = listOf(
                InfoItem(
                    title = R.string.ranked,
                    value = "1"
                ),
                InfoItem(
                    title = R.string.popularity,
                    value = "1"
                )
            ),
            descriptions = listOf(
                InfoItem(
                    title = R.string.synopsis,
                    value = "Welcome to your personal dashboard, where you can find an introduction to how GitHub works, tools to help you build software, and help merging your first lines of code."
                ),
                InfoItem(
                    title = R.string.background,
                    value = "Welcome to your personal dashboard, where you can find an introduction to how GitHub works, tools to help you build software, and help merging your first lines of code."
                )
            ),
            modifier = Modifier.fillMaxSize()
        )
    }
}