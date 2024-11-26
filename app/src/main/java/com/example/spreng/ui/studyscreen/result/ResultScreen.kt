package com.example.spreng.ui.studyscreen.result

import android.content.Intent
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.tween
import androidx.compose.animation.slideInVertically
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.spreng.MainActivity
import com.example.spreng.R
import com.example.spreng.ui.custom.CustomRoundedBorderBox
import com.example.spreng.ui.studyscreen.StudyBottomButton
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import kotlinx.coroutines.delay


@Composable
fun ResultScreen(
    modifier: Modifier = Modifier,
    numCorrect: Int,
    numTotal: Int
) {
    val context = LocalContext.current

    val systemUiController = rememberSystemUiController()
    systemUiController.setSystemBarsColor(
        color = colorResource(R.color.container),
        darkIcons = true
    )

    Scaffold(
        bottomBar = {
            BottomAppBar(
                modifier = modifier,
                containerColor = Color.Transparent,
                contentPadding = PaddingValues(0.dp)
            ) {
                Box(
                    modifier = Modifier.fillMaxWidth(),
                    contentAlignment = Alignment.Center
                ) {
                    StudyBottomButton(
                        text = "Về trang chủ",
                        onBottomButtonPressed = {
                            context.startActivity(Intent(context, MainActivity::class.java))
                        }
                    )
                }
            }
        },

        containerColor = colorResource(R.color.container)

    ) {
        Column(
            modifier = modifier.padding(it).fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top
        ) {
            Spacer(Modifier.padding(vertical = 100.dp))
            ResultBanner()

            ScoreCard(numCorrect = numCorrect, numTotal = numTotal)

            XpCard(xp = numCorrect * 10)
        }
    }
}

private const val IDLE_TIME = 100
private const val BANNER_SLIDE_IN_TIME = 750
private const val SCORE_SLIDE_IN_TIME = 750
private const val SCORE_CHANGE_COLOR_TIME = 750
private const val XP_SLIDE_IN_TIME = 750


@Composable
private fun ResultBanner(
    modifier: Modifier = Modifier
) {
    var isVisible by remember { mutableStateOf(true) }

    LaunchedEffect(true) {
        delay(IDLE_TIME.toLong())
        isVisible = true
    }

    AnimatedVisibility(
        visible = isVisible,
        enter = slideInVertically(
            initialOffsetY = { - it },
            animationSpec = tween(BANNER_SLIDE_IN_TIME)
        ),
        modifier = modifier
    ) {
        CustomRoundedBorderBox(
            cornerRadius = dimensionResource(R.dimen.medium),
            bottomBorderWidth = dimensionResource(R.dimen.small),
            containerColor = colorResource(R.color.teal_200),
            borderColor = colorResource(R.color.teal_700),
            modifier = Modifier.padding(dimensionResource(R.dimen.medium))
        ) {
            Text(
                text = "Tổng Kết Bài Học",
                style = MaterialTheme.typography.displaySmall,
                modifier = Modifier.padding(dimensionResource(R.dimen.medium))
            )
        }
    }
}

@Composable
private fun ScoreCard(
    modifier: Modifier = Modifier,
    numCorrect: Int,
    numTotal: Int
) {

    val correctRate = numCorrect.toFloat() / numTotal

    var isVisible by remember { mutableStateOf(true) }

    var isColorChanged by remember { mutableStateOf(false) }

    val color by animateColorAsState(
        targetValue = if (isColorChanged) {
            if (correctRate < 0.4f) {
                colorResource(R.color.error)
            } else if (correctRate < 0.7f) {
                colorResource(R.color.warning)
            } else {
                colorResource(R.color.success)
            }
        } else {
            colorResource(R.color.teal_200)
        },
        animationSpec = tween(SCORE_CHANGE_COLOR_TIME),
        label = ""
    )

    val borderColor by animateColorAsState(
        targetValue = if (isColorChanged) {
            if (correctRate < 0.4f) {
                colorResource(R.color.error_border)
            } else if (correctRate < 0.7f) {
                colorResource(R.color.warning_border)
            } else {
                colorResource(R.color.success_border)
            }
        } else {
            colorResource(R.color.teal_700)
        },
        animationSpec = tween(SCORE_CHANGE_COLOR_TIME),
        label = ""
    )

    LaunchedEffect(true) {
        delay((IDLE_TIME + BANNER_SLIDE_IN_TIME).toLong())
        isVisible = true
        delay((IDLE_TIME + SCORE_SLIDE_IN_TIME).toLong())
        isColorChanged = true
    }

    AnimatedVisibility(
        visible = isVisible,
        enter = slideInVertically(
            initialOffsetY = { it },
            animationSpec = tween(SCORE_SLIDE_IN_TIME)
        )
    ) {
        CustomRoundedBorderBox(
            cornerRadius = dimensionResource(R.dimen.medium),
            bottomBorderWidth = dimensionResource(R.dimen.tiny_small),
            modifier = modifier.padding(top = dimensionResource(R.dimen.medium)),
            borderColor = borderColor,
            containerColor = color
        ) {
            Text(
                text = "Kết quả của bạn: $numCorrect/$numTotal",
                style = MaterialTheme.typography.titleMedium,
                modifier = Modifier.padding(dimensionResource(R.dimen.medium))
            )
        }
    }
}


@Composable
private fun XpCard(
    modifier: Modifier = Modifier,
    xp: Int
) {
    var isVisible by remember { mutableStateOf(true) }

    LaunchedEffect(true) {
        delay(
            (IDLE_TIME * 3 + BANNER_SLIDE_IN_TIME + SCORE_SLIDE_IN_TIME + SCORE_CHANGE_COLOR_TIME).toLong()
        )
        isVisible = true
    }

    AnimatedVisibility(
        visible = isVisible,
        enter = slideInVertically(
            initialOffsetY = { it },
            animationSpec = tween(XP_SLIDE_IN_TIME)
        )
    ) {
        CustomRoundedBorderBox(
            cornerRadius = dimensionResource(R.dimen.medium),
            bottomBorderWidth = dimensionResource(R.dimen.tiny_small),
            modifier = modifier.padding(top = dimensionResource(R.dimen.medium)),
            containerColor = colorResource(R.color.teal_200),
            borderColor = colorResource(R.color.teal_700)
        ) {
            Text(
                text = "Tổng xp nhận được: $xp",
                style = MaterialTheme.typography.titleMedium,
                modifier = Modifier.padding(dimensionResource(R.dimen.medium))
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun ResultScreenPreview() {
    ResultScreen(numCorrect = 1, numTotal = 10)
}
