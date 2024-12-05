package com.example.spreng.ui.loginscreen

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.focusModifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.spreng.R
import com.example.spreng.ui.custom.CustomRoundedBorderBox
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SignInScreen(
    signInViewModel: SignInViewModel = viewModel(factory = SignInViewModel.factory),
    onSignUpClick: () -> Unit,
    onSignInSuccess: () -> Unit
) {

    val uiState by signInViewModel.uiState.collectAsState()
    val signInState by signInViewModel.signInState.collectAsState()
    val context = LocalContext.current

    Column(
        modifier = Modifier
            .fillMaxSize()
//            .background(Color(245, 245, 245))
            .background(colorResource(R.color.container))
            .padding(20.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        // Title
        Text(
            "Đăng nhập để học ngay nhé",
            fontSize = 20.sp,
            style = MaterialTheme.typography.bodyLarge,
            color = Color.Black,
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(top = 100.dp) //khoảng cách từ trên xuống
        )

        // Username input field
        TextField(
            value = uiState.username,
            onValueChange = { signInViewModel.updateUsername(it) },
            label = { Text("Nhập tên người dùng", color = Color.Gray) },
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 100.dp) //khoảng cách giữa title và TextField
                .border(1.dp, color = Color.LightGray, shape = RoundedCornerShape(16.dp)),
            shape = RoundedCornerShape(16.dp),
            colors = TextFieldDefaults.textFieldColors(
                containerColor = Color.White,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                focusedLabelColor = colorResource(R.color.teal_200),
                unfocusedLabelColor = Color.Gray,
            )
        )

        // Password input field
        TextField(
            value = uiState.password,
            onValueChange = { signInViewModel.updatePassword(it) },
            label = { Text("Nhập mật khẩu", color = Color.Gray) },
            visualTransformation = PasswordVisualTransformation(),
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp)
                .border(1.dp, color = Color.LightGray, shape = RoundedCornerShape(16.dp)),
            shape = RoundedCornerShape(16.dp),
            colors = TextFieldDefaults.textFieldColors(
                containerColor = Color.White,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                focusedLabelColor = colorResource(R.color.teal_200),
                unfocusedLabelColor = Color.Gray,
            )
        )

        // State handling
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
                    modifier = Modifier.padding(top = 16.dp),
                    text = (signInState as SignInViewModel.SignInState.Error).error,
                    color = Color.Red
                ) // Hiển thị thông báo lỗi
            }
        }

        // Login button
        Box(
            modifier = Modifier
                .padding(top = 32.dp)
                .width(190.dp)
                .height(64.dp)
        ) {
            CustomRoundedBorderBox(
                cornerRadius = 28.dp,
                bottomBorderWidth = 4.dp,
                borderColor = Color.LightGray,
                containerColor = colorResource(R.color.teal_200)
            ) {
                Button(
                    onClick = {
                        signInViewModel.signIn(context)
                    },
                    modifier = Modifier.fillMaxWidth(),
                    colors = ButtonDefaults.buttonColors(containerColor = colorResource(R.color.teal_200))
                ) {
                    Text("Đăng nhập", color = Color.White, fontSize = 18.sp)
                }
            }
        }

        // Sign-up link
        Text(
            text = "Chưa có tài khoản? Tạo tài khoản",
            fontSize = 16.sp,
            color = Color.Blue,
            modifier = Modifier
                .padding(top = 16.dp)
                .clickable { onSignUpClick() },
            style = MaterialTheme.typography.bodyMedium
        )
    }
}



@Preview
@Composable
fun SignInScreenPreview() {
    SignInScreen(onSignUpClick = {}, onSignInSuccess = {})
}