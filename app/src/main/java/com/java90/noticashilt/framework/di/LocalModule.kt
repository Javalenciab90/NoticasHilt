package com.java90.noticashilt.framework.di

import android.content.Context
import android.provider.ContactsContract
import androidx.room.Room
import com.java90.noticashilt.framework.db.DataBaseService
import com.java90.noticashilt.framework.db.NoteDao
import com.java90.noticashilt.framework.db.NoteEntity
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object LocalModule {

    @Singleton
    @Provides
    fun provideDataBaseService(@ApplicationContext context: Context) : DataBaseService {
        return Room.databaseBuilder(
                context,
                DataBaseService::class.java,
                DataBaseService.DATABASE_NAME)
        .fallbackToDestructiveMigration()
        .build()
    }

    @Singleton
    @Provides
    fun provideNoteDao(dataBaseService: DataBaseService) : NoteDao {
        return dataBaseService.noteDao()
    }
}