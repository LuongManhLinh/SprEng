package com.example.spreng.ui.mainscreen.ranking

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.spreng.R
import com.example.spreng.data.RankCardItem
import com.example.spreng.data.getRankCardItem
import com.example.spreng.ui.custom.CustomRoundedBorderBox

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AllRankingScreen(
//    navController: NavHostController,
    showRankingScreen: () -> Unit
) {
    Scaffold(
        topBar = {
            CustomRoundedBorderBox(
                modifier = Modifier
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
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(dimensionResource(R.dimen.small))
                ) {
                    // arrowback nằm bên trái
                    IconButton(
                        onClick = showRankingScreen,
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
                        text = "Tất cả thứ hạng",
                        fontWeight = FontWeight.Bold,
                        fontSize = 24.sp,
                        color = Color.Black,
                        modifier = Modifier
                            .align(Alignment.Center)
                    )
                }
            }
        },
        containerColor = colorResource(R.color.container)
    ) { innerPadding ->
        DisplayRank(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
        )
    }
}

@Composable
fun DisplayRank(modifier: Modifier = Modifier) {
    val allRank = getRankCardItem()
    Surface(
        modifier = modifier
            .fillMaxSize(),
        color = colorResource(R.color.container)
    ) {
        LazyColumn(
            modifier = Modifier
                .padding(start = 16.dp, top = 24.dp, end = 16.dp)
                .background(colorResource(R.color.container)),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            items(allRank) { item ->

                RankCard(item)
                Log.i("Check", "11")
            }
        }
    }
}

@Composable
fun RankCard(rankCardItem: RankCardItem) {
    val containerColor = when (rankCardItem.nameRank) {
        R.string.bronze_rank -> Color(255, 200, 120)
        R.string.silver_rank -> Color(210, 210, 230)
        R.string.gold_rank -> Color(255, 205, 80)
        R.string.platinum_rank -> Color(255, 180, 180)
        R.string.diamond_rank -> Color(180, 220, 255)

        else -> colorResource(R.color.container)
    }
    val backgroundColor = when (rankCardItem.nameRank) {
        R.string.bronze_rank -> Color(255, 220, 180)
        R.string.silver_rank -> Color(220, 220, 220)
        R.string.gold_rank -> Color(255, 235, 128)
        R.string.platinum_rank -> Color(255, 190, 190)
        R.string.diamond_rank -> Color(200, 230, 255)

        else -> colorResource(R.color.container)
    }

    CustomRoundedBorderBox(
        modifier = Modifier
            .padding(
                top = dimensionResource(R.dimen.tiny),
                start = dimensionResource(R.dimen.tiny),
                end = dimensionResource(R.dimen.tiny)
            ),
        cornerRadius = dimensionResource(R.dimen.medium),
        bottomBorderWidth = 6.dp,
        containerColor = containerColor,
        borderColor = backgroundColor,
        contentHeightDp = 75.dp
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(16.dp))
        ) {
            Image(
                painter = painterResource(rankCardItem.img),
                contentDescription = stringResource(rankCardItem.nameRank),
                modifier = Modifier
                    .padding(start = 8.dp)
                    .align(Alignment.CenterVertically)
                    .size(50.dp)
            )
            Column(
                modifier = Modifier
                    .align(Alignment.CenterVertically)
                    .padding(start = 8.dp)
            ) {
                Text(
                    text = stringResource(rankCardItem.nameRank),
                    style = MaterialTheme.typography.titleLarge,
                    color = Color.Black
                )
                Text(
                    text = stringResource(rankCardItem.descriptionRank),
                    style = MaterialTheme.typography.titleSmall,
                    color = Color.Black
                )
            }
        }
    }


}

