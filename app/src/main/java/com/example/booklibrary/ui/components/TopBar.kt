package com.example.booklibrary.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun TopBar(title: String){
        Box(modifier = Modifier
            .background(Color.Blue)
            .padding(top = 64.dp, bottom = 16.dp)
            .fillMaxWidth(),
            contentAlignment = Alignment.BottomCenter
        ){
            Text(title,
                    color = Color.White,
                    fontSize = 24.sp
                )
            }

}