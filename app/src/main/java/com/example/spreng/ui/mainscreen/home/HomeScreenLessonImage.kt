package com.example.spreng.ui.mainscreen.home

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.expandHorizontally
import androidx.compose.animation.expandVertically
import androidx.compose.animation.shrinkHorizontally
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.material.icons.outlined.Star
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.PointerEvent
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntSize
import com.example.spreng.R
import com.example.spreng.ui.custom.CustomRoundedBorderBox
import kotlinx.coroutines.delay

@Composable
internal fun LessonImage(
    modifier: Modifier = Modifier,
    lessonUI: LessonUI,
    lessonIdx: Int,
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
        Row {
            Spacer(Modifier.weight(lessonUI.leftWeight))

            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                Image(
                    painter = painterResource(
                        if (lessonUI.isCompleted) {
                            if (lessonUI.isPressed) {
                                R.drawable.lesson_pressed
                            } else {
                                R.drawable.lesson
                            }
                        } else {
                            if (lessonUI.isPressed) {
                                R.drawable.lesson_uncompleted_pressed
                            } else {
                                R.drawable.lesson_uncompleted
                            }
                        }
                    ),
                    contentDescription = null,
                    modifier = Modifier
                        .pointerInput(Unit) {
                            awaitPointerEventScope {
                                while (true) {
                                    val event = awaitPointerEvent()

                                    val isPressed = handlePressAction(
                                        event = event,
                                        size = size
                                    )
                                    onPressChanged(lessonIdx, isPressed)
                                }
                            }
                        }
                )

                if (lessonUI.isCompleted) {
                    RatingBar(
                        rating = lessonUI.rating ?: 0
                    )
                }

            }

            Spacer(Modifier.weight(lessonUI.rightWeight))
        }

        LessonBox(
            isShowingBox = lessonUI.cardState == LessonCardState.SHOWING
                    || lessonUI.cardState == LessonCardState.OPENING,
            title = lessonUI.title,
            summarization = lessonUI.summarization,
            isLessonCompleted = lessonUI.isCompleted,
            onLessonStarted = onLessonStarted,
            onOpeningCompleted = onOpeningCompleted,
            onClosingCompleted = onClosingCompleted
        )
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


private fun handlePressAction(
    event: PointerEvent,
    size: IntSize,
) : Boolean {
    val eventPosition = event.changes.firstOrNull()?.position

    if (event.changes.firstOrNull()?.pressed == true && eventPosition != null) {

        val w = size.width
        val h = size.height
        val eX = eventPosition.x
        val eY = eventPosition.y

        val isPressed = eX >= 0 && eY >= 0 && eX <= w && eY <= h

        return isPressed
    }

    return false
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
    isLessonCompleted: Boolean
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
    onLessonClicked: () -> Unit,
) {
    val mediumPadding = dimensionResource(R.dimen.medium)

    Column(
        modifier = modifier.background(
            color = colorResource(R.color.teal_200)
        )
    ) {
        Text(
            text = title,
            style = MaterialTheme.typography.headlineLarge,
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
            modifier = Modifier.padding(
                start = mediumPadding,
                end = mediumPadding
            )
        )
        Row {
            Spacer(Modifier.weight(1f))

            CustomRoundedBorderBox(
                modifier = Modifier
                    .padding(dimensionResource(R.dimen.medium))
                    .clickable(
                        interactionSource = null,
                        indication = null,
                    ) {
                        onLessonClicked()
                    },
                cornerRadius = dimensionResource(R.dimen.small),
                containerColor = colorResource(R.color.purple_200),
                borderColor = colorResource(R.color.purple_700),
                startBorderWidth = dimensionResource(R.dimen.tiny),
                bottomBorderWidth = dimensionResource(R.dimen.small),
            ) {
                Text(
                    text = if (isLessonCompleted) {
                        stringResource(R.string.button_title_start)
                    } else {
                        stringResource(R.string.button_title_review)
                    },
                    style = MaterialTheme.typography.headlineSmall,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(
                        top = dimensionResource(R.dimen.small),
                        bottom = dimensionResource(R.dimen.small),
                        start = mediumPadding,
                        end = mediumPadding
                    )
                )
            }

        }
    }
}

@Preview(showBackground = true)
@Composable
private fun LessonContentPrev() {
    LessonContent(
        title = "Environment",
        summarization = "This is a lesson about environment",
        isLessonCompleted = false,
        onLessonClicked = {}
    )
}

@Preview(showBackground = true)
@Composable
private fun RatingBarPrev() {
    RatingBar(
        modifier = Modifier.background(colorResource(R.color.container)),
        rating = 2
    )
}
