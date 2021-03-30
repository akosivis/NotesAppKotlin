package com.viselvis.notesappkotlin.fragments

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.viselvis.notesappkotlin.R
import com.viselvis.notesappkotlin.adapters.NoteListAdapter
import com.viselvis.notesappkotlin.application.NoteApplication
import com.viselvis.notesappkotlin.databinding.FragmentAllNotesBinding
import com.viselvis.notesappkotlin.viewmodels.AllNotesViewModel
import com.viselvis.notesappkotlin.viewmodels.AllNotesViewModelFactory

class AllNotesFragment : Fragment() {

    private val allNotesViewModel: AllNotesViewModel by viewModels {
        AllNotesViewModelFactory((activity?.application as NoteApplication).repository)
    }

    private val adapter = NoteListAdapter()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val binding: FragmentAllNotesBinding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_all_notes, container, false)

        binding.btnAddNote.setOnClickListener {
            binding.root.findNavController().navigate(R.id.action_allNotesFragment_to_addNoteFragment)
        }

        binding.rvNotes.adapter = adapter
        binding.rvNotes.layoutManager = LinearLayoutManager(activity)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        allNotesViewModel.allNotes.observe(viewLifecycleOwner, Observer {
            notes -> notes.let { adapter.submitList(it) }
        })
    }

    companion object {

    }
}