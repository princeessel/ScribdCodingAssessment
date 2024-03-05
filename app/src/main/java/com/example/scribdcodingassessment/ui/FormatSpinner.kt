package com.example.scribdcodingassessment.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ExposedDropdownMenuDefaults
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import com.example.scribdcodingassessment.ui.viewmodel.BookViewModel

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun FormatSpinner(
    viewModel: BookViewModel,
    modifier: Modifier = Modifier,
    type: String,
    onClick: () -> Unit,
) {
    Box(
        modifier = modifier
    ) {
        var bookType = type
        TextField(
            value = bookType,
            onValueChange = { onQueryChange ->
                bookType = onQueryChange
                viewModel.updateBookTypeString(bookType)
            },
            trailingIcon = {
                ExposedDropdownMenuDefaults.TrailingIcon(
                    expanded = false
                )
            },
            shape = CircleShape
        )

        Box(
            modifier = Modifier
                .matchParentSize()
                .alpha(0f)
                .clickable(onClick = onClick)
        )
    }
}

