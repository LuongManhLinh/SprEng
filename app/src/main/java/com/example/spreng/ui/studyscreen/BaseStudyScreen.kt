package com.example.spreng.ui.studyscreen

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.spreng.R

/**
 * Base screen for all study screens
 * @param contentComposable is a composable function representing the content of the question
 */
@Composable
fun BaseStudyScreen(
    onCancelling: () -> Unit,
    modifier: Modifier = Modifier,
    contentComposable: (@Composable () -> Unit)
) {
    Scaffold(
        topBar = { StudyTopBar(onCancelling) },
        bottomBar = { StudyBottomBar() },
        modifier = modifier
    ) { innerPadding ->
        Box(
            modifier = Modifier.padding(innerPadding).fillMaxSize()
        ) {
            contentComposable()
        }

    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun StudyTopBar(
    onCancelling: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
    ) {
        TopAppBar(
            title = {
                LinearProgressIndicator(
                    progress = { 0.5f },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(16.dp)
                        .padding(end = 8.dp)
                        .clip(RoundedCornerShape(dimensionResource(R.dimen.large)))
                        .border(1.dp, Color.Black, RoundedCornerShape(dimensionResource(R.dimen.large)))
                    ,
                    color = Color(20, 200, 20),
                    trackColor = Color.LightGray,
                    strokeCap = StrokeCap.Round
                )
            },

            navigationIcon = {
                IconButton(onClick = onCancelling) {
                    Icon(Icons.Filled.Close, contentDescription = "Close")
                }
            }
        )
        HorizontalDivider(
            thickness = dimensionResource(R.dimen.very_tiny),
            color = Color.Gray
        )
    }


}

@Composable
private fun StudyBottomBar(
    modifier: Modifier = Modifier
) {

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
                onClick = { },
                shape = RoundedCornerShape(dimensionResource(R.dimen.small)),
                modifier = Modifier.width(300.dp)
            ) {
                Text("Hoàn thành")
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
private fun BaseStudyScreenPreview() {
    BaseStudyScreen(onCancelling = {}, contentComposable = { })
}