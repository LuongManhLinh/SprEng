
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
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.spreng.R
import com.example.spreng.ui.mainscreen.info.ProfileViewModel
import com.example.spreng.ui.mainscreen.ranking.Card

@Composable
fun InfoScreen(
    profileViewModel: ProfileViewModel = viewModel(factory = ProfileViewModel.factory),
    modifier: Modifier = Modifier
) {
//    var username by remember { mutableStateOf("JohnDoe123") }
//    var fullName by remember { mutableStateOf("John Doe") }
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
                fontWeight = FontWeight.Bold
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
fun SelectCourseScreen(onSelect: (String) -> Unit) {
    val courses = listOf("Tiếng Anh", "Tiếng Trung", "Tiếng Việt")
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Text(text = "Chọn Khoá Học", fontWeight = FontWeight.Bold, fontSize = 20.sp)
        courses.forEach { course ->
            Button(
                onClick = { onSelect(course) },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(course)
            }
        }
    }
}
@Composable
private fun ActionButtons(
    navController: NavController,
    onSelectCourse: () -> Unit,
    onAddFriend: () -> Unit,
    onEditProfile: () -> Unit
) {
    Column(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Button(
            onClick = onAddFriend,
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(8.dp)
        ) {
            Icon(
                imageVector = Icons.Default.Star,
                contentDescription = "Thêm bạn bè"
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text("Thêm bạn bè")
        }
        Button(
            onClick = {navController.navigate("edit")},
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(8.dp)
        ) {
            Icon(
                imageVector = Icons.Default.Edit,
                contentDescription = "Chỉnh sửa hồ sơ"
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text("Chỉnh sửa hồ sơ")
        }
    }
}
//@Composable
//fun InfoScreen(
//    username: String,
//    fullName: String,
//    onNavigateToEditProfile: () -> Unit,
//    onNavigateToSelectCourse: () -> Unit,
//    onNavigateToAddFriend: () -> Unit
//) {
//
//    Column(
//        modifier = Modifier
//            .fillMaxSize()
//            .padding(16.dp)
//    ) {
//        // Header hiển thị thông tin người dùng
//        ProfileHeader(
//            username = username,
//            fullName = fullName,
//            onEditProfile = onNavigateToEditProfile,
//            onEditProfilePicture = {
////                viewModel.updateProfilePicture(android.R.drawable.ic_menu_camera)
//            }
//        )
//        Spacer(modifier = Modifier.height(24.dp))
//
//        // Action buttons
////        ActionButtons(
////            onSelectCourse = onNavigateToSelectCourse,
////            onAddFriend = onNavigateToAddFriend,
////            onEditProfile = onNavigateToEditProfile
////        )
//        Spacer(modifier = Modifier.height(24.dp))
//
//        // Stats
//        StatsSection()
//    }
//}
//@Composable
//fun AppNavigation() {
//    val viewModel = remember { com.example.spreng.ui.mainscreen.info.ProfileViewModel() }
//    val navController = rememberNavController()
//
//    NavHost(navController = navController, startDestination = "infoScreen") {
//        composable("infoScreen") {
//            InfoScreen(
//                viewModel = viewModel,
//                onNavigateToEditProfile = { navController.navigate("editProfileScreen") },
//                onNavigateToSelectCourse = { navController.navigate("selectCourseScreen") },
//                onNavigateToAddFriend = { /* Điều hướng tới trang thêm bạn bè */ }
//            )
//        }
//        composable("editProfileScreen") {
//            EditProfileScreen(
//                viewModel = viewModel,
//                onSave = { navController.popBackStack() }
//            )
//        }
//        composable("selectCourseScreen") {
//            SelectCourseScreen(
//                onSelect = { course ->
//                    // Lưu trữ khóa học đã chọn hoặc xử lý logic khác
//                    navController.popBackStack()
//                }
//            )
//        }
//    }
//}

@Composable
private fun ProfileHeader(
    username: String,
    fullName: String,
    onEditProfile: () -> Unit,
    onEditProfilePicture: () -> Unit
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
                .clickable { onEditProfilePicture() }
        ) {
            Image(
                painter = painterResource(id = android.R.drawable.ic_menu_gallery),
                contentDescription = "Profile picture",
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.Crop
            )
        }
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = username,
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold
        )
        Text(
            text = fullName,
            fontSize = 16.sp,
            color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.7f)
        )
    }
}
@Composable
private fun StatItem(
    icon: androidx.compose.ui.graphics.vector.ImageVector,
    label: String,
    value: String
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.padding(8.dp)
    ) {
        Icon(
            imageVector = icon,
            contentDescription = label,
            tint = MaterialTheme.colorScheme.primary
        )
        Text(
            text = value,
            fontWeight = FontWeight.Bold,
            fontSize = 16.sp
        )
        Text(
            text = label,
            fontSize = 14.sp,
            color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.7f)
        )
    }
}
@Composable
private fun ActionButtons() {
    Column(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Button(
            onClick = { /* Handle course selection */ },
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(8.dp)
        ) {
            Icon(
                imageVector = Icons.Default.Star,
                contentDescription = "Chọn Khoá Học"
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text("Chọn Khoá Học")
        }
        Button(
            onClick = { /* Handle add friends */ },
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(8.dp)
        ) {
            Icon(
                imageVector = Icons.Default.Star,
                contentDescription = "Thêm Bạn Bè"
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text("Thêm Bạn Bè")
        }
        Button(
            onClick = {},
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(8.dp)
        ) {
            Icon(
                imageVector = Icons.Default.Edit,
                contentDescription = "Chỉnh Sửa Hồ Sơ"
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text("Chỉnh Sửa Hồ Sơ")
        }
    }
}
@Composable
internal fun StatsSection(streak: String, xp: String, rank: String, top3count: String) {
    Column() {
        Text(
            text = "Thống kê",
            fontSize = 32.sp
        )
        Row() {
            Card(streak, "Streak", R.drawable.streak,modifier = Modifier.weight(1f))
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
            text = "NguyenVanA since March 2024",
            color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.7f)
        )
    }
}

//@Composable
//fun EditProfileScreen(
//    viewModel: com.example.spreng.ui.mainscreen.info.ProfileViewModel,
//    onSave: () -> Unit
//) {
//    var fullName by remember { mutableStateOf(viewModel.profileData.value.fullName) }
//    var email by remember { mutableStateOf(viewModel.profileData.value.email) }
//    var phoneNumber by remember { mutableStateOf(viewModel.profileData.value.phoneNumber) }
//
//    Column(
//        modifier = Modifier
//            .fillMaxSize()
//            .padding(16.dp),
//        verticalArrangement = Arrangement.spacedBy(16.dp)
//    ) {
//        Text(text = "Chỉnh Sửa Hồ Sơ", fontWeight = FontWeight.Bold, fontSize = 20.sp)
//
//        TextField(
//            value = fullName,
//            onValueChange = { fullName = it },
//            label = { Text("Họ và Tên") }
//        )
//        TextField(
//            value = email,
//            onValueChange = { email = it },
//            label = { Text("Email") }
//        )
//        TextField(
//            value = phoneNumber,
//            onValueChange = { phoneNumber = it },
//            label = { Text("Số Điện Thoại") }
//        )
//        Button(
//            onClick = {
//                // Cập nhật dữ liệu trong ViewModel
//                viewModel.updateProfile(
//                    viewModel.profileData.value.copy(
//                        fullName = fullName,
//                        email = email,
//                        phoneNumber = phoneNumber
//                    )
//                )
//                onSave() // Quay lại màn hình trước đó
//            },
//            modifier = Modifier.fillMaxWidth()
//        ) {
//            Text("Ghi Nhận Thông Tin")
//        }
//    }
//}

