package com.java90.noticashilt.framework.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.java90.noticashilt.framework.db.models.NoteEntity

@Database(entities = [NoteEntity::class], version = 1)
abstract class DataBaseService : RoomDatabase() {

    companion object {
        const val DATABASE_NAME = "notes.db"
    }

    abstract fun noteDao() : NoteDao
}