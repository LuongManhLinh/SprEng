package com.example.spreng.ui.mainscreen.revision

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.example.spreng.R

@Composable
fun ReviewMistakesScreen(
    modifier: Modifier = Modifier,
) {
    Scaffold(
        modifier = modifier,
        topBar = {
            ReviewMistakeTopBar()
        }
    ) { contentPadding ->
        LazyColumn (
            modifier = Modifier
                .padding(
                    top = contentPadding.calculateTopPadding(),
                )
                .fillMaxSize()
        ) {

        }
    }
}

@Composable
fun ReviewMistakeTopBar(
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .padding(dimensionResource(R.dimen.tiny))
            .height(dimensionResource(R.dimen.very_large))
            .fillMaxWidth()
            .clip(shape = RoundedCornerShape(dimensionResource(R.dimen.small)))
            .background(Color.LightGray)
            .padding(dimensionResource(R.dimen.small)),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically

        ) {
        Text(
            text = "Các lỗi sai",
            fontWeight = FontWeight.Bold,
            fontSize = 24.sp,
        )
    }
}

@Preview
@Composable
fun PreviewMistakes() {
    ReviewMistakesScreen()
}