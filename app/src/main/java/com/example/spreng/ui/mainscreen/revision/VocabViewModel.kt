package com.example.spreng.ui.mainscreen.revision

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
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

    //danh sách từ đã được lọc
    private val _filteredList = MutableStateFlow<List<Vocab>>(_vocabList.value)
    val filteredList: StateFlow<List<Vocab>> = _filteredList

    // Cập nhật query theo những gì hiển thị trên thanh tìm kiếm
    fun updateQuery(newQuery: String) {
        _query.value = newQuery
    }

    fun performSearch() {
        _filteredList.value = if (_query.value.isBlank()) {
            _vocabList.value
        } else {
            _vocabList.value.filter { vocab ->
                vocab.word.contains(_query.value, ignoreCase = true)
            }
        }
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
}

