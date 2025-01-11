package com.phuhm.basemodule.data.repository

import com.phuhm.basemodule.data.model.Note
import kotlinx.coroutines.flow.Flow

interface NoteRepository {
    fun getNotes() : Flow<List<Note>>
    suspend fun getNoteById(id: Int) : Note?
    suspend fun insertNote(note: Note) : Long
    suspend fun deleteNote(note: Note) : Int
}