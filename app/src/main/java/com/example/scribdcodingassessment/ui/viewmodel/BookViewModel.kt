package com.example.scribdcodingassessment.ui.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.scribdcodingassessment.Constants.AUDIOBOOK
import com.example.scribdcodingassessment.Constants.EBOOK
import com.example.scribdcodingassessment.data.BooksRepository
import com.example.scribdcodingassessment.data.model.Book
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class BookViewModel @Inject constructor(
    private val booksRepository: BooksRepository
) : ViewModel() {

    private val _data = MutableStateFlow(UiState())
    val data: StateFlow<UiState> = _data.asStateFlow()

    var bookType by mutableStateOf("Format")
        private set

    init {
        getAllBooks(bookType)
    }

    fun getAllBooks(type: String) {
        viewModelScope.launch {
            val response = booksRepository.getAllBooks()

            if (response.isNotEmpty()) {
                when (type) {
                    EBOOK -> {
                        _data.update {
                            it.copy(books = getAllEbooks(response = response, type = type))
                        }
                    }

                    AUDIOBOOK -> {
                        _data.update {
                            it.copy(books = getAllAudiobooks(response = response, type = type))
                        }
                    }

                    else -> {
                        _data.update { it.copy(books = response) }
                    }
                }
            }

        }
    }

    private fun getAllEbooks(response: Array<Book>, type: String? = null): Array<Book> {
        val ebooks = mutableListOf<Book>()
        response.forEach {
            if (it.type == type) {
                ebooks.add(it)
            }
        }
        return ebooks.toTypedArray()
    }

    private fun getAllAudiobooks(response: Array<Book>, type: String? = null): Array<Book> {
        val audiobooks = mutableListOf<Book>()
        response.forEach {
            if (it.type == type) {
                audiobooks.add(it)
            }
        }
        return audiobooks.toTypedArray()
    }

    fun updateBookTypeString(type: String?) {
        type?.let {
            bookType = it
        }
    }
}

data class UiState(
    val loading: Boolean = false,
    val error: String? = null,
    val books: Array<Book> = arrayOf()
)