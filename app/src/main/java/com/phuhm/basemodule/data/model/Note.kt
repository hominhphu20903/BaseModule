package com.phuhm.basemodule.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.phuhm.basemodule.utils.DateUtils

@Entity
data class Note(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val title: String,
    val notes: String,
    val timestamp: Long = DateUtils.getCurrentDate().time
)
