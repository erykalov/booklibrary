package com.example.booklibrary.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.booklibrary.models.Book
import com.example.booklibrary.ui.components.TopBar

@Composable
fun BookListScreen(
    title: String,
    books: List<Book>,
    onAddBook: () -> Unit,
    onBookClick: (Book) -> Unit
) {
    TopBar(title)

        Column(
            modifier = Modifier.fillMaxWidth()
                .padding(top = 112.dp, start = 8.dp, end = 8.dp),
        ) {
            LazyColumn ( modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                items(books) { book ->
                    Card(
                        onClick = { onBookClick(book) },
                        modifier = Modifier.fillMaxWidth(),
                        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
                        colors = CardDefaults.cardColors(
                            containerColor = MaterialTheme.colorScheme.surfaceVariant
                        )
                    ) {
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(16.dp)
                        ) {
                            Text(
                                text = book.title,
                                style = MaterialTheme.typography.titleLarge,
                                fontWeight = FontWeight.Bold
                            )
                            Text(
                                text = "Автор: ${book.author}",
                                style = MaterialTheme.typography.bodyMedium,
                                color = MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.7f),
                                modifier = Modifier.padding(top = 4.dp)
                            )
                        }
                    }
                }

        }
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 48.dp, end = 32.dp),
            contentAlignment = Alignment.BottomEnd
        ) {
            Button(onClick = { onAddBook() }
            ) {
                Text(
                    "+",
                    color = Color.White,
                    fontSize = 32.sp
                )
            }
        }
    }
}

