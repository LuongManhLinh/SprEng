package com.example.spreng.ui.mainscreen.ranking

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.spreng.R
import com.example.spreng.data.DefaultMainNavItemRepo
import com.example.spreng.data.MainNavRoute
import com.example.spreng.data.NavRanking
import kotlin.random.Random

@Composable
fun RankingScreen(
//    navController: NavHostController,
    modifier: Modifier = Modifier,
    showInfoUser: () -> Unit,
    showAllRanking: () -> Unit
) {
    val listCard: MutableList<userCard> = mutableListOf()
    Column(modifier = Modifier.fillMaxSize().padding(top = 8.dp)) {
        TopBar(showAllRanking)
        Spacer(Modifier.height(16.dp))
        HorizontalDivider(thickness = 2.dp)
        Spacer(Modifier.height(16.dp))

        // sort
        for (i in 1..50) {
            val randomExp = Random.nextInt(10, 201)
            listCard.add(userCard("Nguoi thu $i", randomExp))
        }
        listCard.sortByDescending { it.xp }
        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            itemsIndexed(listCard) { index, user ->
                Card(
//                    navController,
                    stt = index + 1,
                    userName = user.userName,
                    exp = user.xp,
                    showInfoUser = {showInfoUser()} ,
                    )
//                HorizontalDivider(thickness = 2.dp)
                if (index + 1 == 10) {
                    Spacer(Modifier.height(8.dp))
                    Text(
                        text = "Nhóm lên hạng",
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
    showAllRanking: () -> Unit
) {
    Row(modifier = Modifier.fillMaxWidth()) {
        Text(
            text = "Hạng ",
            fontSize = 36.sp,
            modifier = Modifier
                .padding(start = 8.dp, top = 8.dp, bottom = 8.dp)
        )
        Image(
            painter = painterResource(R.drawable.diamond),
            contentDescription = "bronze",
            modifier = Modifier.size(60.dp)
                .align(Alignment.CenterVertically)
                .padding(start = 0.dp)
                .clickable {
//                    navController.navigate(NavRanking.AllRank.name)
                    showAllRanking()
                }
        )
        Spacer(Modifier.width(132.dp))
        Image(
            painter = painterResource(R.drawable.time),
            contentDescription = "time",
            modifier = Modifier
                .size(24.dp, 24.dp)
        )
        Text(
            text = "7 days",
            fontSize = 24.sp,
            modifier = Modifier
        )
    }
}

@Composable
fun Card(
//    navController: NavHostController,
    stt: Int,
    userName: String,
    exp: Int,
    showInfoUser: () -> Unit,
    modifier: Modifier = Modifier
) {
    var colorIndex: Color
    if (stt <= 10)
        colorIndex = Color(178, 0, 249)
    else if (stt <= 35)
        colorIndex = Color(88, 95, 99)
    else
        colorIndex = Color(207, 23, 30)
    Row(modifier = modifier
        .fillMaxWidth()
        .height(60.dp)
        .padding(start = 8.dp, end = 8.dp)
        .clip(RoundedCornerShape(16.dp))
        .background(Color(205, 231, 236))
        .clickable{
//            navController.navigate(NavRanking.Profile)
            showInfoUser()
        },
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = stt.toString(),
            fontSize = 24.sp,
            color = colorIndex,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .weight(1f)
        )
        Image(
            painter = painterResource(R.drawable.time),
            contentDescription = "avt",
            modifier = modifier.size(40.dp, 40.dp)
        )
        Spacer(modifier.width(8.dp))
        Text(
            text = userName,
            fontSize = 24.sp,
            modifier = Modifier
                .weight(5f)
        )
        Text(
            text = exp.toString() + " xp",
            fontSize = 24.sp,
            modifier = Modifier
                .weight(2f)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun RankingPre() {
//    RankingScreen(navController = rememberNavController())
}

data class userCard(
    val userName: String,
    val xp: Int
)

