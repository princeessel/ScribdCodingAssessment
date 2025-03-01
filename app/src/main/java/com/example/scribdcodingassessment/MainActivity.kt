package com.example.scribdcodingassessment

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.scribdcodingassessment.ui.BookCard
import com.example.scribdcodingassessment.ui.BottomSheet
import com.example.scribdcodingassessment.ui.theme.ScribdCodingAssessmentTheme
import com.example.scribdcodingassessment.ui.viewmodel.BookViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ScribdCodingAssessmentTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    BookList()
                    BottomSheet()
                }
            }
        }
    }
}

@Composable
fun BookList(viewModel: BookViewModel = viewModel()) {
    val state by viewModel.data.collectAsState()
    LazyColumn(
        modifier = Modifier.padding(top = 64.dp)
    ) {
        items(state.books) {
            BookCard(it)
        }
    }
}

@Composable
fun BookDetails(viewModel: BookViewModel = viewModel()) {
    val state by viewModel.data.collectAsState()
    LazyColumn(
        modifier = Modifier.padding(top = 64.dp)
    ) {
        items(state.books) {
            BookCard(it)
            BookDetails(viewModel)
        }
    }
}


@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    ScribdCodingAssessmentTheme {
        BookList()
    }
}
