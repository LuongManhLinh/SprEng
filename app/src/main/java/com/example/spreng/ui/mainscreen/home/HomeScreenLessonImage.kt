package com.example.spreng.ui.mainscreen.home

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.expandHorizontally
import androidx.compose.animation.expandVertically
import androidx.compose.animation.shrinkHorizontally
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Reviews
import androidx.compose.material.icons.outlined.RadioButtonChecked
import androidx.compose.material.icons.outlined.Star
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.spreng.R
import com.example.spreng.ui.custom.CustomRoundedBorderBox
import com.example.spreng.ui.custom.pressHandling
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


@Composable
internal fun LessonUI(
    modifier: Modifier = Modifier,
    lessonUIState: LessonUIState,
    lessonIdx: Int,
    isCurrentLesson: Boolean,
    onPressChanged: (Int, Boolean) -> Unit,
    onLessonStarted: () -> Unit,
    onOpeningCompleted: () -> Unit,
    onClosingCompleted: () -> Unit
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
            .padding(dimensionResource(R.dimen.medium))
            .clip(RoundedCornerShape(dimensionResource(R.dimen.small)))
    ) {
        LessonImage(
            lessonUIState = lessonUIState,
            lessonIdx = lessonIdx,
            isCurrentLesson = isCurrentLesson,
            onPressChanged = onPressChanged
        )

        LessonBox(
            isShowingBox = lessonUIState.cardState == LessonCardState.SHOWING
                    || lessonUIState.cardState == LessonCardState.OPENING,
            title = lessonUIState.title,
            summarization = lessonUIState.summarization,
            isLessonCompleted = lessonUIState.isCompleted,
            isCurrentLesson = isCurrentLesson,
            onLessonStarted = onLessonStarted,
            onOpeningCompleted = onOpeningCompleted,
            onClosingCompleted = onClosingCompleted
        )
    }
}

@Composable
private fun LessonImage(
    modifier: Modifier = Modifier,
    lessonUIState: LessonUIState,
    lessonIdx: Int,
    isCurrentLesson: Boolean,
    onPressChanged: (Int, Boolean) -> Unit
) {
    Row(
        modifier = modifier
    ) {
        Spacer(Modifier.weight(lessonUIState.leftWeight))

        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Image(
                painter = painterResource(
                    if (lessonUIState.isCompleted || isCurrentLesson) {
                        if (lessonUIState.isPressed) {
                            R.drawable.lesson_pressed
                        } else {
                            R.drawable.lesson
                        }
                    } else {
                        if (lessonUIState.isPressed) {
                            R.drawable.lesson_uncompleted_pressed
                        } else {
                            R.drawable.lesson_uncompleted
                        }
                    }
                ),
                contentDescription = null,
                modifier = Modifier
                    .pressHandling {
                        onPressChanged(lessonIdx, it)
                    }
            )


            if (lessonUIState.isCompleted) {
                RatingBar(
                    rating = lessonUIState.rating ?: 0
                )
            }

        }

        Spacer(Modifier.weight(lessonUIState.rightWeight))
    }
}


@Composable
private fun RatingBar(
    modifier: Modifier = Modifier,
    rating: Int,
    maxRating: Int = 3
) {
    Row(modifier = modifier) {
        for (index in 1..maxRating) {
            Icon(
                imageVector = if (index <= rating) Icons.Outlined.Star else Icons.Outlined.Star,
                contentDescription = null,
                modifier = Modifier,
                tint = if (index <= rating) Color(122, 128, 232) else Color.Black
            )
        }
    }
}

