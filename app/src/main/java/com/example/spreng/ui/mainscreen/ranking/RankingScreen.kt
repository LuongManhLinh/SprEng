package com.example.spreng.ui.mainscreen.ranking

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.spreng.R
import com.example.spreng.data.DefaultMainNavItemRepo
import com.example.spreng.data.MainNavRoute
import com.example.spreng.data.NavRanking
import com.example.spreng.preferences.UserManager
import com.example.spreng.ui.custom.CustomRoundedBorderBox
import kotlin.random.Random

@Composable
fun RankingScreen(
    modifier: Modifier = Modifier,
    showInfoUser: (Long) -> Unit,
    showAllRanking: () -> Unit,
    rank: String,
    rankingViewModel: RankingViewModel = viewModel(factory = RankingViewModel.factory),
) {
    val uiState by rankingViewModel.uiState.collectAsState()
    val context = LocalContext.current

    val currentUserId = UserManager.getUserId(context)
//    LaunchedEffect(rank) {
    rankingViewModel.fetchRanking(rank, currentUserId)
//    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = colorResource(R.color.container))
    ) {
        CustomRoundedBorderBox(
            modifier = modifier
                .padding(
                    top = dimensionResource(R.dimen.tiny),
                    start = dimensionResource(R.dimen.tiny),
                    end = dimensionResource(R.dimen.tiny)
                ),
            cornerRadius = dimensionResource(R.dimen.small),
            bottomBorderWidth = dimensionResource(R.dimen.small),
            containerColor = colorResource(R.color.teal_200),
            borderColor = colorResource(R.color.gray_teal)
        ) {
            TopBar(showAllRanking)
        }
        Spacer(Modifier.height(16.dp))
        HorizontalDivider(thickness = 2.dp, color = Color.LightGray)

        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(0.dp)
        ) {
            itemsIndexed(uiState) { index, user ->
                val isCurrentUser = user.id == currentUserId
                val containerColor = when {
                    isCurrentUser -> Color(0, 250, 154)
                    else -> Color(173, 216, 230)
                }

                val borderColor = when {
                    isCurrentUser -> Color(170, 240, 210)
                    else -> Color(200, 230, 245)
                }
                CustomRoundedBorderBox(
                    modifier = modifier
                        .padding(
                            top = dimensionResource(R.dimen.small),
                            start = dimensionResource(R.dimen.small_medium),
                            end = dimensionResource(R.dimen.small_medium)
                        ),
                    cornerRadius = 24.dp,
                    bottomBorderWidth = dimensionResource(R.dimen.tiny),
                    containerColor = containerColor,
                    borderColor = borderColor,
                ) {
                    Card(
                        stt = index + 1,
                        userName = user.username,
                        exp = user.xp,
                        showInfoUser = {
                            showInfoUser(user.id)
                        },
                    )
                }

                if (index + 1 == 10) {
                    Spacer(Modifier.height(8.dp))
                    Text(
                        text = "Nhóm lên hạng",
                        fontWeight = FontWeight.Bold,
                        fontSize = 34.sp,
                        color = Color(178, 0, 249),
                        textAlign = TextAlign.Center,
                        modifier = Modifier.fillMaxWidth()
                    )
                }
                if (index + 1 == 35) {
                    Spacer(Modifier.height(8.dp))
                    Text(
                        text = "Nhóm xuống hạng",
                        fontWeight = FontWeight.Bold,
                        fontSize = 34.sp,
                        color = Color(207, 23, 30),
                        textAlign = TextAlign.Center,
                        modifier = Modifier.fillMaxWidth()
                    )
                }
            }
        }

    }

}

@Composable
fun TopBar(
    showAllRanking: () -> Unit,
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(56.dp)
            .padding(horizontal = 8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Row(
            modifier = Modifier.weight(1f),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Hạng ",
                fontSize = 24.sp,
                modifier = Modifier.padding(end = 8.dp)
            )
            Image(
                painter = painterResource(R.drawable.diamond),
                contentDescription = "bronze",
                modifier = Modifier
                    .size(48.dp)
                    .clickable {
                        showAllRanking()
                    }
            )
        }

        Row(
            modifier = Modifier.weight(1f),
            horizontalArrangement = Arrangement.End,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(R.drawable.time),
                contentDescription = "time",
                modifier = Modifier
                    .size(24.dp)
                    .padding(end = 4.dp)
            )
            Text(
                text = "7 ngày",
                fontSize = 24.sp
            )
        }
    }

}

@Composable
fun Card(
    stt: Int,
    userName: String,
    exp: Int,
    showInfoUser: () -> Unit,
    modifier: Modifier = Modifier,
) {
    var colorIndex = Color.Black
    Row(
        modifier = modifier
            .fillMaxWidth()
            .height(56.dp)
            .padding(start = 4.dp, end = 8.dp)
            .clip(RoundedCornerShape(16.dp))
            .clickable {
                showInfoUser()
            },
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween // Đảm bảo các phần tử phân bổ đều trong Row
    ) {
        Text(
            text = stt.toString(),
            fontSize = 18.sp,
            color = colorIndex,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .weight(1f)
        )
        Image(
            painter = painterResource(R.drawable.sample_avatar),
            contentDescription = "avt",
            modifier = modifier.size(40.dp, 40.dp)
                .clip(RoundedCornerShape(20.dp))
        )
        Spacer(modifier.width(8.dp))
        Text(
            text = userName,
            fontSize = 24.sp,
            modifier = Modifier
                .weight(5f)
        )
        Box(
            modifier = Modifier
                .weight(3f)
                .align(Alignment.CenterVertically)
                .wrapContentWidth(Alignment.End)
        ) {
            Text(
                text = "$exp xp",
                fontSize = 18.sp,
                modifier = Modifier
                    .wrapContentWidth(Alignment.End)
            )
        }

    }


}

@Preview(showBackground = true)
@Composable
fun RankingPre() {
    RankingScreen(
        showInfoUser = { /* No-op */ },
        showAllRanking = { /* No-op */ },
        rank = "Bronze"
    )
}


