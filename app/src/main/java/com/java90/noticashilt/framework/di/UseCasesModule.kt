package com.java90.noticashilt.framework.di

import com.java90.core.data.local.Repository
import com.java90.core.usecases.AddNoteUseCase
import com.java90.core.usecases.GetAllNotesUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object UseCasesModule {

    @Singleton
    @Provides
    fun provideAddNoteUseCase(repository: Repository) : GetAllNotesUseCase {
        return GetAllNotesUseCase(repository)
    }
}