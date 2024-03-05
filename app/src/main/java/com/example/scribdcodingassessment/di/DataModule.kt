package com.example.scribdcodingassessment.di

import android.app.Application
import com.example.scribdcodingassessment.data.BooksRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object DataModule {

    @Provides
    @Singleton
    fun providesRepository(app: Application): BooksRepository =  BooksRepository(app)
}