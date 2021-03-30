package com.java90.noticashilt.presentation.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.java90.core.data.Note
import com.java90.noticashilt.R
import com.java90.noticashilt.databinding.FragmentListNotesBinding
import com.java90.noticashilt.presentation.adapters.NotesAdapter

class ListNotesFragment : Fragment() {

    private val notes = listOf(
            Note("note1","first note",1111,1000,0,10),
            Note("note2","Second note",1111,1000,1,10),
            Note("note3","Third note",1111,1000,2,10)
    )

    private var _binding: FragmentListNotesBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding = FragmentListNotesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setUpRecyclerView()
        setUpObservables()
    }

    private fun setUpRecyclerView() {
        binding.notesListView.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = NotesAdapter()
        }
    }

    private fun setUpObservables() {
        binding.notesListView.adapter?.let {
            if (it is NotesAdapter) {
                it.submitList(notes)
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}