package com.example.spreng.ui.mainscreen.ranking

import android.annotation.SuppressLint
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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Block
import androidx.compose.material.icons.filled.GroupAdd
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
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
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.spreng.R
import com.example.spreng.preferences.UserManager
import com.example.spreng.ui.custom.CustomRoundedBorderBox
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


@SuppressLint("SuspiciousIndentation")
@Composable
fun InfoUserScreen(
    visitedUserId: Long,
    showRankingScreen: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: InfoUserViewModel = viewModel(factory = InfoUserViewModel.factory)
) {
    val context = LocalContext.current
    val currentUserId = UserManager.getUserId(context)
    val uiState by viewModel.userDetail.collectAsState()
    
    val isLoading by viewModel.isLoading.collectAsState()

    LaunchedEffect(uiState.isFollow) {
        viewModel.fetchUserDetail(context, visitedUserId)
    }
    val coroutineScope = rememberCoroutineScope()

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(color = colorResource(R.color.container))
    ) {
        uiState.let {
            CustomRoundedBorderBox(
                modifier = Modifier
                    .padding(
                        top = dimensionResource(R.dimen.tiny),
                        start = dimensionResource(R.dimen.tiny),
                        end = dimensionResource(R.dimen.tiny)
                    ),
                cornerRadius = dimensionResource(R.dimen.medium),
                bottomBorderWidth = 6.dp,
                contentHeightDp = 100.dp,
                containerColor = Color(135, 183, 239),
                borderColor = Color(185, 215, 245),
            ) {
                Header(
                    username = it.username,
                    email = it.email,
                    showRankingScreen = showRankingScreen
                )
            }
        }
        Spacer(Modifier.height(16.dp))
        HorizontalDivider(thickness = 2.dp, color = Color.Gray)
        Spacer(Modifier.height(16.dp))
        uiState.let {
            DetailScreen(
                streak = it.streak.toString(),
                xp = it.xp.toString(),
                rank = it.rank,
                top3count = it.top3Count.toString(),
                coroutineScope = coroutineScope,
                follow = it.followers.size,
                followed = it.followedUsers.size,
                isFollow = it.isFollow,
                isLoading = isLoading,
                onAddFollowClick = {
                    Log.d("FollowClick", "Add follow clicked")

                    if (!isLoading) viewModel.addFollow(context, visitedUserId)
                },
                onDeleteFollowClick = {
                    Log.d("FollowClick", "Delete follow clicked")

                    if (!isLoading) viewModel.unfollow(context, visitedUserId)
                },
                showButtonAddFollow = currentUserId != visitedUserId
            )
        }
    }
}

@Composable
fun DetailScreen(
    streak: String,
    xp: String,
    rank: String,
    top3count: String,
    coroutineScope: CoroutineScope,
    follow: Int,
    followed: Int,
    isFollow: Boolean,
    isLoading: Boolean,
    onAddFollowClick: () -> Unit,
    onDeleteFollowClick: () -> Unit,
    showButtonAddFollow: Boolean,
) {
    var isPressed by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .padding(8.dp)
    ) {
      
        Text(
            text = "Đã tham gia vào ngày 20/11/2024",
            fontSize = 22.sp,
            color = Color.Black,
            modifier = Modifier
                .padding(start = 8.dp, top = 16.dp, bottom = 16.dp)
                .align(Alignment.CenterHorizontally)
        )
        Follower(follow, followed)
        if (showButtonAddFollow) {
            Box(
                modifier = Modifier
                    .padding(top = 16.dp)
                    .width(190.dp)
                    .height(64.dp)
                    .align(Alignment.CenterHorizontally)
            ) {
                CustomRoundedBorderBox(
                  modifier = Modifier
                    .padding(
                        top = if (isPressed) {
                            4.dp
                        } else {
                            0.dp
                        }
                    ),
                    cornerRadius = 28.dp,
                  bottomBorderWidth = if(isPressed) 0.dp else 4.dp,
                    borderColor = Color(120, 240, 230),
                    containerColor = colorResource(R.color.teal_200)
                ) {
                    Button(
                        onClick = {
                          isPressed = true;
                        coroutineScope.launch {
                            delay(125)
                            isPressed = false
                            if (!isLoading) {
                                if (!isFollow) onAddFollowClick()
                                else onDeleteFollowClick()
                            }
                        }
                            
                        },
                        modifier = Modifier.fillMaxWidth(),
                        colors = ButtonDefaults.buttonColors(containerColor = colorResource(R.color.teal_200)),
                        enabled = !isLoading
                    ) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Icon(
                                imageVector = Icons.Filled.GroupAdd,
                                contentDescription = "Add friend",
                                tint = Color.Black,
                                modifier = Modifier.padding(end = 8.dp)
                            )
                            Text(
                                text = if (!isFollow) "Theo dõi" else "Huỷ",
                                color = Color.Black,
                                fontSize = 18.sp
                            )
                        }
                    }
                }
            }
        }

        Text(
            text = "Thống kê",
            fontSize = 32.sp,
            color = Color.Black,
            modifier = Modifier
                .padding(8.dp)
                .align(Alignment.CenterHorizontally)
        )
        Spacer(Modifier.height(16.dp))
        Row {
            Card(streak, "Streak", R.drawable.streak, modifier = Modifier.weight(1f))
            Card(xp, "Tổng Xp", R.drawable.xp, modifier = Modifier.weight(1f))
        }
        Row {
            Card(rank, "Hạng", R.drawable.bronze, modifier = Modifier.weight(1f))
            Card(top3count, "Số lần top 3", R.drawable.medal, modifier = Modifier.weight(1f))
        }
    }
}

