package com.example.booklibrary.ui.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.booklibrary.models.Book
import com.example.booklibrary.ui.components.TopBar
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.text.input.KeyboardType

@Composable
fun AddBook(
    title: String,
    book: Book? = null,
    onNavigate: () -> Unit,
    onAddBook: (Book) -> Unit,
    onEditBook: (Book) -> Unit
){

    var bookTitle by rememberSaveable {
        mutableStateOf(book?.title ?: "")
    }
    var bookAuthor by rememberSaveable {
        mutableStateOf(book?.author ?: "")
    }
    var bookYear by rememberSaveable {
        mutableStateOf(book?.year?: "")
    }

    LaunchedEffect(book) {
        bookTitle = book?.title ?: ""
        bookAuthor = book?.author ?: ""
        bookYear = book?.year ?: ""
    }

    Column(
        modifier = Modifier
            .fillMaxWidth(),
    ) {

        TopBar(title)
        Box(
            modifier = Modifier.padding(top = 8.dp, start = 8.dp)
        ) {
            Button(onClick = { onNavigate() } ) {
                Text("<--=",
                    color = Color.White,
                    fontSize = 24.sp)
            }
        }
        Column(modifier = Modifier.padding(16.dp)) {
            TextField(
                value = bookTitle,
                onValueChange = { bookTitle = it },
                label = { Text("Title") },
                modifier = Modifier.fillMaxWidth()
            )
            TextField(
                value = bookAuthor,
                onValueChange = { bookAuthor = it },
                label = { Text("Author") },
                modifier = Modifier.fillMaxWidth()
            )
            TextField(
                value = bookYear,
                onValueChange = { input ->
                    if (input.all { it.isDigit() } && input.length <= 4) {
                        bookYear = input
                    }
                },
                label = { Text("Year") },
                modifier = Modifier.fillMaxWidth(),
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Number
                )
            )
            Spacer(modifier = Modifier.height(8.dp))

            Button(
                onClick = {
                    if (book == null){
                        if (bookTitle.isNotBlank() || bookAuthor.isNotBlank() || bookYear.isNotBlank()) {
                            val newBook = Book(
                                title = bookTitle,
                                author = bookAuthor,
                                year = bookYear)
                            onAddBook(newBook)
                        }
                    } else {
                        val updatedBook = book.copy(
                            title = bookTitle,
                            author = bookAuthor,
                            year = bookYear
                        )
                        onEditBook(updatedBook)
                        onNavigate()
                    }
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                if (book != null){
                    Text("Update")
                } else{
                    Text("Save")
                }
            }
        }
    }

}