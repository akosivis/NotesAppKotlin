package com.viselvis.notesappkotlin.viewmodels

import androidx.lifecycle.*
import com.viselvis.notesappkotlin.database.Note
import com.viselvis.notesappkotlin.database.NoteRepository
import com.viselvis.notesappkotlin.fragments.AddNoteFragment
import com.viselvis.notesappkotlin.fragments.AllNotesFragment
import java.lang.IllegalArgumentException

class AllNotesViewModel(private val repository: NoteRepository) : ViewModel() {
    val allNotes: LiveData<List<Note>> = repository.allNotes.asLiveData()
}

class AllNotesViewModelFactory(private val repository: NoteRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(AllNotesViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return AllNotesViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}