package com.phuhm.basemodule.data.model

sealed class RoomDatabaseResult {
    data object Success : RoomDatabaseResult()
    data object Failure : RoomDatabaseResult()
}