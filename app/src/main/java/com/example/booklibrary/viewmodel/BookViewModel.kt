package com.example.booklibrary.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.booklibrary.models.Book

class BookViewModel : ViewModel() {

    val books = mutableStateListOf<Book>()

    var editingBook by mutableStateOf<Book?>(null)

    fun startEditing(book: Book) {
        editingBook = book
    }

    fun addBook(book: Book) {
        books.add(book)
    }

    fun updateBook(updated: Book) {
        val index = books.indexOfFirst { it.uuid == updated.uuid }
        if (index != -1) {
            books.removeAt(index)
            books.add(index, updated)
        }
    }

    fun deleteBook(book: Book) {
        books.removeIf { it.uuid == book.uuid }
    }
}