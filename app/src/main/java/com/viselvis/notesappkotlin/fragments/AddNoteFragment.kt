package com.viselvis.notesappkotlin.fragments

import android.app.Application
import android.os.Bundle
import android.text.TextUtils
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import com.viselvis.notesappkotlin.R
import com.viselvis.notesappkotlin.application.NoteApplication
import com.viselvis.notesappkotlin.database.Note
import com.viselvis.notesappkotlin.database.NoteRepository
import com.viselvis.notesappkotlin.databinding.FragmentAddNoteBinding
import com.viselvis.notesappkotlin.viewmodels.AddNoteViewModel
import com.viselvis.notesappkotlin.viewmodels.AddNoteViewModelFactory
import java.util.*

class AddNoteFragment : Fragment() {

    private val addNoteViewModel: AddNoteViewModel by viewModels {
        AddNoteViewModelFactory((activity?.application as NoteApplication).repository)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        //return inflater.inflate(R.layout.fragment_add_note, container, false)
        val binding: FragmentAddNoteBinding = DataBindingUtil.inflate(
                inflater, R.layout.fragment_add_note, container, false)

        binding.btnAddNote.setOnClickListener {
            if ( !TextUtils.isEmpty(binding.edtTitle.text) && !TextUtils.isEmpty(binding.edtContent.text) ) {
                val title = binding.edtTitle.text.toString()
                val content = binding.edtContent.text.toString()

                insertNoteOnDb(title, content)
            } else {
                Toast.makeText(context, "One/both of the fields are still empty!", Toast.LENGTH_LONG).show()
            }
        }

        return binding.root
    }

    private fun insertNoteOnDb(title: String, content: String) {
        val dateCreated = Calendar.getInstance().timeInMillis
        val dateModified = Calendar.getInstance().timeInMillis
        val newNote = Note(title, content, dateCreated, dateModified)

        addNoteViewModel.saveNote(newNote)
    }

}