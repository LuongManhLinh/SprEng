package com.example.spreng.ui.mainscreen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.spreng.R

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
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
            items(uiState.lessonList) { lesson ->

                val interactionSource = remember { MutableInteractionSource() }
                val isPressed = interactionSource.collectIsPressedAsState().value

                Row(
                    modifier = Modifier.padding(dimensionResource(R.dimen.medium))
                ) {
                    Spacer(Modifier.weight(lesson.leftWeight))

                    Button(
                        onClick = {},
                        interactionSource = interactionSource,
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color.Transparent
                        ),
                        shape = RectangleShape,
                        contentPadding = PaddingValues(0.dp)
                    ) {
                        Image(
                            painter = painterResource(
                                if (isPressed) {
                                    R.drawable.lesson_ic_pressed
                                } else {
                                    R.drawable.lesson_ic
                                }
                            ),
                            contentDescription = null,
                        )
                    }


                    Spacer(Modifier.weight(lesson.rightWeight))
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
            painter = painterResource(R.drawable.xp),
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



@Preview(showBackground = true)
@Composable
private fun Preview() {
    HomeScreen(viewModel = HomeViewModel())
}