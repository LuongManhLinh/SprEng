package com.example.spreng.ui.loginscreen
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun SignInScreen(
    signInViewModel: SignInViewModel = viewModel(factory = SignInViewModel.factory),
    onSignUpClick: () -> Unit,
    onSignInSuccess: () -> Unit
)   {

    val uiState by signInViewModel.uiState.collectAsState()
    val signInState by signInViewModel.signInState.collectAsState()
    val context = LocalContext.current
    Column(modifier = Modifier
        .fillMaxSize()
        .padding(16.dp),
        verticalArrangement = Arrangement.Center) {

        Text("Sign In", style = MaterialTheme.typography.bodyLarge, modifier = Modifier.padding(bottom = 16.dp))

        // Username input field
        TextField(
            value = uiState.username,
            onValueChange = { signInViewModel.updateUsername(it) },
            label = { Text("Username") },
            modifier = Modifier.fillMaxWidth().padding(bottom = 8.dp)
        )

        // Password input field
        TextField(
            value = uiState.password,
            onValueChange = { signInViewModel.updatePassword(it)},
            label = { Text("Password") },
            visualTransformation = PasswordVisualTransformation(),
            modifier = Modifier.fillMaxWidth().padding(bottom = 16.dp)
        )
        when (signInState) {
            is SignInViewModel.SignInState.Loading -> {
//                CircularProgressIndicator() // Hiển thị loading
            }
            is SignInViewModel.SignInState.Success -> {
                LaunchedEffect(Unit) {
                    onSignInSuccess()
                }
            }
            is SignInViewModel.SignInState.Error -> {
                Text(
                    text = (signInState as SignInViewModel.SignInState.Error).error,
                    color = Color.Red
                ) // Hiển thị thông báo lỗi
            }
        }
        Button(
            onClick = {
                signInViewModel.signIn(context)
            },
            modifier = Modifier.fillMaxWidth()) {
            Text("Sign In")
        }

        Text(
            text = "Don't have an account? Sign up",
            color = MaterialTheme.colorScheme.primary,
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp)
                .clickable { onSignUpClick() }, // Call the onSignUpClicked function
            style = MaterialTheme.typography.bodyMedium
        )
    }
}
