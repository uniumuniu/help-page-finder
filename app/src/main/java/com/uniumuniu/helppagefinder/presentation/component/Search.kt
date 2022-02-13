package com.uniumuniu.helppagefinder.presentation.component

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun Search(query: String, onSearch: (String) -> Unit) {
    TextField(
        value = query,
        onValueChange = onSearch,
        modifier = Modifier.fillMaxWidth(),
        placeholder = {
            Text(text = "Search")
        }
    )
}