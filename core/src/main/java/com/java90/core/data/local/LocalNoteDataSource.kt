package com.java90.core.data.local

import com.java90.core.domain.models.Note

interface LocalNoteDataSource {
    suspend fun add(note: Note)
    suspend fun getAll() : List<Note>
}