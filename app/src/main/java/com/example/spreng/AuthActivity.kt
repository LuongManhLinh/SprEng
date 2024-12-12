package com.example.spreng

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.example.spreng.data.preferences.UserManager
import com.example.spreng.ui.loginscreen.AuthScreen
import com.example.spreng.ui.navigation.MainScreen
import com.example.spreng.ui.theme.SprEngTheme

class AuthActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            SprEngTheme {
                if (UserManager.isUserLoggedIn(this)) {
                    // Nếu đã đăng nhập, chuyển đến trang chính
                    startActivity(Intent(this, MainActivity::class.java))
                    finish()  // Kết thúc MainActivity để không quay lại màn hình đăng nhập
                } else {
                    // Nếu chưa đăng nhập, chuyển đến màn hình đăng nhập
                    AuthScreen()
                }
            }
        }
    }
}