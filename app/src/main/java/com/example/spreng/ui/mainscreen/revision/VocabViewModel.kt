package com.example.spreng.ui.mainscreen.revision

import androidx.compose.ui.graphics.Color
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update

data class Vocab(
    val word: String,
    val transcription: String,
    var isChecked: Boolean = true,
    var isVolumeOn: Boolean = false
)

class VocabViewModel : ViewModel() {
    private val _vocabList = MutableStateFlow(
        listOf(
            Vocab("Apple", "Phiên âm"),
            Vocab("Banana", "Phiên âm"),
            Vocab("Cat", "Phiên âm"),
            Vocab("Dog", "Phiên âm"),
            Vocab("Elephant", "Phiên âm"),
            Vocab("Quay", "Phiên âm"),
            Vocab("Bird", "Phiên âm"),
            Vocab("Fish", "Phiên âm"),
            Vocab("Human", "Phiên âm"),
            Vocab("Blue", "Phiên âm")
        )
    )
    val vocabList: StateFlow<List<Vocab>> = _vocabList

    //hiển thị trong thanh tìm kiếm
    private val _query = MutableStateFlow("")
    val query: StateFlow<String> = _query

    //màu của icon tìm kiếm
    val iconColor = MutableLiveData(Color.Black)

    // Đếm số từ chưa được chọn
    val uncheckedCount: StateFlow<Int> = _vocabList
        .map { list -> list.count { !it.isChecked } }
        .stateIn(viewModelScope, SharingStarted.Eagerly, 0)

    // Cập nhật query theo những gì hiển thị trên thanh tìm kiếm
    fun updateQuery(newQuery: String) {
        _query.value = newQuery
    }

    //cập nhật color icon tìm kiếm
    fun updateIconColor() {
        iconColor.value = if(iconColor.value == Color.Black) Color.Blue else Color.Black
    }

    // Chuyển giữa checked và unchecked
    fun toggleChecked(vocab: Vocab) {
        _vocabList.update { list ->
            list.map {
                if (it == vocab) it.copy(isChecked = !it.isChecked)
                else it
            }
        }
    }

    // Chuyển đổi giữa volume on và off
    fun toggleVolume(vocab: Vocab) {
        _vocabList.update { list ->
            list.map {
                if (it == vocab) it.copy(isVolumeOn = !it.isVolumeOn)
                else it
            }
        }
    }

    // Xóa các từ chưa được chọn
    fun removeUnchecked() {
        _vocabList.update { list -> list.filter { it.isChecked } }
    }
}

