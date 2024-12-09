package com.example.spreng.ui.mainscreen.revision

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.spreng.R
import com.example.spreng.ui.custom.CustomRoundedBorderBox

@Composable
fun ReviewMistakesScreen(
    showRevision: () -> Unit,
    modifier: Modifier = Modifier,
    mistakeViewModel: MistakeViewModel = viewModel()
) {
    val mistakeList by mistakeViewModel.mistakeList.collectAsState()
    Scaffold(
        modifier = modifier,
        topBar = {
            CustomRoundedBorderBox(
                modifier = modifier
                    .padding(
                        top = dimensionResource(R.dimen.tiny),
                        start = dimensionResource(R.dimen.tiny),
                        end = dimensionResource(R.dimen.tiny)
                    ),
                cornerRadius = dimensionResource(R.dimen.medium),
                bottomBorderWidth = 6.dp,
                containerColor = Color(135, 183, 239),
                borderColor = Color(160, 171, 200),
                contentHeightDp = 64.dp
            ) {
                ReviewMistakeTopBar(
                    showRevision = { showRevision() },
                    numbOfMistake = mistakeList.size
                )
            }
        },
        containerColor = colorResource(R.color.container)

    ) { contentPadding ->
        //hiển thị các lỗi sai
        Column(
            modifier = Modifier
                .padding(top = contentPadding.calculateTopPadding())
                .fillMaxSize()
                .padding(dimensionResource(R.dimen.small_medium))

        ) {
            LazyColumn(
                modifier = Modifier
                    .border(
                        1.dp,
                        Color.DarkGray,
                        shape = RoundedCornerShape(dimensionResource(R.dimen.medium_large))
                    )
                    .fillMaxSize()
                    .clip(RoundedCornerShape(dimensionResource(R.dimen.medium_large)))
                    .background(Color(245, 245, 245))
            ) {
                items(mistakeList) { mistake ->
                    Column() {
                        MistakeItem(
                            mistake = mistake
                        )
                        HorizontalDivider(color = Color.LightGray, thickness = 1.dp)
                    }
                }
            }

        }
    }
}

//giao diện hiển thị một lỗi sai
@Composable
fun MistakeItem(
    mistake: Mistake,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .padding(top = 16.dp, bottom = 16.dp, start = 8.dp, end = 8.dp)
            .fillMaxWidth()
    ) {
        Column(
            modifier = Modifier
                .weight(10f)
                .padding(start = 8.dp)
        ) {
            Text(
                text = mistake.questionDescription,
                fontSize = 18.sp,
                color = Color.Black
            )
            Text(
                text = mistake.question,
                fontSize = 22.sp,
                color = Color.Black
            )
        }
    }
}

//top bar
@Composable
fun ReviewMistakeTopBar(
    modifier: Modifier = Modifier,
    showRevision: () -> Unit,
    numbOfMistake: Int
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .padding(dimensionResource(R.dimen.small))
    ) {
        // arrowback nằm bên trái
        IconButton(
            onClick = showRevision,
            modifier = Modifier.align(Alignment.CenterStart)
        ) {
            Icon(
                imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                contentDescription = "Quay lại",
                tint = Color.Black
            )
        }

        // số lượng lỗi sai nằm giữa
        Text(
            text = "$numbOfMistake lỗi sai",
            fontWeight = FontWeight.Bold,
            fontSize = 24.sp,
            color = Color.Black,
            modifier = Modifier
                .align(Alignment.Center)
        )
    }
}

@Preview
@Composable
fun PreviewMistakes() {
    ReviewMistakesScreen({})
}