package com.java90.noticashilt.presentation.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.java90.core.domain.models.Note
import com.java90.core.domain.util.DataState
import com.java90.noticashilt.databinding.FragmentListNotesBinding
import com.java90.noticashilt.framework.utils.hideProgressBar
import com.java90.noticashilt.framework.utils.showProgressBar
import com.java90.noticashilt.presentation.adapters.NotesAdapter
import dagger.hilt.EntryPoint
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ListNotesFragment : Fragment() {

    private val viewModel: ListNotesViewModel by viewModels()

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

        binding.addNote.setOnClickListener {
            findNavController().navigate(ListNotesFragmentDirections.actionListNotesFragmentToNoteFragment())
        }
    }

    override fun onResume() {
        viewModel.getNotes()
        super.onResume()
    }

    private fun setUpRecyclerView() {
        binding.notesListView.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = NotesAdapter(onItemClickListener = {
                navigateToNoteFragment(it)
            })
        }
    }

    private fun navigateToNoteFragment(id: Long = 0L) {
        findNavController().navigate(ListNotesFragmentDirections.actionListNotesFragmentToNoteFragment(id))
    }

    private fun setUpObservables() {
        viewModel.notes.observe(viewLifecycleOwner, { dataState ->
            when (dataState) {
                is DataState.Loading -> {
                    binding.loadingNotes.showProgressBar()
                }
                is DataState.Success -> {
                    binding.loadingNotes.hideProgressBar()
                    binding.notesListView.adapter?.let {
                        if (it is NotesAdapter) {
                            it.submitList(dataState.data)
                        }
                    }
                }
                is DataState.Error -> {
                    binding.loadingNotes.showProgressBar()
                }
            }
        })
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}