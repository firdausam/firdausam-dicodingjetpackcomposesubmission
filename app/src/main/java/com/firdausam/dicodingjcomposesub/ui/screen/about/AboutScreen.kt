package com.firdausam.dicodingjcomposesub.ui.screen.about

import android.content.Context
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.firdausam.dicodingjcomposesub.R
import com.firdausam.dicodingjcomposesub.ui.theme.DicodingJetpackComposeSubmissionTheme

@Composable
fun AboutScreen(
    modifier: Modifier = Modifier,
    onSocmedIconClick: (String) -> Unit
) {
    val context: Context = LocalContext.current
    Box(modifier.fillMaxSize()) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.Center),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(id = R.drawable.img_profil),
                contentDescription = stringResource(R.string.profile),
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(120.dp)
                    .clip(CircleShape)
            )
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = stringResource(id = R.string.my_name),
                style = MaterialTheme.typography.h6.copy(
                    fontWeight = FontWeight.Black
                )
            )
            Spacer(modifier = Modifier.height(16.dp))
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    painter = painterResource(R.drawable.ic_dicoding),
                    contentDescription = stringResource(R.string.dicoding),
                    modifier = Modifier
                        .size(40.dp)
                        .clip(CircleShape)
                        .clickable(onClick = {
                            onSocmedIconClick(context.getString(R.string.my_dicoding))
                        })
                )
                Spacer(modifier = Modifier.width(16.dp))
                Image(
                    imageVector = ImageVector.vectorResource(R.drawable.ic_linkedin),
                    contentDescription = stringResource(R.string.linkedin),
                    modifier = Modifier
                        .size(48.dp)
                        .clip(CircleShape)
                        .clickable(onClick = {
                            onSocmedIconClick(context.getString(R.string.my_linkedin))
                        })
                )
                Spacer(modifier = Modifier.width(16.dp))
                Icon(
                    imageVector = ImageVector.vectorResource(R.drawable.ic_github),
                    contentDescription = stringResource(R.string.github),
                    modifier = Modifier
                        .size(48.dp)
                        .clip(CircleShape)
                        .clickable(onClick = {
                            onSocmedIconClick(context.getString(R.string.my_dicoding))
                        }),
                    tint = if (isSystemInDarkTheme()) Color.White else Color.Black
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun AboutScreenPreview() {
    DicodingJetpackComposeSubmissionTheme {
        AboutScreen(onSocmedIconClick = {})
    }
}