package com.java90.noticashilt.framework.di

import com.java90.core.data.local.LocalNoteDataSource
import com.java90.noticashilt.framework.LocalNoteDataSourceImp
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class DataSourceModule {

    @Binds
    abstract fun bindLocalNoteDataSource(
            localNoteDataSourceImp: LocalNoteDataSourceImp
    ) : LocalNoteDataSource
}