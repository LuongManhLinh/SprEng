package com.example.spreng.ui.mainscreen.revision

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.spreng.R
import com.example.spreng.data.RevisionRoute
import com.example.spreng.ui.custom.CustomRoundedBorderBox

@Composable
fun RevisionScreen(
    navController: NavController,
    modifier: Modifier = Modifier
) {
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
                RevisionTopBar()
            }
        }
    ) { contentPadding ->
        Column(
            modifier = Modifier
                .padding(contentPadding)
                .padding(16.dp)
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(16.dp))
            Box(modifier = Modifier
                .fillMaxWidth(),
                contentAlignment = Alignment.Center) {
                Text(
                    text = "Ôn tập những gì bạn đã học nhé",
                    fontSize = 24.sp,
                    color = Color.Black
                )
            }
            Spacer(modifier = Modifier.height(32.dp))
            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                CustomRoundedBorderBox (
                    cornerRadius = 8.dp,
                    bottomBorderWidth = 8.dp,
                    borderColor = Color.Gray,
//                    containerColor = Color(199, 210, 254)
                ) {
                    Button(
                        onClick = {
                            navController.navigate(RevisionRoute.MISTAKE.name)
                        },
                        modifier = Modifier
                            .size(240.dp),
                        shape = RoundedCornerShape(8.dp),
                        colors = ButtonDefaults.buttonColors(containerColor = Color(199, 210, 254))
                    ) {
                        Text(
                            text = "Lỗi sai",
                            color = Color.Black,
                            fontSize = 36.sp
                        )
                    }
                }

                Spacer(modifier = Modifier.height(24.dp))

                CustomRoundedBorderBox (
                    cornerRadius = 8.dp,
                    bottomBorderWidth = 8.dp,
                    borderColor = Color.Gray
                ) {
                    Button(
                        onClick = {
                            navController.navigate(RevisionRoute.VOCAB.name)
                        },
                        modifier = Modifier
                            .size(240.dp),
                        shape = RoundedCornerShape(8.dp),
                        colors = ButtonDefaults.buttonColors(containerColor = Color(199, 210, 254))

                    ) {
                        Text(
                            text = "Từ vựng",
                            color = Color.Black,
                            fontSize = 36.sp
                        )
                    }
                }
            }
        }
    }
}

@Composable
private fun RevisionTopBar(modifier: Modifier = Modifier) {
    Row(
        modifier = modifier
            .clip(shape = RoundedCornerShape(dimensionResource(R.dimen.small)))
            .background(Color.LightGray)
            .fillMaxWidth()
            .height(dimensionResource(R.dimen.very_large))
            .padding(dimensionResource(R.dimen.small)),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = "Ôn tập",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold
        )
    }
}

@Preview
@Composable
fun PreviewRevision() {
    RevisionScreen(navController = rememberNavController())
}
