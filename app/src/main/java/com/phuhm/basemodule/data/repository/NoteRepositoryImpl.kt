package com.phuhm.basemodule.data.repository

import com.phuhm.basemodule.data.database.NoteDao
import com.phuhm.basemodule.data.model.Note
import kotlinx.coroutines.flow.Flow

class NoteRepositoryImpl(private val dao: NoteDao) : NoteRepository {
    override fun getNotes(): Flow<List<Note>> {
        return dao.getNotes()
    }

    override suspend fun getNoteById(id: Int): Note? {
        return dao.getNoteById(id)
    }

    override suspend fun insertNote(note: Note): Long {
        return dao.insertNote(note)
    }

    override suspend fun deleteNote(note: Note): Int {
        return dao.deleteNote(note)
    }
}