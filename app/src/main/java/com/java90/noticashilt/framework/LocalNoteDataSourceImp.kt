package com.java90.noticashilt.framework

import com.java90.core.data.local.LocalNoteDataSource
import com.java90.core.domain.models.Note
import com.java90.noticashilt.framework.db.NoteDao
import com.java90.noticashilt.framework.db.mappers.LocalMapper
import javax.inject.Inject

class LocalNoteDataSourceImp @Inject constructor(
    private val noteDao: NoteDao,
    private val localMapper: LocalMapper
): LocalNoteDataSource {

    override suspend fun add(note: Note) {
        noteDao.addNoteEntity(localMapper.mapToEntity(note))
    }

    override suspend fun getAll(): List<Note> {
        return localMapper.mapFromEntityList(noteDao.getAllNoteEntities())
    }
}