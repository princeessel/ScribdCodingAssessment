package com.example.scribdcodingassessment.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.IconButton
import androidx.compose.material.ModalBottomSheetLayout
import androidx.compose.material.ModalBottomSheetState
import androidx.compose.material.ModalBottomSheetValue
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.rememberModalBottomSheetState
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateMapOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.scribdcodingassessment.ui.viewmodel.BookViewModel
import kotlinx.coroutines.launch
import androidx.lifecycle.viewmodel.compose.viewModel

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun BottomSheet(viewModel: BookViewModel = viewModel()) {
    val scope = rememberCoroutineScope()
    val modalBottomSheetState: ModalBottomSheetState = rememberModalBottomSheetState(
        initialValue = ModalBottomSheetValue.Hidden,
        skipHalfExpanded = true
    )
    val types = listOf(BookType(name = "Ebooks"), BookType(name = "Audiobooks"))
    val checkedState = remember { mutableStateMapOf<String, Boolean>().withDefault { false } }
    val state by rememberSaveable { mutableStateOf(types) }
    val (value, setValue) = remember { mutableStateOf(viewModel.bookType) }


    val toggleBottomSheetScaffoldState = {
        scope.launch {
            if (modalBottomSheetState.isVisible) {
                modalBottomSheetState.hide()
            } else {
                modalBottomSheetState.show()
            }
        }
    }

    ModalBottomSheetLayout(
        sheetState = modalBottomSheetState,
        sheetContent = {
            Row {
                Row {
                    Text(
                        text = "Formats",
                        modifier = Modifier
                            .padding(start = 16.dp, top = 16.dp)
                            .weight(1f)
                    )
                    IconButton(onClick = { /*TODO*/ }) {
                        Icon(Icons.Default.Close, contentDescription = null)
                    }
                }
            }

            state.forEach { item ->
                Row {
                    Text(
                        text = item.name,
                        modifier = Modifier
                            .weight(1f)
                            .padding(start = 16.dp)
                    )
                    Checkbox(
                        checked = checkedState.getValue(item.name),
                        onCheckedChange = {
                            checkedState[item.name] = it
                            if (checkedState[item.name] == true) {
                                setValue(item.name)
                            } else {
                                setValue("Formats")
                            }
                        },
                    )
                }
            }
            ApplyFilterButton(
                bookType = value,
                isBottomSheetVisible = modalBottomSheetState.isVisible,
                sheetState = modalBottomSheetState
            )
        },
    ) {
        Box(
            contentAlignment = Alignment.TopCenter,
            modifier = Modifier
                .padding(bottom = 16.dp)
        ) {
            FormatSpinner(
                viewModel = viewModel,
                type = value,
                onClick = { toggleBottomSheetScaffoldState() },
                modifier = Modifier
                    .height(60.dp)
                    .padding(horizontal = 4.dp, vertical = 4.dp)
            )
        }
    }
}

data class BookType(val name: String)
