package com.java90.noticashilt.framework.db.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "notes")
class NoteEntity(
    val title: String,
    val content: String,
    @ColumnInfo(name = "creation_time")
    val creationTime: Long,
    @ColumnInfo(name = "update_time")
    val updateTime: Long,
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0L
)