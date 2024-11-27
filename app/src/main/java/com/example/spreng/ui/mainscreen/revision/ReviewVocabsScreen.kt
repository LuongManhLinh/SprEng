package com.example.spreng.ui.mainscreen.revision

import android.content.Context
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.unit.dp
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.spreng.R
import com.example.spreng.text2speech.TTS
import com.example.spreng.ui.custom.CustomRoundedBorderBox


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ReviewVocabsScreen(
    showRevision: () -> Unit,
    modifier: Modifier = Modifier,
    vocabViewModel: VocabViewModel = viewModel()
) {
    val vocabList by vocabViewModel.vocabList.collectAsState()
    val filteredList by vocabViewModel.filteredList.collectAsState()
    val query by vocabViewModel.query.collectAsState()
    val context = LocalContext.current

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
                containerColor = Color(135, 183, 239),
                borderColor = Color(60, 71, 88)
            ) {
                ReviewVocabTopBar(
                    showRevision = {showRevision()},
                    numbOfVocab = vocabList.size,
                )
            }
        },
        containerColor = colorResource(R.color.container)
    ) { contentPadding ->
        Column(
            modifier = Modifier
                .padding(top = contentPadding.calculateTopPadding())
                .fillMaxSize()
//                .background(colorResource(id = R.color.container))
        ) {
            // Thanh tìm kiếm sử dụng TextField
            CustomRoundedBorderBox(
                modifier = modifier
                    .padding(
                        top = dimensionResource(R.dimen.small_medium),
                        start = dimensionResource(R.dimen.small_medium),
                        end = dimensionResource(R.dimen.small_medium)
                    ),
                cornerRadius = dimensionResource(R.dimen.medium_large),
                startBorderWidth = dimensionResource(R.dimen.very_tiny),
                bottomBorderWidth = dimensionResource(R.dimen.tiny),
                containerColor = Color.White,
                borderColor = Color(60, 71, 88)
            ) {
                TextField(
                    value = query,
                    onValueChange = { vocabViewModel.updateQuery(it)
                        vocabViewModel.performSearch()},
                    placeholder = { Text(
                        text = "Gõ vào đây từ bạn muốn tìm",
                        color = Color.Gray) },
                    trailingIcon = {
                        Icon(imageVector = Icons.Filled.Search,
                            contentDescription = "Tìm",
                            tint = Color.DarkGray,
                            modifier = Modifier
                                .size(40.dp)
                                .clickable {
                                    vocabViewModel.performSearch()
                                })
                    },
                    colors = TextFieldDefaults.textFieldColors(
                        containerColor = Color.White
                    ),
                    modifier = Modifier
                        .fillMaxWidth()
                )
            }

            // Danh sách từ vựng
            CustomRoundedBorderBox(
                modifier = modifier
                    .padding(dimensionResource(R.dimen.small_medium)),
                cornerRadius = dimensionResource(R.dimen.medium_large),
                startBorderWidth = dimensionResource(R.dimen.very_tiny),
                bottomBorderWidth = dimensionResource(R.dimen.tiny),
                containerColor = Color(135, 183, 239),
                borderColor = Color(60, 71, 88)
            ) {
                LazyColumn(
                    modifier = Modifier
                        .border(
                            1.dp,
                            Color.LightGray,
                            shape = RoundedCornerShape(dimensionResource(R.dimen.medium_large))
                        )
                        .fillMaxSize()
                        .background(Color(226, 232, 240))
                ) {
                    items(filteredList) { vocab ->
                        Column() {
                            // một từ trong list
                            VocabItem(
                                vocab = vocab,
                                context = context,
                                onToggleCheck = { vocabViewModel.toggleChecked(it) },
//                            onToggleVolume = { vocabViewModel.toggleVolume(it) }
                            )
                            HorizontalDivider(color = Color.LightGray, thickness = 1.dp)
                        }
                    }
                }
            }
        }
    }
}

//giao diện hiển thị một từ vựng
@Composable
fun VocabItem(
    vocab: Vocab,
    context: Context,
    modifier: Modifier = Modifier,
    onToggleCheck: (Vocab) -> Unit,
//    onToggleVolume: (Vocab) -> Unit
) {
    Row(
        modifier = modifier
            .padding(top = 16.dp, bottom = 16.dp, start = 8.dp, end = 8.dp)
            .fillMaxWidth()
    ) {
        Image(
            painter = painterResource(R.drawable.volume),
            contentDescription = "volume",
            modifier = modifier
                .padding(top = 4.dp)
                .weight(1f)
                .clickable {
                    TTS(context = context, sentence = vocab.word)
                }
        )

        Column(
            modifier = Modifier
                .weight(10f)
                .padding(start = 8.dp)
        ) {
            Text(
                text = vocab.word,
                fontSize = 22.sp,
                color = Color.Black
            )
            Text(
                text = vocab.meaning,
                fontSize = 18.sp,
                color = Color.Black
            )
        }
        Image(
            painter = painterResource(if (vocab.isChecked) R.drawable.checked else R.drawable.unchecked),
            contentDescription = "check",
            modifier = modifier
                .padding(top = 2.dp)
                .weight(1f)
                .clickable { onToggleCheck(vocab) }
        )
    }
}

//top bar
@Composable
fun ReviewVocabTopBar(
    showRevision: () -> Unit,
    numbOfVocab: Int,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .padding(dimensionResource(R.dimen.small))
    ) {
        // arrowback nằm bên trái
        IconButton(
            onClick = {
                showRevision()
            },
            modifier = Modifier.align(Alignment.CenterStart)
        ) {
            Icon(
                imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                contentDescription = "Quay lại",
                tint = Color.Black
            )
        }

        // số lượng từ vựng nằm giữa
        Text(
            text = "$numbOfVocab từ vựng",
            color = Color.Black,
            fontWeight = FontWeight.Bold,
            fontSize = 24.sp,
            modifier = Modifier.align(Alignment.Center)
        )
    }
}

//@Preview
//@Composable
//fun PreviewVocabs() {
//    ReviewVocabsScreen(navController = rememberNavController())
//}