package com.viselvis.notesappkotlin.database

import androidx.annotation.WorkerThread
import kotlinx.coroutines.flow.Flow

class NoteRepository(private val noteDatabaseDao: NoteDatabaseDao) {
    val allNotes: Flow<List<Note>> = noteDatabaseDao.getAllNotes()

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun insert(note: Note) {
        noteDatabaseDao.insertNote(note)
    }

}