package com.example.scribdcodingassessment.ui

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ModalBottomSheetState
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.scribdcodingassessment.ui.viewmodel.BookViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun ApplyFilterButton(
    viewModel: BookViewModel = viewModel(),
    bookType: String,
    isBottomSheetVisible: Boolean,
    sheetState: ModalBottomSheetState
) {
    val scope = rememberCoroutineScope()

    Button(
        onClick = {
            if (bookType.isNotEmpty()) {
                viewModel.getAllBooks(bookType.lowercase())
            }
            scope.launch { sheetState.hide() }
                .invokeOnCompletion { !isBottomSheetVisible }
        },
        modifier = Modifier
            .padding(16.dp)
    ) {
        Text(
            text = "Apply",
            modifier = Modifier
                .fillMaxWidth(),
            textAlign = TextAlign.Center
        )
    }
}