package com.example.spreng.ui.mainscreen.revision

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.filled.VolumeOff
import androidx.compose.material.icons.automirrored.filled.VolumeUp
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.spreng.R
import com.example.spreng.data.MainNavRoute


@Composable
fun ReviewVocabsScreen(navController: NavController,
                       modifier: Modifier = Modifier,
                       vocabViewModel: VocabViewModel = viewModel()
) {
    val vocabList by vocabViewModel.vocabList.collectAsState()
    val query by vocabViewModel.query.collectAsState()

    Scaffold(
        modifier = modifier,
        topBar = {
            ReviewVocabTopBar(
                navController = navController,
                numbOfVocab = vocabList.size
            )
        }
    ) { contentPadding ->
        Column(
            modifier = Modifier
                .padding(top = contentPadding.calculateTopPadding())
                .fillMaxSize()
        ) {
            // Thanh tìm kiếm sử dụng TextField
            TextField(
                value = query,
                onValueChange = { vocabViewModel.updateQuery(it) },
                placeholder = { Text("Gõ vào đây từ bạn muốn tìm") },
                trailingIcon = {
                    Icon(imageVector = Icons.Filled.Search, contentDescription = "Tìm")
                },
                modifier = Modifier
                    .padding(dimensionResource(R.dimen.small_medium))
                    .fillMaxWidth()
                    .clip(shape = RoundedCornerShape(dimensionResource(R.dimen.large)))
            )
            // Danh sách từ vựng
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(dimensionResource(R.dimen.small_medium))
                    .border(1.dp, Color.Black, shape = RoundedCornerShape(dimensionResource(R.dimen.medium)))
                    .clip(shape = RoundedCornerShape(dimensionResource(R.dimen.medium)))
            ) {
                items(vocabList) { vocab ->
                    Column() {
                        // một từ trong list
                        VocabItem(
                            vocab = vocab,
                            onToggleCheck = { vocabViewModel.toggleChecked(it) },
                            onToggleVolume = { vocabViewModel.toggleVolume(it) }
                        )
                        HorizontalDivider(color = Color.Black, thickness = 1.dp)
                    }
                }
            }
        }
    }
}

@Composable
fun VocabItem(
    vocab: Vocab,
    modifier: Modifier = Modifier,
    onToggleCheck: (Vocab) -> Unit,
    onToggleVolume: (Vocab) -> Unit
) {
    Row(
        modifier = modifier
//            .clip(RoundedCornerShape(8.dp))
//            .background(Color.Cyan)
            .padding(top = 16.dp, bottom = 16.dp, start = 8.dp, end = 8.dp)
            .fillMaxWidth()
    ) {
        Icon(
            imageVector = if (vocab.isVolumeOn) Icons.AutoMirrored.Filled.VolumeUp else Icons.AutoMirrored.Filled.VolumeOff,
            contentDescription = "volumn",
            modifier = modifier
                .weight(1f)
                .clickable { onToggleVolume(vocab) }
        )
        Column(
            modifier = Modifier
                .weight(10f)
                .padding(start = 8.dp)
        ) {
            Text(
                text = vocab.word,
            )
            Text(
                text = vocab.transcription,
            )
        }
        Image(
            painter = painterResource(if (vocab.isChecked) R.drawable.checked else R.drawable.unchecked),
            contentDescription = "check",
            modifier = modifier
                .weight(1f)
                .clickable { onToggleCheck(vocab) }
        )
    }
}

@Composable
fun ReviewVocabTopBar(
    navController: NavController,
    numbOfVocab: Int,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .padding(dimensionResource(R.dimen.tiny))
            .height(dimensionResource(R.dimen.very_large))
            .fillMaxWidth()
            .background(MaterialTheme.colorScheme.primaryContainer)
            .padding(dimensionResource(R.dimen.small))
    ) {
        // arrowback nằm bên trái
        IconButton(
            onClick = {
                navController.navigate(MainNavRoute.REVISION.name)
            },
            modifier = Modifier.align(Alignment.CenterStart)
        ) {
            Icon(
                imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                contentDescription = "Quay lại"
            )
        }

        // số lượng từ vựng nằm giữa
        Text(
            text = "$numbOfVocab từ vựng",
            fontWeight = FontWeight.Bold,
            fontSize = 24.sp,
            modifier = Modifier.align(Alignment.Center)
        )
    }
}

@Preview
@Composable
fun PreviewVocabs() {
    ReviewVocabsScreen(navController = rememberNavController())
}