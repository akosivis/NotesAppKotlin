package com.viselvis.notesappkotlin.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity (tableName = "note_table")
data class Note (
    @PrimaryKey(autoGenerate = true) val noteId: Long,
    var noteTitle: String,
    var noteContent: String,
    var noteFirstCreated: Long,
    var noteLastModified: Long
) {
    constructor(
            noteTitle: String,
            noteContent: String,
            noteFirstCreated: Long,
            noteLastModified: Long
    ) : this(0, noteTitle, noteContent, noteFirstCreated, noteLastModified)
}