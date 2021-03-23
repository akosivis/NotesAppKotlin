package com.viselvis.notesappkotlin.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.viselvis.notesappkotlin.database.Note
import com.viselvis.notesappkotlin.database.NoteRepository
import com.viselvis.notesappkotlin.fragments.AddNoteFragment
import kotlinx.coroutines.launch
import java.lang.IllegalArgumentException

class AddNoteViewModel(private val repository: NoteRepository): ViewModel() {

    // hold the Note object to add


    private fun saveNote(note: Note) = viewModelScope.launch {
        repository.insert(note)
    }
}

class AddNoteViewModelFactory(private val repository: NoteRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(AddNoteFragment::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return AddNoteViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}