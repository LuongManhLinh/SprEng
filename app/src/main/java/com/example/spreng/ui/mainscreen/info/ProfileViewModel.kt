import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel

data class ProfileData(
    var username: String = "JohnDoe123",
    var fullName: String = "John Doe",
    var email: String = "john.doe@example.com",
    var phoneNumber: String = "0123456789",
    var profilePicture: Int = android.R.drawable.ic_menu_gallery // Placeholder image resource ID
)

class ProfileViewModel : ViewModel() {
    var profileData = mutableStateOf(ProfileData())
        private set

    fun updateProfile(updatedData: ProfileData) {
        profileData.value = updatedData
    }

    fun updateProfilePicture(newPicture: Int) {
        profileData.value = profileData.value.copy(profilePicture = newPicture)
    }
}
