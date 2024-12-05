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
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun SignUpScreen(
    signUpViewModel: SignUpViewModel = viewModel(factory = SignUpViewModel.factory),
    onSignInClick: () -> Unit,
    onSignUpSuccess: () -> Unit
) {
    val context = LocalContext.current
    val uiState by signUpViewModel.uiState.collectAsState()
    Column(modifier = Modifier
        .fillMaxSize()
        .padding(16.dp),
        verticalArrangement = Arrangement.Center) {

        Text("Sign Up", style = MaterialTheme.typography.titleLarge, modifier = Modifier.padding(bottom = 16.dp))

        // Username input field
        TextField(
            value = uiState.username,
            onValueChange = { signUpViewModel.updateUsername(it) },
            label = { Text("Username") },
            modifier = Modifier.fillMaxWidth().padding(bottom = 8.dp)
        )

        // Email input field
        TextField(
            value = uiState.email,
            onValueChange = { signUpViewModel.updateEmail(it) },
            label = { Text("Email") },
            modifier = Modifier.fillMaxWidth().padding(bottom = 8.dp)
        )

        // Password input field
        TextField(
            value = uiState.password,
            onValueChange = { signUpViewModel.updatePassword(it) },
            label = { Text("Password") },
            visualTransformation = PasswordVisualTransformation(),
            modifier = Modifier.fillMaxWidth().padding(bottom = 16.dp)
        )

        Button(
            onClick = {
                if (uiState.username.isNotEmpty() && uiState.email.isNotEmpty() && uiState.password.isNotEmpty()) {
                    signUpViewModel.signUp()
                    onSignUpSuccess()
                }
            },
            modifier = Modifier.fillMaxWidth()) {
            Text("Sign Up")
        }

        Text(
            text = "Have an account? Sign in",
            color = MaterialTheme.colorScheme.primary,
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp)
                .clickable { onSignInClick() }, // Call the onSignUpClicked function
            style = MaterialTheme.typography.bodyMedium
        )
    }
}
