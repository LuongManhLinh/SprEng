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
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.spreng.MainActivity
import com.example.spreng.R
import com.example.spreng.ui.custom.CustomRoundedBorderBox

@Composable
fun ResultScreen(
    modifier: Modifier = Modifier,
    numCorrect: Int,
    numTotal: Int
) {
    val context = LocalContext.current
    Scaffold(
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
        }
    ) {
        Column(
            modifier = modifier.padding(it).fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            CustomRoundedBorderBox(
                cornerRadius = dimensionResource(R.dimen.medium),
                bottomBorderWidth = dimensionResource(R.dimen.tiny)
            ) {
                Text(
                    text = "Congratulations!",
                    style = MaterialTheme.typography.displayMedium,
                    modifier = Modifier.padding(dimensionResource(R.dimen.medium))
                )
            }

            CustomRoundedBorderBox(
                cornerRadius = dimensionResource(R.dimen.medium),
                bottomBorderWidth = dimensionResource(R.dimen.tiny),
                modifier = Modifier.padding(top = dimensionResource(R.dimen.medium))
            ) {
                Text(
                    text = "Kết quả của bạn: $numCorrect/$numTotal",
                    style = MaterialTheme.typography.titleMedium,
                    modifier = Modifier.padding(dimensionResource(R.dimen.medium))
                )
            }

            CustomRoundedBorderBox(
                cornerRadius = dimensionResource(R.dimen.medium),
                bottomBorderWidth = dimensionResource(R.dimen.tiny),
                modifier = Modifier.padding(top = dimensionResource(R.dimen.medium))
            ) {
                Text(
                    text = "Tổng xp nhận được: ${numCorrect * 10}",
                    style = MaterialTheme.typography.titleMedium,
                    modifier = Modifier.padding(dimensionResource(R.dimen.medium))
                )
            }
        }
    }

}

@Preview(showBackground = true)
@Composable
private fun ResultScreenPreview() {
    ResultScreen(numCorrect = 5, numTotal = 10)
}
