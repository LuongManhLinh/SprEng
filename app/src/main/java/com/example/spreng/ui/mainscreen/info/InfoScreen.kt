import android.content.Intent
import androidx.activity.ComponentActivity
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.ContextCompat.startActivity
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.spreng.AuthActivity
import com.example.spreng.MainActivity
import com.example.spreng.R
import com.example.spreng.preferences.UserManager
import com.example.spreng.ui.custom.CustomRoundedBorderBox
import com.example.spreng.ui.mainscreen.info.ProfileViewModel
import com.example.spreng.ui.mainscreen.ranking.Card
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun InfoScreen(
    profileViewModel: ProfileViewModel = viewModel(factory = ProfileViewModel.factory),
    modifier: Modifier = Modifier
) {
    val context = LocalContext.current
    val uiState by profileViewModel.uiState.collectAsState()
    LaunchedEffect(Unit) {
        profileViewModel.updateProfile(context)
    }
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        ProfileHeader(
            username = uiState.username,
            fullName = uiState.fullName,
            //onEditProfile = { /* Handle edit profile */ }
        )
        Spacer(modifier = Modifier.height(24.dp))
        JoinDateSection()
        ActionButtons()
        Spacer(modifier = Modifier.height(24.dp))
        StatsSection(
            streak = uiState.streak.toString(),
            xp = uiState.exp.toString(),
            rank = uiState.rank,
            top3count = uiState.top3Count.toString()
        )
    }
}

@Composable
private fun ProfileHeader(
    username: String,
    fullName: String,
) {
    Column(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(
            modifier = Modifier
                .size(100.dp)
                .clip(CircleShape)
                .border(2.dp, MaterialTheme.colorScheme.primary, CircleShape)
                .clickable { /* Handle image selection */ }
        ) {
            Image(
                painter = painterResource(id = android.R.drawable.ic_menu_gallery),
                contentDescription = "Profile picture",
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.Crop
            )
            Icon(
                imageVector = Icons.Default.Edit,
                contentDescription = "Edit profile picture",
                modifier = Modifier
                    .align(Alignment.BottomEnd)
                    .background(MaterialTheme.colorScheme.primary, CircleShape)
                    .padding(4.dp),
                tint = Color.White
            )
        }
        Spacer(modifier = Modifier.width(16.dp))
        Column {
            Text(
                text = username,
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black
            )
            Text(
                text = fullName,
                fontSize = 16.sp,
                color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.7f)
            )
        }
    }
}

@Composable
private fun ActionButtons() {
    val context = LocalContext.current
    var addFriendIsPressed by remember { mutableStateOf(false) }
    var editProfileIsPressed by remember { mutableStateOf(false) }
    var logOutIsPressed by remember { mutableStateOf(false) }
    val coroutineScope = rememberCoroutineScope()
    Column(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Box(
            modifier = Modifier.fillMaxWidth(),
        ) {
            CustomRoundedBorderBox(
                modifier = Modifier
                    .padding(
                        top = if (addFriendIsPressed) {
                            4.dp
                        } else {
                            0.dp
                        }
                    ),
                cornerRadius = 16.dp,
                bottomBorderWidth = if (addFriendIsPressed) 0.dp else 4.dp,
                borderColor = Color(100, 151, 177),
                containerColor = Color(75, 134, 180)
            ) {
                Button(
                    onClick = { /* Handle add friends */
                        addFriendIsPressed = true
                        coroutineScope.launch {
                            delay(125)
                            addFriendIsPressed = false
                        }
                    },
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(8.dp),
                    colors = ButtonDefaults.buttonColors(Color(75, 134, 180))
                ) {
                    Icon(
                        imageVector = Icons.Default.Star,
                        contentDescription = "Thêm Bạn Bè"
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text("Thêm Bạn Bè", color = Color.White)
                }
            }
        }

        Box(
            modifier = Modifier.fillMaxWidth(),
        ) {
            CustomRoundedBorderBox(
                modifier = Modifier
                    .padding(
                        top = if (editProfileIsPressed) {
                            4.dp
                        } else {
                            0.dp
                        }
                    ),
                cornerRadius = 16.dp,
                bottomBorderWidth = if (editProfileIsPressed) 0.dp else 4.dp,
                borderColor = Color(100, 151, 177),
                containerColor = Color(75, 134, 180)
            ) {
                Button(
                    onClick = {
                        editProfileIsPressed = true
                        coroutineScope.launch {
                            delay(125)
                            editProfileIsPressed = false
                        }
                    },
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(8.dp),
                    colors = ButtonDefaults.buttonColors(Color(75, 134, 180))
                ) {
                    Icon(
                        imageVector = Icons.Default.Edit,
                        contentDescription = "Chỉnh Sửa Hồ Sơ"
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text("Chỉnh Sửa Hồ Sơ", color = Color.White)
                }
            }
        }

        Box(
            modifier = Modifier.fillMaxWidth(),
        ) {
            CustomRoundedBorderBox(
                modifier = Modifier
                    .padding(
                        top = if (logOutIsPressed) {
                            4.dp
                        } else {
                            0.dp
                        }
                    ),
                cornerRadius = 16.dp,
                bottomBorderWidth = if (logOutIsPressed) 0.dp else 4.dp,
                borderColor = Color(100, 151, 177),
                containerColor = Color(75, 134, 180)
            ) {
                Button(
                    onClick = {
                        logOutIsPressed = true
                        coroutineScope.launch {
                            delay(125)
                            logOutIsPressed = false
                            UserManager.saveLoginState(context = context, isLoggedIn = false)
                            val intent = Intent(context, AuthActivity::class.java)
                            context.startActivity(intent)
                            (context as? ComponentActivity)?.finish()
                        }
                    },
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(8.dp),
                    colors = ButtonDefaults.buttonColors(Color(75, 134, 180))

                ) {
                    Spacer(modifier = Modifier.width(8.dp))
                    Text("Đăng xuất", color = Color.White)
                }
            }
        }
    }
}

@Composable
internal fun StatsSection(streak: String, xp: String, rank: String, top3count: String) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Text(
            text = "Thống kê",
            fontSize = 32.sp,
            color = Color.Black,
        )
        Row() {
            Card(streak, "Streak", R.drawable.streak, modifier = Modifier.weight(1f))
            Card(xp, "Tổng Xp", R.drawable.xp, modifier = Modifier.weight(1f))
        }
        Row() {
            Card(rank, "Hạng", R.drawable.bronze, modifier = Modifier.weight(1f))
            Card(top3count, "Số lần top 3", R.drawable.medal, modifier = Modifier.weight(1f))
        }
    }
}

@Composable
fun JoinDateSection() {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            imageVector = Icons.Default.Star,
            contentDescription = "Join date",
            tint = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.7f)
        )
        Spacer(modifier = Modifier.width(8.dp))
        Text(
            text = "Tham gia từ tháng 12, 2014",
            color = Color.Gray,
        )
    }
}