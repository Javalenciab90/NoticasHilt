package com.java90.noticashilt.framework.di

import com.java90.core.data.local.LocalNoteDataSource
import com.java90.core.data.local.Repository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Singleton
    @Provides
    fun provideRepository(localNoteDataSource: LocalNoteDataSource) : Repository {
        return Repository(localNoteDataSource)
    }
}