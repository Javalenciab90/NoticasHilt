package com.java90.noticashilt.framework.db

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface NoteDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addNoteEntity(noteEntity: NoteEntity)

    @Query("SELECT * FROM notes WHERE id = :id")
    suspend fun getNoteEntity(id: Long) : NoteEntity?

    @Query("SELECT * FROM notes")
    suspend fun getAllNoteEntities() : Flow<List<NoteEntity>>

    @Delete
    suspend fun deleteNoteEntity(noteEntity: NoteEntity)
}