@Composable
fun Header(
    username: String,
    email: String,
    showRankingScreen: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(4.dp)
    ) {
        IconButton(
            onClick = { showRankingScreen() },
            modifier = Modifier.align(Alignment.CenterVertically)
        ) {
            Icon(
                imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                contentDescription = "ArrowBack",
                tint = Color.Black
            )
        }

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 8.dp)
        ) {

            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    painter = painterResource(R.drawable.sample_avatar),
                    modifier = Modifier
                        .size(50.dp)
                        .clip(CircleShape)
                        .border(1.dp, Color.Gray, CircleShape),
                    contentScale = ContentScale.Crop,
                    contentDescription = "avt"
                )
                Spacer(modifier = Modifier.width(8.dp))
                Column(modifier = Modifier.weight(6f)) {
                    Text(
                        text = username,
                        fontSize = 22.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.Black
                    )
                    Text(
                        text = email,
                        fontSize = 16.sp,
                        color = Color.Black
                    )
                }
                IconButton(
                    onClick = {},
                    modifier = Modifier.weight(1f)
                ) {
                    Icon(
                        imageVector = Icons.Filled.Block,
                        contentDescription = "Block user",
                        tint = Color.Black
                    )
                }

            }
        }
    }

}

@Composable
fun Card(amount: String, type: String, img: Int, modifier: Modifier = Modifier) {
    CustomRoundedBorderBox(
        modifier = modifier.padding(8.dp),
        cornerRadius = 24.dp,
        bottomBorderWidth = 4.dp,
        contentHeightDp = 90.dp,
        contentWidthDp = 180.dp,
        containerColor = Color(95, 210, 185),
        borderColor = Color(207, 244, 210),
    )
    {
        Row(
            modifier = modifier
                .padding(8.dp)
                .fillMaxWidth()
        ) {
            Image(
                painter = painterResource(img),
                contentDescription = "streak",
                modifier = Modifier
                    .size(42.dp)
                    .padding(4.dp)

            )
            Column() {
                Text(
                    text = amount,
                    color = Color.Black,
                    fontSize = 18.sp,
                    modifier = Modifier.padding(4.dp)
                )
                Text(
                    text = type,
                    color = Color.Black,
                    fontSize = 18.sp,
                    modifier = Modifier.padding(4.dp)
                )
            }
        }
    }
}

@Composable
fun Follower(follow: Int, followed: Int) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = "Đang theo dõi $follow",
            color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.7f)
        )
        Spacer(modifier = Modifier.width(8.dp))
        Text(
            text = "Được theo dõi $followed",
            color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.7f)
        )
    }
}
//@Preview(showBackground = true)
//@Composable
//fun Preview() {
//    InfoUserScreen({})
//}
