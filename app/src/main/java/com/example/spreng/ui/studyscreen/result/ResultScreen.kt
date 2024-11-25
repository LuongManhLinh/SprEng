package com.example.spreng.ui.studyscreen.result

import android.content.Intent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Button
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarColors
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.spreng.MainActivity
import com.example.spreng.R
import com.example.spreng.ui.custom.CustomRoundedBorderBox
import com.google.accompanist.systemuicontroller.rememberSystemUiController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ResultScreen(
    modifier: Modifier = Modifier,
    numCorrect: Int,
    numTotal: Int
) {
    val context = LocalContext.current

    val systemUiController = rememberSystemUiController()
    systemUiController.setSystemBarsColor(
        color = colorResource(R.color.container),
        darkIcons = true
    )

    Scaffold(
        topBar = {
            Box(
                modifier = Modifier.fillMaxWidth(),
                contentAlignment = Alignment.Center
            ) {
                CustomRoundedBorderBox(
                    cornerRadius = dimensionResource(R.dimen.medium),
                    bottomBorderWidth = dimensionResource(R.dimen.tiny),
                    containerColor = colorResource(R.color.teal_200),
                    modifier = Modifier.padding(dimensionResource(R.dimen.medium))
                ) {
                    Text(
                        text = "Tổng Kết Bài Học",
                        style = MaterialTheme.typography.displaySmall,
                        modifier = Modifier.padding(dimensionResource(R.dimen.medium))
                    )
                }
            }
        },
        bottomBar = {
            BottomAppBar(
                modifier = modifier,
                containerColor = Color.Transparent,
                contentPadding = PaddingValues(0.dp)
            ) {
                Box(
                    modifier = Modifier.fillMaxWidth(),
                    contentAlignment = Alignment.Center
                ) {
                    Button(
                        onClick = {
                            context.startActivity(Intent(context, MainActivity::class.java))
                        },
                        shape = RoundedCornerShape(dimensionResource(R.dimen.small)),
                        modifier = Modifier.width(300.dp)
                    ) {
                        Text(
                            text = "Về trang chủ"
                        )
                    }
                }
            }
        },

        containerColor = colorResource(R.color.container)

    ) {
        Column(
            modifier = modifier.padding(it).fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {


            ScoreCard(numCorrect = numCorrect, numTotal = numTotal)

            XpCard(xp = numCorrect * 10)



        }
    }
}

@Composable
private fun ScoreCard(
    modifier: Modifier = Modifier,
    numCorrect: Int,
    numTotal: Int
) {
    val correctRate = numCorrect.toFloat() / numTotal
    CustomRoundedBorderBox(
        cornerRadius = dimensionResource(R.dimen.medium),
        bottomBorderWidth = dimensionResource(R.dimen.tiny),
        modifier = modifier.padding(top = dimensionResource(R.dimen.medium)),
        containerColor = if (correctRate < 0.4f) {
            Color.Red
        } else if (correctRate < 0.7f) {
            Color.Yellow
        } else {
            Color.Green
        }
    ) {
        Text(
            text = "Kết quả của bạn: $numCorrect/$numTotal",
            style = MaterialTheme.typography.titleMedium,
            modifier = Modifier.padding(dimensionResource(R.dimen.medium))
        )
    }
}

@Composable
private fun XpCard(
    modifier: Modifier = Modifier,
    xp: Int
) {
    CustomRoundedBorderBox(
        cornerRadius = dimensionResource(R.dimen.medium),
        bottomBorderWidth = dimensionResource(R.dimen.tiny),
        modifier = modifier.padding(top = dimensionResource(R.dimen.medium)),
        containerColor = colorResource(R.color.teal_200)
    ) {
        Text(
            text = "Tổng xp nhận được: $xp",
            style = MaterialTheme.typography.titleMedium,
            modifier = Modifier.padding(dimensionResource(R.dimen.medium))
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun ResultScreenPreview() {
    ResultScreen(numCorrect = 1, numTotal = 10)
}
