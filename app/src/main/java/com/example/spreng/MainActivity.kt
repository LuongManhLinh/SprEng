package com.example.spreng

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.example.spreng.ui.navigation.MainScreen
import com.example.spreng.ui.theme.SprEngTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            SprEngTheme {
                MainScreen()
            }
        }
    }
}

