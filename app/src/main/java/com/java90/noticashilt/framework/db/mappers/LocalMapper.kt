package com.java90.noticashilt.framework.db.mappers

import com.java90.core.domain.models.Note
import com.java90.core.domain.util.EntityMapper
import com.java90.noticashilt.framework.db.models.NoteEntity
import javax.inject.Inject

class LocalMapper @Inject constructor() : EntityMapper<NoteEntity, Note> {

    override fun mapFromEntity(entity: NoteEntity): Note {
        return Note(
                title = entity.title,
                content = entity.content,
                creationTime = entity.creationTime,
                updateTime = entity.updateTime,
                id = entity.id
        )
    }

    override fun mapToEntity(domainModel: Note): NoteEntity {
        return NoteEntity(
                title = domainModel.title,
                content = domainModel.content,
                creationTime = domainModel.creationTime,
                updateTime = domainModel.updateTime,
                id = domainModel.id
        )
    }

    fun mapFromEntityList(entities: List<NoteEntity>) : List<Note> {
        return entities.map { mapFromEntity(it) }
    }
}