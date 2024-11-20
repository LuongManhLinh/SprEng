package com.example.spreng.ui.studyscreen.answer.writing

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.spreng.R

@Composable
fun BaseWritingScreen(
    modifier: Modifier = Modifier,
//    baseWritingScreenViewModel: BaseWritingScreenViewModel = viewModel(),
    inputAnswer: String,
    saveInputAnswer: (String) -> Unit
) {
//    val uiState by baseWritingScreenViewModel.uiState.collectAsState()
    Box(
        modifier = modifier
            .fillMaxSize()
            .padding(dimensionResource(R.dimen.small))
            .clip(RoundedCornerShape(dimensionResource(R.dimen.small)))
            .border(
                width = 1.dp,
                color = Color.Black,
                shape = RoundedCornerShape(dimensionResource(R.dimen.small))
            )
    ) {
        TextField(
//            value = uiState.answerWriting,
            value = inputAnswer,
            onValueChange ={saveInputAnswer(it)}
//                baseWritingScreenViewModel.updateAnswer(
//                    it
//                )

            ,
            modifier = modifier.fillMaxSize()
        )
    }
}


@Preview(showBackground = true)
@Composable
fun BasePre() {
//    BaseWritingScreen()
}
