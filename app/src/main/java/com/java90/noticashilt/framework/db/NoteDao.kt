package com.java90.noticashilt.framework.db

import androidx.lifecycle.LiveData
import androidx.room.*
import com.java90.noticashilt.framework.db.models.NoteEntity

@Dao
interface NoteDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addNoteEntity(noteEntity: NoteEntity)

    @Query("SELECT * FROM notes WHERE id = :id")
    suspend fun getNoteEntity(id: Long) : NoteEntity?

    @Query("SELECT * FROM notes")
    suspend fun getAllNoteEntities() : List<NoteEntity>

    @Delete
    suspend fun deleteNoteEntity(noteEntity: NoteEntity)
}