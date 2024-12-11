package com.example.spreng.ui.studyscreen.answer.micro

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.spreng.R
import com.example.spreng.speech2Text.SpeechRecognizer
import com.example.spreng.ui.custom.CustomRoundedBorderBox
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun SpeakingScreen(
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
    var isPressed by remember { mutableStateOf(false) }
    val coroutineScope = rememberCoroutineScope()

    Column(
        modifier = modifier
            .padding(dimensionResource(R.dimen.medium))
            .fillMaxSize()
            .background(colorResource(R.color.container))
    ) {
        Box(
            modifier = modifier
                .width(200.dp)
                .height(64.dp)
        ) {
            CustomRoundedBorderBox(
                modifier = Modifier
                    .padding(
                        top = if (isPressed) {
                            dimensionResource(R.dimen.very_large) + 4.dp
                        } else {
                            dimensionResource(R.dimen.very_large)
                        },
                        start = dimensionResource(R.dimen.tiny),
                        end = dimensionResource(R.dimen.tiny)
                    )
                    .align(Alignment.TopCenter),
                cornerRadius = dimensionResource(R.dimen.medium),
                bottomBorderWidth = if(isPressed) 0.dp else dimensionResource(R.dimen.tiny),
                borderColor = Color(108, 126, 185),
                containerColor = Color(198, 215, 235),
                contentWidthDp = 200.dp,
                contentHeightDp = 64.dp
            ) {
                Button(
                    onClick = {
                        isPressed = true;
                        coroutineScope.launch {
                            delay(125)
                            isPressed = false
                            speechRecognizer.startListening()
                        }
                    },
                    modifier = modifier,
                    shape = RoundedCornerShape(dimensionResource(R.dimen.medium)),
                    colors = ButtonDefaults.buttonColors(containerColor = Color(198, 215, 235))
                ) {
                    Icon(
                        imageVector = Icons.Filled.Mic,
                        contentDescription = "micro",
                        modifier = Modifier.size(40.dp),
                        tint = Color.Black
                    )
                    Text(
                        text = "Nhấn để nói",
                        color = Color.Black,
                        fontSize = 18.sp
                    )
                }
            }
        }

        Spacer(modifier = modifier.height(dimensionResource(R.dimen.medium)))

        //answer box
        if (inputAnswer.isNotEmpty()) {
            Box(
                modifier = modifier
                    .padding(dimensionResource(R.dimen.small))
                    .align(Alignment.CenterHorizontally)
                    .clip(RoundedCornerShape(dimensionResource(R.dimen.very_tiny)))
                    .border(
                        width = dimensionResource(R.dimen.very_tiny),
                        color = Color.Black,
                        shape = RoundedCornerShape(dimensionResource(R.dimen.small))
                    )
                    .background(Color(226, 232, 240))
                    .padding(dimensionResource(R.dimen.small))
            ) {
                Text(
                    text = inputAnswer,
                    color = Color.Black,
                    modifier = Modifier,
                    fontSize = 22.sp
                )
            }
        }
    }

}

@Preview(showBackground = true)
@Composable
fun PreviewScreen() {
    SpeakingScreen(inputAnswer = "Hello, World!",
        saveInputAnswer = {})
}