package com.example.spreng.ui.mainscreen.ranking
import android.graphics.Paint.Align
import android.service.carrier.MessagePdu
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.material.icons.filled.ArrowBack
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
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.googlefonts.GoogleFont
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.spreng.R
import com.example.spreng.data.getRankCardItem
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.spreng.data.MainNavItem
import com.example.spreng.data.MainNavRoute
import com.example.spreng.data.NavRanking
import com.example.spreng.data.RankCardItem

@Composable
fun Title(
) {
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AllRankingScreen(navController: NavHostController) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Tất cả thứ hạng") },
                colors = TopAppBarDefaults.mediumTopAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer
                ),
                navigationIcon = {
                    IconButton(onClick = {
                        navController.navigate(MainNavRoute.RANKING.name)
                    }) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "ArrowBack"
                        )
                    }
                }
            )
        }
    ) { innerPadding ->
        DisplayRank(modifier = Modifier
            .padding(innerPadding)
            .fillMaxSize()
        )
    }

}

@Composable
fun DisplayRank(modifier: Modifier = Modifier) {
    val allRank = getRankCardItem()
    Surface(
        modifier =  modifier
            .fillMaxWidth()
            .background(MaterialTheme.colorScheme.background),
        color = MaterialTheme.colorScheme.background
    ) {
        LazyColumn(
            modifier = Modifier.padding(start = 8.dp, top = 24.dp, end = 8.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)) {
            items(allRank) { item ->
                RankCard(item)
                Log.i("Check", "11")
            }
        }
    }
}

@Composable
fun RankCard(rankCardItem: RankCardItem) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(75.dp)
            .clip(RoundedCornerShape(16.dp))
            .background(Color(205, 231, 236))
    ) {
        Image(
            painter = painterResource(rankCardItem.img),
            contentDescription = stringResource(rankCardItem.nameRank),
            modifier = Modifier.align(Alignment.CenterVertically)
        )
        Column(
            modifier = Modifier
                .align(Alignment.CenterVertically)
                .padding(start = 8.dp)
        ) {
            Text(
                text = stringResource(rankCardItem.nameRank),
                style = MaterialTheme.typography.titleLarge,
            )
            Text(
                text = stringResource(rankCardItem.descriptionRank),
                style = MaterialTheme.typography.titleSmall
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun Pre() {
    AllRankingScreen(navController = rememberNavController())
}