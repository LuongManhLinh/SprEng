package com.example.spreng.ui.mainscreen.ranking

import android.provider.ContactsContract.CommonDataKinds.Email
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Block
import androidx.compose.material.icons.filled.GroupAdd
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.spreng.R


@Composable
fun InfoUserScreen(
    userId: Long,
    showRankingScreen: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: InfoUserViewModel = viewModel(factory = InfoUserViewModel.factory)
) {
    val uiState by viewModel.userDetail.collectAsState()
//    LaunchedEffect(Unit) {
        viewModel.fetchUserDetail(userId)
//    }

    Column(modifier = modifier.fillMaxSize()) {
        uiState?.let {
            Header(
                username = it.username,
                email = it.email,
                showRankingScreen = showRankingScreen
            )
        }
        Spacer(Modifier.height(16.dp))
        HorizontalDivider(thickness = 2.dp, color = Color.Gray)
        Spacer(Modifier.height(16.dp))
        uiState?.let {
            DetailScreen(
                streak = it.streak.toString(),
                xp = it.xp.toString(),
                rank = it.rank.toString(),
                top3count = it.top3Count.toString()
            )
        }
    }

}

@Composable
fun Header(
    username: String,
    email: String,
    showRankingScreen: () -> Unit
) {
    Box() {
        Column(modifier = Modifier.fillMaxWidth()) {
            Surface(
                modifier = Modifier
                    .background(Color(198, 215, 235))
                    .padding(top = 75.dp),
                color = Color(198, 215, 235)
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Image(
                        painter = painterResource(R.drawable.sample_avatar),
                        modifier = Modifier
                            .padding(8.dp)
                            .size(64.dp)
                            .clip(CircleShape)
                            .weight(2f),
                        contentScale = ContentScale.Crop,
                        contentDescription = null
                    )
                    Column(modifier = Modifier.weight(6.5f)) {
                        Text(username, fontSize = 24.sp)
                        Text(email)
                    }
                    IconButton(
                        onClick = {},
                        modifier = Modifier.weight(1.5f)
                    ) {
                        Icon(
                            imageVector = Icons.Filled.Block,
                            contentDescription = "block"
                        )
                    }
                }
            }

            Spacer(Modifier.height(20.dp))
            Text(
                text = "Đã tham gia vào ngày 20/11/2024",
                modifier = Modifier.padding(bottom = 16.dp)
            )
            Button(
                onClick = {},
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
            ) {
                Row() {
                    Icon(
                        imageVector = Icons.Filled.GroupAdd,
                        contentDescription = "add friend",
                        modifier = Modifier.padding(end = 8.dp)
                    )
                    Text("Theo dõi")
                }
            }
        }
        IconButton(
            onClick = {
                showRankingScreen()
            },
            modifier = Modifier
                .align(Alignment.TopStart)
                .padding(8.dp)
        ) {
            Icon(
                imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                contentDescription = "ArrowBack"
            )
        }
    }
}

@Composable
fun DetailScreen(streak: String, xp: String, rank: String, top3count: String) {
    Column() {
        Text(
            text = "Thống kê",
            fontSize = 32.sp
        )
        Row() {
            Card(streak, "Streak", R.drawable.streak,modifier = Modifier.weight(1f))
            Card(xp, "Tổng Xp", R.drawable.xp, modifier = Modifier.weight(1f))
        }
        Row() {
            Card(rank, "Hạng", R.drawable.bronze, modifier = Modifier.weight(1f))
            Card(top3count, "Số lần top 3", R.drawable.medal, modifier = Modifier.weight(1f))
        }
    }
}

@Composable
fun Card(amount: String, type: String, img: Int, modifier: Modifier = Modifier) {
    Surface(
        modifier = modifier.fillMaxWidth().padding(8.dp),
        color = Color(207, 244, 210),
        shape = RoundedCornerShape(16.dp)
    ) {
        Row(
            modifier = modifier.padding(8.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center,
        ) {
            Image(
                painter = painterResource(img),
                contentDescription = "streak",
                modifier = Modifier.size(42.dp).padding(4.dp)
            )
            Column() {
                Text(
                    text = amount,
                    modifier = Modifier.padding(4.dp)
                )
                Text(
                    text = type,
                    modifier = Modifier.padding(4.dp)
                )
            }
        }
    }
}

//@Preview(showBackground = true)
//@Composable
//fun Preview() {
//    InfoUserScreen({})
//}
