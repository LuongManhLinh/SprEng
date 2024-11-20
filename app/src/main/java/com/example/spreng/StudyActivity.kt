package com.example.spreng

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import com.example.spreng.ui.studyscreen.BaseStudyScreen
import com.example.spreng.ui.studyscreen.answer.wordpicker.WordPickerFillScreen
import com.example.spreng.ui.studyscreen.answer.wordpicker.WordPickerSequenceScreen
import com.example.spreng.ui.studyscreen.answer.writing.BaseWritingScreen
import com.example.spreng.ui.studyscreen.question.listening.BaseListeningQuestion
import com.example.spreng.ui.studyscreen.StudyFlowScreen
import com.example.spreng.ui.theme.SprEngTheme
import java.lang.reflect.Modifier

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