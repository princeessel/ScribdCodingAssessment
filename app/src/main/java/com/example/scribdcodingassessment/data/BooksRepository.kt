package com.example.scribdcodingassessment.data

import android.app.Application
import com.example.scribdcodingassessment.data.model.Book
import com.google.gson.Gson
import javax.inject.Inject

class BooksRepository @Inject constructor(
    private val app: Application
) {
    suspend fun getAllBooks(): Array<Book> {
        val inputStream = app.assets.open("books.json")
        val size: Int = inputStream.available()
        val buffer = ByteArray(size)
        inputStream.read(buffer)
        inputStream.close()
        val json = String(buffer, Charsets.UTF_8)
        val gson = Gson()
        return gson.fromJson(json, Array<Book>::class.java)
    }
}