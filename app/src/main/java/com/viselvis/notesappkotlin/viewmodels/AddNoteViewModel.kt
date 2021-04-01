package com.viselvis.notesappkotlin.viewmodels

import androidx.lifecycle.*
import com.viselvis.notesappkotlin.database.Note
import com.viselvis.notesappkotlin.database.NoteRepository
import com.viselvis.notesappkotlin.fragments.AddNoteFragment
import kotlinx.coroutines.launch
import java.lang.IllegalArgumentException

class AddNoteViewModel(private val repository: NoteRepository): ViewModel() {

    private var _isDataInserted = MutableLiveData<Long>()
    val isDataInserted: LiveData<Long>
        get() = _isDataInserted

    fun resetInsertChecker() {
        _isDataInserted.value = 0
    }

    // hold the Note object to add
    fun saveNote(note: Note) = viewModelScope.launch {
         _isDataInserted.value = repository.insert(note)
    }
}

class AddNoteViewModelFactory(private val repository: NoteRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(AddNoteViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return AddNoteViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}