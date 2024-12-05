package com.example.spreng.ui.studyscreen

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.spreng.R
import com.example.spreng.ui.custom.CustomRoundedBorderBox
import com.example.spreng.ui.custom.pressHandling
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

/**
 * Base screen for all study screens
 * @param content is a composable function representing the content of the question
 */
@Composable
fun BaseStudyScreen(
    modifier: Modifier = Modifier,
    learningProgress: Float,
    questionTitle: String,
    isDone: Boolean = false,
    onCancelling: () -> Unit,
    onBottomButtonPressed: () -> Unit,
    content: (@Composable () -> Unit)
) {

    val systemUiController = rememberSystemUiController()
    systemUiController.setStatusBarColor(
        color = colorResource(id = R.color.container),
        darkIcons = true
    )

    Scaffold(
        topBar = {
            StudyTopBar(learningProgress, onCancelling)
        },
        bottomBar = {
            StudyBottomBar(
                isDone = isDone,
                onBottomButtonPressed = onBottomButtonPressed
            )
        },
        modifier = modifier,
        containerColor = colorResource(R.color.container)
    ) { innerPadding ->
        Column(
            modifier = Modifier.padding(innerPadding).fillMaxSize()
        ) {
            Text(
                text = questionTitle,
                style = MaterialTheme.typography.headlineMedium,
                modifier = Modifier
                    .padding(
                        start = dimensionResource(R.dimen.small),
                        end = dimensionResource(R.dimen.small),
                        bottom = dimensionResource(R.dimen.small)
                    )

            )
            content()
        }

    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun StudyTopBar(
    learningProgress: Float,
    onCancelling: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
    ) {
        TopAppBar(
            title = {
                LinearProgressIndicator(
                    progress = { learningProgress },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(dimensionResource(R.dimen.medium))
                        .padding(end = dimensionResource(R.dimen.small))
                        .clip(RoundedCornerShape(dimensionResource(R.dimen.large)))
                        .border(1.dp, Color.Black, RoundedCornerShape(dimensionResource(R.dimen.large)))
                    ,
                    color = Color(20, 200, 20),
                    trackColor = Color.LightGray,
                    strokeCap = StrokeCap.Round
                )
            },

            colors = TopAppBarDefaults.topAppBarColors(
                containerColor = colorResource(R.color.container)
            ),

            navigationIcon = {
                IconButton(onClick = onCancelling) {
                    Icon(
                        Icons.Filled.Close,
                        contentDescription = stringResource(R.string.cnt_desc_close),
                        tint = Color.Black
                    )
                }
            }
        )

        HorizontalDivider(
            thickness = dimensionResource(R.dimen.very_tiny),
            color = Color.Black
        )
    }

}


@Composable
private fun StudyBottomBar(
    modifier: Modifier = Modifier,
    isDone: Boolean,
    onBottomButtonPressed: () -> Unit,
) {

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
                text = stringResource(
                    if (isDone) R.string.button_title_next
                    else R.string.button_title_complete
                ),
                onBottomButtonPressed = onBottomButtonPressed
            )
        }
    }
}


@Composable
internal fun StudyBottomButton(
    modifier: Modifier = Modifier,
    text: String,
    onBottomButtonPressed: () -> Unit
) {
    var isPressed by remember { mutableStateOf(false) }

    val coroutineScope = rememberCoroutineScope()

    CustomRoundedBorderBox(
        cornerRadius = dimensionResource(R.dimen.small_medium),
        modifier = modifier
            .padding(
                top = if (isPressed) {
                    dimensionResource(R.dimen.small)
                } else {
                    0.dp
                }
            )
            .width(320.dp)
            .pressHandling {
                isPressed = it
                if (isPressed) {
                    coroutineScope.launch {
                        delay(100)
                        onBottomButtonPressed()
                    }
                }
            },
        bottomBorderWidth = if (isPressed) {
            0.dp
        } else {
            dimensionResource(R.dimen.small)
        },
        contentWidthDp = 320.dp,
        containerColor = colorResource(R.color.study_button),
        borderColor = colorResource(R.color.study_button_border),
    ) {
        Text(
            text = text,
            color = Color.Black,
            style = MaterialTheme.typography.titleLarge,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(dimensionResource(R.dimen.small))
        )
    }
}

@Preview
@Composable
fun StudyBottomButtonPreview() {
    StudyBottomButton(text = "Next", onBottomButtonPressed = {})
}

@Preview(showBackground = true)
@Composable
private fun BaseStudyScreenPreview() {
    BaseStudyScreen(
        learningProgress = 0.3f,
        questionTitle = "Hoàn thiện câu sau",
        onCancelling = {},
        onBottomButtonPressed = {},
        content = {
            Column(
                modifier = Modifier.fillMaxSize().padding(
                    start = 8.dp,
                    end = 8.dp
                )
            ) {
                Box(
                    modifier = Modifier
                        .padding(bottom = 8.dp)
                        .fillMaxSize()
                        .weight(1f)
                        .border(1.dp, Color.Black, RoundedCornerShape(8.dp))
                )
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .weight(2f)
                        .border(1.dp, Color.Black, RoundedCornerShape(8.dp))
                )
            }
        }
    )
}