package com.java90.core.usecases

import com.java90.core.data.local.Repository
import javax.inject.Inject

class GetNoteUseCase @Inject constructor(private val repository: Repository) {
    suspend operator fun invoke(id: Long) = repository.getNote(id)
}