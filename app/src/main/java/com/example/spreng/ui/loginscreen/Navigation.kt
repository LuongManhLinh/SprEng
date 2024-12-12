package com.example.spreng.ui.loginscreen

import android.content.Intent
import androidx.activity.ComponentActivity
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.spreng.MainActivity
import com.example.spreng.R
import com.example.spreng.data.NavLogin
import com.example.spreng.data.preferences.UserManager
import com.example.spreng.ui.loginscreen.SignInScreen
import com.google.accompanist.systemuicontroller.rememberSystemUiController

@Composable
fun AuthScreen() {
    val navController = rememberNavController()

    val systemUiController = rememberSystemUiController()
    systemUiController.setStatusBarColor(
    color = colorResource(id = R.color.container),
    darkIcons = true
    )
    NavigationScreen(
        navController = navController,
        modifier = Modifier
    )
}

@Composable
private fun NavigationScreen(
    navController : NavHostController,
    modifier: Modifier = Modifier
) {
    val context = LocalContext.current

    NavHost(
        navController = navController,
        startDestination = NavLogin.SignIn.name,
        modifier = modifier,
    ) {
        composable(route = NavLogin.SignIn.name) {
            SignInScreen(
                onSignUpClick = { navController.navigate(NavLogin.SignUp.name) },
                onSignInSuccess = {
                    UserManager.saveLoginState(context = context, isLoggedIn = true)
                    val intent = Intent(context, MainActivity::class.java)
                    context.startActivity(intent)
                    (context as? ComponentActivity)?.finish()
                }
            )
        }
        composable(route = NavLogin.SignUp.name) {
            SignUpScreen(
                onSignInClick = { navController.navigate(NavLogin.SignIn.name) },
                onSignUpSuccess = { navController.navigate(NavLogin.SignIn.name) }
            )
        }
    }
}