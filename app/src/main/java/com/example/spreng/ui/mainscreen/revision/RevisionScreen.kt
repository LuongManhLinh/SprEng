package com.example.spreng.ui.mainscreen.revision

import androidx.compose.foundation.Image
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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.spreng.R
import com.example.spreng.ui.custom.CustomRoundedBorderBox

@Composable
fun RevisionScreen(
    showMistakes: () -> Unit,
    showVocabs: () -> Unit,
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
                bottomBorderWidth = dimensionResource(R.dimen.small),
                containerColor = colorResource(R.color.teal_200),
                borderColor = colorResource(R.color.gray_teal)
            ) {
                RevisionTopBar()
            }
        },
        containerColor = colorResource(R.color.container)
    ) { contentPadding ->
        Column(
            modifier = Modifier
//                .background(colorResource(id = R.color.container))
                .padding(contentPadding)
                .padding(16.dp)
                .fillMaxSize(),
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
                    cornerRadius = dimensionResource(R.dimen.large),
                    bottomBorderWidth = 6.dp,
                    containerColor = Color(141, 189, 250),
                    borderColor = Color(180, 220, 255),
                ) {
                    Button(
                        onClick = { showMistakes() },
                        modifier = Modifier
                            .size(220.dp),
                        shape = RoundedCornerShape(dimensionResource(R.dimen.large)),
                        colors = ButtonDefaults.buttonColors(containerColor = Color(141, 189, 250))
                    ) {
                        Column(
                            modifier = modifier
                                .fillMaxSize()
                                .padding(8.dp),
                            verticalArrangement = Arrangement.Center,
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Image(
                                painter = painterResource(R.drawable.mistake),
                                contentDescription = "mistake",
                                modifier = modifier
                                    .padding(top = 4.dp)
                                    .size(82.dp)
                            )
                            Text(
                                text = "Lỗi sai",
                                color = Color.Black,
                                fontSize = 36.sp,
                            )
                        }
                    }
                }

                Spacer(modifier = Modifier.height(24.dp))

                CustomRoundedBorderBox (
                    cornerRadius = dimensionResource(R.dimen.large),
                    bottomBorderWidth = 6.dp,
                    containerColor = Color(131, 230, 201),
                    borderColor = Color(180, 225, 215),
                ) {
                    Button(
                        onClick = { showVocabs() },
                        modifier = Modifier
                            .size(220.dp),
                        shape = RoundedCornerShape(dimensionResource(R.dimen.large)),
                        colors = ButtonDefaults.buttonColors(containerColor = Color(131, 230, 201))
                    ) {
                        Column(
                            modifier = modifier
                                .fillMaxSize()
                                .padding(8.dp),
                            verticalArrangement = Arrangement.Center,
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Image(
                                painter = painterResource(R.drawable.vocab),
                                contentDescription = "vocab",
                                modifier = modifier
                                    .padding(top = 4.dp)
                                    .size(82.dp)
                            )
                            Text(
                                text = "Từ vựng",
                                color = Color.Black,
                                fontSize = 36.sp,
                            )
                        }
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
            .fillMaxWidth()
            .height(56.dp)
            .padding(dimensionResource(R.dimen.small)),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = "Ôn tập",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Black
        )
    }
}

@Preview
@Composable
fun PreviewRevision() {
    RevisionScreen({}, {})
}
