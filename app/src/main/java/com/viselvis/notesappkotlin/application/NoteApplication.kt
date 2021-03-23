package com.viselvis.notesappkotlin.application

import android.app.Application
import com.viselvis.notesappkotlin.database.NoteDatabase
import com.viselvis.notesappkotlin.database.NoteRepository

class NoteApplication: Application() {
    val database by lazy { NoteDatabase.getInstance(this) }
    val repository by lazy { NoteRepository(database.noteDao()) }
}