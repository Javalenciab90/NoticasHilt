package com.java90.core.usecases

import com.java90.core.data.local.Repository
import javax.inject.Inject

class GetAllNotesUseCase @Inject constructor(private val repository: Repository) {
    suspend operator fun invoke() = repository.getAllNotes()
}