package com.example.spreng.ui.loginscreen

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.spreng.R
import com.example.spreng.ui.custom.CustomRoundedBorderBox

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SignUpScreen(
    signUpViewModel: SignUpViewModel = viewModel(factory = SignUpViewModel.factory),
    onSignInClick: () -> Unit,
    onSignUpSuccess: () -> Unit
) {
    val context = LocalContext.current
    val uiState by signUpViewModel.uiState.collectAsState()

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
            "Tạo tài khoản để bắt đầu học",
            fontSize = 20.sp,
            style = MaterialTheme.typography.bodyLarge,
            color = Color.Black,
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(top = 100.dp) // Khoảng cách từ trên xuống
        )

        // Username input field
        TextField(
            value = uiState.username,
            onValueChange = { signUpViewModel.updateUsername(it) },
            label = { Text("Nhập tên người dùng", color = Color.Gray) },
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 100.dp) // Khoảng cách giữa title và TextField
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

        // Email input field
        TextField(
            value = uiState.email,
            onValueChange = { signUpViewModel.updateEmail(it) },
            label = { Text("Nhập email", color = Color.Gray) },
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

        // Password input field
        TextField(
            value = uiState.password,
            onValueChange = { signUpViewModel.updatePassword(it) },
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

        // checkbox đồng ý điều khoản
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(top = 16.dp)
        ) {
            Checkbox(
                checked = uiState.isTermsAccepted,
                onCheckedChange = { signUpViewModel.updateTermsAccepted(it) },
                colors = CheckboxDefaults.colors(
                    checkedColor = colorResource(R.color.teal_200),
                    uncheckedColor = colorResource(R.color.teal_200),
                    checkmarkColor = Color.White
                )
            )
            Text(
                text = "Tôi đồng ý với các điều khoản và chính sách",
                fontSize = 14.sp,
                modifier = Modifier.padding(start = 8.dp)
            )
        }

        // Sign Up button
        Box(
            modifier = Modifier
                .padding(top = 32.dp)
                .width(190.dp)
                .height(64.dp)
        ) {
            CustomRoundedBorderBox(
                cornerRadius = 28.dp,
                bottomBorderWidth = 4.dp,
                borderColor = Color(120, 240, 230),
                containerColor = colorResource(R.color.teal_200)
            ) {
                Button(
                    onClick = {
                        if (uiState.username.isNotEmpty() &&
                            uiState.email.isNotEmpty() &&
                            uiState.password.isNotEmpty() &&
                            uiState.isTermsAccepted
                        ) {
                            signUpViewModel.signUp()
                            onSignUpSuccess()
                        }
                    },
                    modifier = Modifier.fillMaxWidth(),
                    colors = ButtonDefaults.buttonColors(containerColor = colorResource(R.color.teal_200))
                ) {
                    Text("Đăng ký", color = Color.White, fontSize = 18.sp)
                }
            }
        }

        // Sign In link
        Text(
            text = "Đã có tài khoản? Đăng nhập",
            fontSize = 16.sp,
            color = Color.Blue,
            modifier = Modifier
                .padding(top = 16.dp)
                .clickable { onSignInClick() },
            style = MaterialTheme.typography.bodyMedium
        )
    }
}
