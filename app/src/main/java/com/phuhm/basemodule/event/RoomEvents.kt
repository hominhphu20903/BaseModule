package com.phuhm.basemodule.event

import com.phuhm.basemodule.data.model.Note

sealed class RoomEvents {
    data class OnInsertNote(val note: Note) : RoomEvents()
    data class OnDeleteNote(val note: Note) : RoomEvents()
    data object OnRestoreNote : RoomEvents()
}