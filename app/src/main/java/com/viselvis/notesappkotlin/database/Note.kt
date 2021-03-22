package com.viselvis.notesappkotlin.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity (tableName = "note_table")
data class Note (
    @PrimaryKey(autoGenerate = true) var noteId: Long,
    var noteTitle: String,
    var noteContent: String,
    var noteFirstCreated: Long,
    var noteLastModified: Long
)