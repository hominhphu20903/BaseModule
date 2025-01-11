package com.phuhm.basemodule.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.phuhm.basemodule.data.model.Note
import com.phuhm.basemodule.data.model.RoomDatabaseResult
import com.phuhm.basemodule.data.repository.NoteRepositoryImpl
import com.phuhm.basemodule.event.RoomEvents
import com.phuhm.basemodule.shared.MyApplication
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class RoomViewModel(private val noteRepositoryImpl: NoteRepositoryImpl) : ViewModel() {
    private val _notes = MutableStateFlow<List<Note>>(emptyList())
    val notes = _notes.asStateFlow()

    private val _insertResult = MutableSharedFlow<RoomDatabaseResult>()
    val insertResult = _insertResult.asSharedFlow()

    private val _deleteResult = MutableSharedFlow<RoomDatabaseResult>()
    val deleteResult = _deleteResult.asSharedFlow()

    private var recentlyDeleteNote: Note? = null
    private var job: Job? = null

    init {
        getNotes()
    }

    fun onEvent(event: RoomEvents) {
        when (event) {
            is RoomEvents.OnInsertNote -> {
                viewModelScope.launch {
                    val isSuccess = noteRepositoryImpl.insertNote(event.note) != -1L
                    _insertResult.emit(if (isSuccess) RoomDatabaseResult.Success else RoomDatabaseResult.Failure)
                }
            }

            is RoomEvents.OnDeleteNote -> {
                viewModelScope.launch {
                    val isSuccess = noteRepositoryImpl.deleteNote(event.note) != 0
                    if(isSuccess) {
                        recentlyDeleteNote = event.note
                    }
                    _deleteResult.emit(if (isSuccess) RoomDatabaseResult.Success else RoomDatabaseResult.Failure)
                }
            }

            is RoomEvents.OnRestoreNote -> {
                viewModelScope.launch {
                    val isSuccess = noteRepositoryImpl.insertNote(recentlyDeleteNote ?: return@launch) != -1L
                    if(isSuccess) {
                        recentlyDeleteNote = null
                    }
                    _insertResult.emit(if (isSuccess) RoomDatabaseResult.Success else RoomDatabaseResult.Failure)
                }
            }
        }
    }

    private fun getNotes() {
        job?.cancel()

        job = viewModelScope.launch {
            noteRepositoryImpl.getNotes().collect {
                _notes.emit(it)
            }
        }
    }

    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val noteRepositoryImpl = (this[APPLICATION_KEY] as MyApplication).noteRepositoryImpl
                RoomViewModel(noteRepositoryImpl = noteRepositoryImpl)
            }
        }
    }
}