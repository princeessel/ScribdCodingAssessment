package com.example.scribdcodingassessment.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.example.scribdcodingassessment.Constants.AUDIOBOOK
import com.example.scribdcodingassessment.Constants.EBOOK
import com.example.scribdcodingassessment.R
import com.example.scribdcodingassessment.data.model.Book


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BookCard(book: Book) {
    Card(
        modifier = Modifier
            .fillMaxWidth(),
        elevation = CardDefaults.cardElevation(defaultElevation = 6.dp),
        onClick = {}
    ) {
        Row {
            val bookTypeImage = when (book.type) {
                EBOOK -> R.drawable.book_placeholder2
                AUDIOBOOK -> R.drawable.svgviewer_output__1_
                else -> -1
            }

            Image(
                painter = painterResource(id = bookTypeImage),
                contentDescription = null,
                modifier = Modifier
                    .size(75.dp)
            )
            Column {
                Row {
                    Text(
                        text = book.title,
                        modifier = Modifier
                            .padding(top = 4.dp, start = 16.dp)
                            .weight(1f),
                        textAlign = TextAlign.Start,
                        overflow = TextOverflow.Ellipsis,
                        maxLines = 1
                    )
                    Icon(
                        painter = painterResource(id = R.drawable.bookmark_border_unfilled),
                        contentDescription = null,
                        modifier = Modifier.padding(top = 5.dp, end = 16.dp)
                    )

                }
                Text(
                    text = book.author,
                    modifier = Modifier
                        .padding(top = 4.dp, start = 16.dp),
                    textAlign = TextAlign.Start
                )
            }
        }

    }
}
