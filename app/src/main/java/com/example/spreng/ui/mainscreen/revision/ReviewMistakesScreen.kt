package com.example.spreng.ui.mainscreen.revision

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
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
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.spreng.R
import com.example.spreng.data.MainNavRoute
import com.example.spreng.ui.custom.CustomRoundedBorderBox

@Composable
fun ReviewMistakesScreen(
    navController: NavHostController,
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
                cornerRadius = dimensionResource(R.dimen.small),
                startBorderWidth = dimensionResource(R.dimen.tiny),
                bottomBorderWidth = dimensionResource(R.dimen.small),
//                containerColor = Color.LightGray,
                borderColor = Color.Gray
            ) {
                ReviewMistakeTopBar(
                    navController = navController,
                    numbOfMistake = mistakeList.size
                )
            }
        }
    ) { contentPadding ->
        //hiển thị các lỗi sai
        LazyColumn(
            modifier = Modifier
                .padding(top = contentPadding.calculateTopPadding())
                .fillMaxSize()
                .padding(dimensionResource(R.dimen.small_medium))
                .border(
                    1.dp,
                    Color.Black,
                    shape = RoundedCornerShape(dimensionResource(R.dimen.medium))
                )
                .clip(shape = RoundedCornerShape(dimensionResource(R.dimen.medium)))
                .fillMaxSize()
        ) {
            items(mistakeList) { mistake ->
                Column() {
                    MistakeItem(
                        mistake = mistake
                    )
                    HorizontalDivider(color = Color.Black, thickness = 1.dp)
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
//            .background(Color.Cyan)
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
                fontSize = 18.sp
            )
            Text(
                text = mistake.question,
                fontSize = 22.sp
            )
        }
    }
}

//top bar
@Composable
fun ReviewMistakeTopBar(
    modifier: Modifier = Modifier,
    navController: NavHostController,
    numbOfMistake: Int
) {
    Box(
        modifier = modifier
            .height(dimensionResource(R.dimen.very_large))
            .fillMaxWidth()
            .background(MaterialTheme.colorScheme.primaryContainer)
            .padding(dimensionResource(R.dimen.small))
    ) {
        // arrowback nằm bên trái
        IconButton(
            onClick = {
                navController.navigate(MainNavRoute.REVISION.name)
            },
            modifier = Modifier.align(Alignment.CenterStart)
        ) {
            Icon(
                imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                contentDescription = "Quay lại"
            )
        }

        // số lượng lỗi sai nằm giữa
        Text(
            text = "$numbOfMistake lỗi sai",
            fontWeight = FontWeight.Bold,
            fontSize = 24.sp,
            modifier = Modifier.align(Alignment.Center)
        )
    }
}

@Preview
@Composable
fun PreviewMistakes() {
    ReviewMistakesScreen(navController = rememberNavController())
}