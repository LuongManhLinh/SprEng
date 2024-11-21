package com.example.spreng

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.spreng.ui.studyscreen.BaseStudyScreen
import com.example.spreng.ui.studyscreen.StudyFlowScreen
import com.example.spreng.ui.studyscreen.answer.wordpicker.WordPickerFillingScreen
import com.example.spreng.ui.studyscreen.answer.wordpicker.WordPickerSequenceScreen
import com.example.spreng.ui.studyscreen.answer.writing.BaseWritingScreen
import com.example.spreng.ui.studyscreen.StudyFlowScreen
import com.example.spreng.ui.theme.SprEngTheme

class StudyActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            SprEngTheme {
                StudyFlowScreen()
            }
        }
    }

}

@Preview(showBackground = true)
@Composable
fun Pre() {
//    BaseStudyScreen(
//        learningProgress = 1f,
//        questionTitle = "",
//        onCancelling = {},
//        onBottomButtonPressed = {
//
//        },
//
//    )
}
