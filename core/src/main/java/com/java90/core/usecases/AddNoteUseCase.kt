package com.java90.core.usecases

import com.java90.core.data.local.Repository
import com.java90.core.domain.models.Note

class AddNoteUseCase(private val repository: Repository) {
    suspend operator fun invoke(note: Note) = repository.addNote(note)
}