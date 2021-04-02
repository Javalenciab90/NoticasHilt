package com.java90.core.usecases

import com.java90.core.data.local.Repository

class GetAllNotesUseCase(private val repository: Repository) {
    suspend operator fun invoke() = repository.getAllNotes()
}