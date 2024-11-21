package com.example.spreng.ui.studyscreen.answer.micro

import android.widget.Toast
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Mic
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.spreng.R
import com.example.spreng.speech2Text.SpeechRecognizer

@Composable
fun TalkingScreen(
    modifier: Modifier = Modifier,
    inputAnswer: String,
    saveInputAnswer: (String) -> Unit
) {
    val context = LocalContext.current
    val speechRecognizer = SpeechRecognizer(
        context = context,
        onResult = { result -> saveInputAnswer(result) },
        onError = { error ->
            Toast.makeText(context, error, Toast.LENGTH_SHORT).show()
        }
    )

    Column(modifier = modifier
        .padding(dimensionResource(R.dimen.medium))
        .fillMaxSize()) {
        Button(
            onClick = {
                speechRecognizer.startListening()
            },
            modifier = Modifier
                .height(dimensionResource(R.dimen.very_large))
                .width(200.dp)
                .align(Alignment.CenterHorizontally),
            shape = RoundedCornerShape(48.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Color(199, 210, 254))
        ) {
            Icon(imageVector = Icons.Filled.Mic,
                contentDescription = "Tìm",
                modifier = Modifier.size(40.dp))
            Text(
                text = "Nhấn để nói",
                color = Color.Black,
                fontSize = 18.sp
            )
        }
        Spacer(modifier = Modifier.height(dimensionResource(R.dimen.medium)))
        Text(
                text = inputAnswer,
                modifier = Modifier
                    .padding(dimensionResource(R.dimen.small))
                    .align(Alignment.CenterHorizontally)
                    .clip(RoundedCornerShape(dimensionResource(R.dimen.tiny)))
                    .border(
                        width = 1.dp,
                        color = Color.Black,
                        shape = RoundedCornerShape(dimensionResource(R.dimen.small))
                    )
                    .padding(dimensionResource(R.dimen.small))
        )
    }

}

//@Preview(showBackground = true)
//@Composable
//fun PreviewScreen() {
//    TalkingScreen()
//}