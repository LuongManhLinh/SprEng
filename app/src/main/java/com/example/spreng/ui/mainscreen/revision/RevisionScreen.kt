package com.example.spreng.ui.mainscreen.revivion

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.spreng.R
import com.example.spreng.data.RevisionRoute

@Composable
fun RevisionScreen(
    navController: NavController,
    modifier: Modifier = Modifier
) {
    Scaffold(
        modifier = modifier,
        topBar = {
            RevisionTopBar()
        }
    ) { contentPadding ->
        Column(
            modifier = Modifier
                .padding(contentPadding)
                .padding(16.dp)
        ) {
            Button(
                onClick = {
                    navController.navigate(RevisionRoute.MISTAKE.name) {
                        launchSingleTop = true
                        restoreState = true
                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                Text("Xem lại lỗi sai")
            }

            Button(
                onClick = {
                    navController.navigate(RevisionRoute.VOCAB.name) {
                        launchSingleTop = true
                        restoreState = true
                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                Text("Ôn lại từ vựng")
            }
        }
    }
}

@Composable
private fun RevisionTopBar(modifier: Modifier = Modifier) {
    Row(
        modifier = modifier
            .padding(dimensionResource(R.dimen.tiny))
            .clip(shape = RoundedCornerShape(dimensionResource(R.dimen.small)))
            .background(Color.LightGray)
            .padding(dimensionResource(R.dimen.small))
            .fillMaxWidth()
    ) {
        Text(
            text = "Ôn tập"
        )
    }
}

@Preview
@Composable
fun PreviewRevision() {
    RevisionScreen(navController = rememberNavController())
}