@Composable
private fun LessonBox(
    modifier: Modifier = Modifier,
    isShowingBox: Boolean,
    title: String,
    summarization: String,
    onLessonStarted: () -> Unit,
    onOpeningCompleted: () -> Unit,
    onClosingCompleted: () -> Unit,
    isLessonCompleted: Boolean,
    isCurrentLesson: Boolean
) {

    LaunchedEffect(isShowingBox) {
        if (isShowingBox) {
            delay(800)
            onOpeningCompleted()
        } else {
            delay(600)
            onClosingCompleted()
        }
    }

    Column(
        modifier = modifier.fillMaxSize(),
        horizontalAlignment = if (isShowingBox) {
            Alignment.Start
        } else {
            Alignment.End
        }
    ) {
        AnimatedVisibility(
            visible = isShowingBox,
            enter = expandHorizontally(
                animationSpec = tween(
                    durationMillis = 800
                ),
                expandFrom = Alignment.Start
            ),
            exit = shrinkHorizontally(
                animationSpec = tween(
                    durationMillis = 500
                ),
                shrinkTowards = Alignment.End
            )
        ) {
            Box(
                modifier = Modifier
                    .height(dimensionResource(R.dimen.tiny))
                    .fillMaxWidth()
                    .background(Color.Black)
            )
        }
    }

    AnimatedVisibility(
        visible = isShowingBox,
        enter = expandVertically(
            animationSpec = tween(
                durationMillis = 700
            )
        ),
        exit = shrinkVertically(
            animationSpec = tween(
                durationMillis = 600
            )
        )
    ) {
        LessonContent(
            title = title,
            summarization = summarization,
            isLessonCompleted = isLessonCompleted,
            isCurrentLesson = isCurrentLesson,
            onLessonClicked = onLessonStarted
        )
    }
}


@Composable
private fun LessonContent(
    modifier: Modifier = Modifier,
    title: String,
    summarization: String,
    isLessonCompleted: Boolean,
    isCurrentLesson: Boolean,
    onLessonClicked: () -> Unit,
) {
    val mediumPadding = dimensionResource(R.dimen.medium)

    var isPressed by remember { mutableStateOf(false) }

    val coroutineScope = rememberCoroutineScope()


    Column(
        modifier = modifier.background(
            color = if (isLessonCompleted || isCurrentLesson) {
                colorResource(R.color.teal_200)
            } else {
                colorResource(R.color.gray_teal)
            }
        )
    ) {
        Text(
            text = title,
            style = MaterialTheme.typography.headlineLarge,
            color = Color.Black,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(
                top = mediumPadding,
                start = mediumPadding,
                end = mediumPadding
            )
        )
        Text(
            text = summarization,
            style = MaterialTheme.typography.headlineSmall,
            color = Color.Black,
            modifier = Modifier.padding(
                start = mediumPadding,
                end = mediumPadding
            )
        )
        Row {

            Spacer(Modifier.weight(1f))

            CustomRoundedBorderBox(
                modifier = Modifier
                    .padding(
                        top = if (isPressed) {
                            dimensionResource(R.dimen.medium) + dimensionResource(R.dimen.small)
                        } else {
                            dimensionResource(R.dimen.medium)
                        },
                        bottom = dimensionResource(R.dimen.medium),
                        end = dimensionResource(R.dimen.medium)
                    )
                    .pressHandling {
                        isPressed = it
                        if (isPressed) {
                            coroutineScope.launch {
                                delay(125)
                                onLessonClicked()
                            }
                        }
                    },
                cornerRadius = dimensionResource(R.dimen.large),
                containerColor = colorResource(R.color.purple_200),
                borderColor = colorResource(R.color.purple_700),
                startBorderWidth = if (isPressed) 0.dp else dimensionResource(R.dimen.tiny),
                bottomBorderWidth = if (isPressed) 0.dp else dimensionResource(R.dimen.small),
                topBorderWidth = 0.dp,
                endBorderWidth = 0.dp
            ) {

                Row(
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .padding(dimensionResource(R.dimen.small))
                ) {
                    Icon(
                        imageVector = if (isLessonCompleted) {
                            Icons.Filled.Reviews
                        } else {
                            Icons.Outlined.RadioButtonChecked
                        },
                        contentDescription = null,
                        tint = Color.Black
                    )
                    Spacer(Modifier.padding(horizontal = dimensionResource(R.dimen.tiny)))
                    Text(
                        text = if (isLessonCompleted) {
                            stringResource(R.string.button_title_review)
                        } else {
                            stringResource(R.string.button_title_start)
                        },
                        color = Color.Black,
                        style = MaterialTheme.typography.titleLarge,
                        fontWeight = FontWeight.Bold
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun LessonContentPreview() {
    LessonContent(
        title = "Lesson Title",
        summarization = "Lesson Summarization",
        isLessonCompleted = false,
        isCurrentLesson = false,
        onLessonClicked = {}
    )
}
