package com.example.booklibrary

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.PreviewScreenSizes
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.example.booklibrary.ui.screens.AddBook
import com.example.booklibrary.ui.screens.BookDetails
import com.example.booklibrary.ui.screens.BookListScreen
import com.example.booklibrary.ui.theme.BooklibraryTheme
import com.example.booklibrary.viewmodel.BookViewModel
import kotlinx.serialization.Serializable

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            BooklibraryTheme {
                BooklibraryApp()
            }
        }
    }
}

@Serializable object HomeDestination
@Serializable object AddDestination
@Serializable data class DetailsDestination(val bookId: String) // UUID передадим как строку

@PreviewScreenSizes
@Composable
fun BooklibraryApp() {
    val navController = rememberNavController()
    val viewModel: BookViewModel = viewModel()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White),
        verticalArrangement = Arrangement.SpaceBetween,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        NavHost(
            navController = navController,
            startDestination = HomeDestination
        ) {

            composable<HomeDestination> {
                BookListScreen(
                    title = "Books",
                    books = viewModel.books,
                    onAddBook = {
                        navController.navigate(AddDestination)
                    },
                    onBookClick = { book ->
                        navController.navigate(DetailsDestination(bookId = book.uuid.toString()))
                    }
                )
            }

            composable<AddDestination> {
                AddBook(
                    title = if (viewModel.editingBook == null) "Add" else "Edit",
                    book = viewModel.editingBook,
                    onAddBook = {
                        viewModel.addBook(it)
                        navController.popBackStack()
                    },
                    onNavigate = {
                        navController.popBackStack()
                                 },
                    onEditBook = {
                        viewModel.updateBook(it)
                        navController.popBackStack()
                    }
                )
            }

            composable<DetailsDestination> { backStackEntry ->
                val routeArgs = backStackEntry.toRoute<DetailsDestination>()

                val selectedBook = viewModel.books.find { it.uuid.toString() == routeArgs.bookId }

                if (selectedBook != null) {
                    BookDetails(
                        title = "Book Details",
                        book = selectedBook,
                        onEditClick = { book ->
                            viewModel.startEditing(book)
                            navController.navigate(AddDestination)
                        },
                        onNavigate = { navController.popBackStack() },
                        onDelete = { book ->
                            viewModel.deleteBook(book)
                        }
                    )
                } else {
                    LaunchedEffect(Unit) {
                        navController.popBackStack()
                    }
                }
            }
        }
    }
}