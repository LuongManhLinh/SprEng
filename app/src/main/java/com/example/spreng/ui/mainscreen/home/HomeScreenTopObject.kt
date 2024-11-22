package com.example.spreng.ui.mainscreen.home

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.expandHorizontally
import androidx.compose.animation.shrinkHorizontally
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBackIos
import androidx.compose.material.icons.automirrored.filled.ArrowForwardIos
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.example.spreng.R
import com.example.spreng.ui.custom.CustomRoundedBorderBox

@Composable
internal fun HomeTopBar(
    modifier: Modifier = Modifier,
    userName: String,
    userXp: String
) {
    CustomRoundedBorderBox(
        modifier = modifier
            .padding(
                top = dimensionResource(R.dimen.tiny),
                start = dimensionResource(R.dimen.tiny),
                end = dimensionResource(R.dimen.tiny)
            ),
        cornerRadius = dimensionResource(R.dimen.small),
        startBorderWidth = dimensionResource(R.dimen.tiny),
        bottomBorderWidth = dimensionResource(R.dimen.small),
        containerColor = Color.LightGray,
        borderColor = Color.Gray
    ) {
        Row(
            modifier = Modifier.padding(dimensionResource(R.dimen.tiny)),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(R.drawable.sample_avatar),
                contentDescription = null,
                modifier = Modifier
                    .size(dimensionResource(R.dimen.middle_large))
                    .clip(
                        RoundedCornerShape(dimensionResource(R.dimen.large))
                    ),
                contentScale = ContentScale.Crop
            )
            Text(
                text = userName,
                fontWeight = FontWeight.Bold,
                fontSize = 24.sp,
                modifier = Modifier.padding(start = dimensionResource(R.dimen.small))
            )
            Spacer(Modifier.weight(1f))
            Image(
                painter = painterResource(R.drawable.xp),
                contentDescription = null,
                modifier = Modifier
                    .size(dimensionResource(R.dimen.middle_large))
                    .clip(RoundedCornerShape(dimensionResource(R.dimen.large))),
                contentScale = ContentScale.Crop
            )
            Text(
                text = userXp,
                fontWeight = FontWeight.Bold,
                fontSize = 24.sp,
                color = Color(0, 130, 0),
                modifier = Modifier.padding(start = dimensionResource(R.dimen.small))
            )
        }

    }
}

@Composable
internal fun StudyProgressBar(
    modifier: Modifier = Modifier,
    numCompletedLesson: Int,
    numTotalLesson: Int,
) {

    var appear by remember { mutableStateOf(true) }

    Row(
        modifier = modifier
            .padding(dimensionResource(R.dimen.small))
            .height(dimensionResource(R.dimen.middle_large)),
        verticalAlignment = Alignment.CenterVertically
    ) {

        ExpandButton(
            icon = if (appear) {
                Icons.AutoMirrored.Filled.ArrowBackIos
            } else {
                Icons.AutoMirrored.Filled.ArrowForwardIos
            }
        ) {
            appear = !appear
        }

        StudyProgressBarContent(
            modifier = Modifier
                .fillMaxWidth()
                .clickable(
                    indication = null,
                    interactionSource = null
                ) {
                    appear = false
                },
            appear = appear,
            numCompletedLesson = numCompletedLesson,
            numTotalLesson = numTotalLesson
        )
    }

}

@Composable
private fun ExpandButton(
    icon: ImageVector,
    onClick: () -> Unit
) {
    Icon(
        imageVector = icon,
        contentDescription = null,
        modifier = Modifier.clickable(
            interactionSource = null,
            indication = null
        ) {
            onClick()
        }
    )
}

@Composable
private fun StudyProgressBarContent(
    modifier: Modifier = Modifier,
    appear: Boolean = false,
    numCompletedLesson: Int,
    numTotalLesson: Int
) {
    val smallDimen = dimensionResource(R.dimen.small)
    AnimatedVisibility(
        visible = appear,
        enter = expandHorizontally(
            animationSpec = tween(
                durationMillis = 400
            )
        ),
        exit = shrinkHorizontally (
            animationSpec = tween(
                durationMillis = 400
            )
        ),
        modifier = modifier
    ) {
        CustomRoundedBorderBox(
            cornerRadius = smallDimen,
            startBorderWidth = dimensionResource(R.dimen.very_tiny),
            bottomBorderWidth = dimensionResource(R.dimen.tiny),
            borderColor = Color(0, 100, 0)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxWidth()
                    .drawBehind {
                        val size = Size(
                            width = size.width * numCompletedLesson / numTotalLesson,
                            height = size.height
                        )
                        drawRect(
                            color = Color.Green,
                            size = size,
                        )
                    }
            ) {
                Spacer(Modifier.weight(0.1f))
                Text(
                    text = stringResource(R.string.progress),
                    fontWeight = FontWeight.Bold,
                    fontSize = 24.sp
                )
                Spacer(Modifier.weight(1f))
                Text(
                    text = "$numCompletedLesson/$numTotalLesson",
                    fontWeight = FontWeight.Bold,
                    fontSize = 24.sp
                )
                Spacer(Modifier.weight(0.1f))
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun Preview() {
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        HomeTopBar(
            userName = "Nguyễn Văn A",
            userXp = "1000"
        )
        StudyProgressBar(
            numCompletedLesson = 5,
            numTotalLesson = 10
        )
    }

}

