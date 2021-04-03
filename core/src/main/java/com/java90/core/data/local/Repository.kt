package com.java90.core.data.local

import com.java90.core.domain.models.Note
import javax.inject.Inject

class Repository @Inject constructor(private val localNoteDataSource: LocalNoteDataSource) {
    suspend fun addNote(note: Note) = localNoteDataSource.add(note)
    suspend fun getAllNotes() = localNoteDataSource.getAll()
    suspend fun getNote(id: Long) = localNoteDataSource.getNote(id)
}