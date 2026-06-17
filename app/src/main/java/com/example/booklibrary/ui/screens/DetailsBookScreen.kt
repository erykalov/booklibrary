package com.example.booklibrary.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import com.example.booklibrary.models.Book
import com.example.booklibrary.ui.components.TopBar
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun BookDetails(
    title: String,
    book: Book,
    onEditClick: (Book) -> Unit,
    onDelete: (Book) -> Unit,
    onNavigate: () -> Unit
){
    var sliderValue by remember { mutableFloatStateOf(book.rating) }
    Column {
        TopBar(title)
        Box(
            modifier = Modifier.padding(top = 8.dp, start = 8.dp)
        ) {
            Button(onClick = { onNavigate() }) {
                Text("<--=",
                    color = Color.White,
                    fontSize = 24.sp)
            }
        }
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
        ) {
                Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {
                    Text(book.title)
                }
                Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {
                    Text(book.author)
                }
        }

        Card(modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
        ) {
            Column(modifier = Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
                Text("Year")
                Text(book.year)
            }
        }

        Card(modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
        ) {
            Box(modifier = Modifier.fillMaxWidth(),
                contentAlignment = Alignment.Center
            ){
                Column(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = "Rating",
                        color = Color.Gray,
                        fontSize = 20.sp
                    )
                    Text(
                        text = "(${sliderValue.toInt()})",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.White
                    )
                }

            }

            Slider(
                value = sliderValue,
                onValueChange = { newValue ->
                    sliderValue = newValue
                    book.rating = newValue
                                },
                valueRange = 1f..5f,
                steps = 3,
                colors = SliderDefaults.colors(
                    thumbColor = Color.LightGray,
                    activeTrackColor = Color(0xFF789BFF),
                    inactiveTrackColor = Color(0xFF4F6EA4)
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp)
            )
        }

        Box(modifier = Modifier.padding(8.dp)) {
            Button(
                onClick = {
                    onEditClick(book)

                },
                modifier = Modifier.fillMaxWidth()
            ){
                Text("Edit")
            }
        }

        Box(modifier = Modifier.padding(8.dp)) {
            Button(
                onClick = {
                    onDelete(book)
                          },
                modifier = Modifier.fillMaxWidth()
            ){
                Text("Delete")
            }
        }
    }
}