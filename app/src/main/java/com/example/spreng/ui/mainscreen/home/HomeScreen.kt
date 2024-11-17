package com.example.spreng.ui.mainscreen.home

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
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
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.spreng.R

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    onLessonClicked: () -> Unit = { },
    viewModel: HomeViewModel = viewModel()
) {
    val uiState by viewModel.uiState.collectAsState()

    Scaffold(
        modifier = modifier,
        topBar = {
            HomeTopBar()
        }
    ) { innerPadding ->
        LazyColumn(
            modifier = Modifier
                .padding(
                    top = innerPadding.calculateTopPadding(),
                )
                .fillMaxSize()
        ) {
            items(uiState.lessonList) { lessonUI ->

                var isPressed by remember { mutableStateOf(false) }
                var isShowingBox by remember { mutableStateOf(false) }

                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier
                        .padding(dimensionResource(R.dimen.medium))
                        .clip(RoundedCornerShape(dimensionResource(R.dimen.small)))
                ) {
                    Row {
                        Spacer(Modifier.weight(lessonUI.leftWeight))

                        Image(
                            painter = painterResource(
                                if (isPressed) {
                                    R.drawable.lesson_ic_pressed
                                } else {
                                    R.drawable.lesson_ic
                                }
                            ),
                            contentDescription = null,
                            modifier = Modifier
                                .pointerInput(Unit) {
                                    awaitPointerEventScope {
                                        while (true) {
                                            val event = awaitPointerEvent()
                                            val eventPosition = event.changes.firstOrNull()?.position

                                            if (event.changes.firstOrNull()?.pressed == true && eventPosition != null) {

                                                val w = size.width
                                                val h = size.height
                                                val eX = eventPosition.x
                                                val eY = eventPosition.y

                                                isPressed = eX >= 0 && eY >= 0 && eX <= w && eY <= h
                                                isShowingBox = !isShowingBox

                                            } else {

                                                isPressed = false

                                            }
                                        }
                                    }
                                }
                        )
                        Spacer(Modifier.weight(lessonUI.rightWeight))
                    }

                    LessonBox(
                        isShowingBox = isShowingBox,
                        info = "This is the lesson ${lessonUI.id}",
                        onLessonClicked = onLessonClicked
                    )
                }

            }
        }
    }
}

@Composable
private fun HomeTopBar(modifier: Modifier = Modifier) {
    Row(
        modifier = modifier
            .padding(dimensionResource(R.dimen.tiny))
            .clip(shape = RoundedCornerShape(dimensionResource(R.dimen.small)))
            .background(Color.LightGray)
            .padding(dimensionResource(R.dimen.small))
        ,
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painter = painterResource(R.drawable.sample_avatar),
            contentDescription = null,
            modifier = Modifier
                .size(dimensionResource(R.dimen.middle_large))
                .clip(RoundedCornerShape(dimensionResource(R.dimen.large))
                )
            ,
            contentScale = ContentScale.Crop
        )
        Text(
            text = "User's name",
            fontWeight = FontWeight.Bold,
            fontSize = 24.sp,
            modifier = Modifier.padding(start = dimensionResource(R.dimen.small))
        )
        Spacer(Modifier.weight(1f))
        Image(
            painter = painterResource(R.drawable.sample_avatar),
            contentDescription = null,
            modifier = Modifier
                .size(dimensionResource(R.dimen.middle_large))
                .clip(RoundedCornerShape(dimensionResource(R.dimen.large)))
            ,
            contentScale = ContentScale.Crop
        )
        Text(
            text = "9999",
            fontWeight = FontWeight.Bold,
            fontSize = 24.sp,
            color = Color(0, 130, 0),
            modifier = Modifier.padding(start = dimensionResource(R.dimen.small))
        )

    }
}


@Composable
private fun LessonBox(
    isShowingBox: Boolean,
    info: String,
    onLessonClicked: () -> Unit
) {
    Column(
        modifier = Modifier.fillMaxSize(),
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
                    durationMillis = 1000
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
            animationSpec = spring(
                dampingRatio = Spring.DampingRatioNoBouncy,
                stiffness = Spring.StiffnessLow
            )
        ),
        exit = shrinkVertically(
            animationSpec = tween(
                durationMillis = 500,
                delayMillis = 100
            )
        )
    ) {
        LessonContent(
            info = info,
            onLessonClicked = onLessonClicked
        )
    }
}


@Composable
private fun LessonContent(
    info: String,
    onLessonClicked: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier.background(Color.Green)
    ) {
        Text(
            text = info,
            style = MaterialTheme.typography.headlineSmall,
            modifier = Modifier.padding(dimensionResource(R.dimen.medium))
        )
        Row {
            Spacer(Modifier.weight(1f))
            Button(
                onClick = { onLessonClicked() },
                modifier = Modifier.padding(dimensionResource(R.dimen.small))
            ) {
                Text("Start lesson")
            }
        }
    }
}



//@Preview(showBackground = false)
//@Composable
//private fun Preview() {
//    HomeScreen(viewModel = HomeViewModel())
//}

@Preview(showBackground = true)
@Composable
private fun LessonCardPreview() {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        LessonBox(
            true,
            "This is a lesson",{}
        )
    }

}

//@Composable
//private fun LessonCard(
//    info: String,
//    posXdp: Int,
//    modifier: Modifier = Modifier
//) {
//    Card(
//        colors = CardDefaults.cardColors(
//            containerColor = Color.Green,
//        ),
//        modifier = modifier
//            .width(360.dp)
//            .drawBehind {
//                val trianglePath = Path().apply {
//                    moveTo(posXdp.dp.toPx() - 16.dp.toPx(), 0f) // Left position
//                    lineTo(posXdp.dp.toPx() + 2.dp.toPx(), 0f) // Right position
//                    lineTo(posXdp.dp.toPx(), -16.dp.toPx()) // Top position
//                    close()
//                }
//                drawPath(trianglePath, color = Color.Green)
//            }
//    ) {
//        Text(
//            text = info,
//            style = MaterialTheme.typography.headlineSmall,
//            modifier = Modifier.padding(dimensionResource(R.dimen.small))
//        )
//    }
//
//}
//
//@Composable
//private fun calLessonCardPos(
//    cardWidthDp: Int = 360,
//    lw: Float
//) : Int {
//    val scrWdp = LocalConfiguration.current.screenWidthDp
//    val newRight = cardWidthDp.toDouble() / scrWdp.toDouble()
//    val newLeft = 1.0 - newRight
//
//    val lwd = LessonUI
//        .map(lw.toDouble(), 0.0, 1.0, newLeft, newRight).toFloat()
//    return (cardWidthDp * lwd).toInt()
//}
