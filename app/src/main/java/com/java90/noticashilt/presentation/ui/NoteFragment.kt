package com.java90.noticashilt.presentation.ui

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.java90.core.domain.models.Note
import com.java90.noticashilt.databinding.FragmentNoteBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class NoteFragment : Fragment() {

    private val viewModel: NoteViewModel by viewModels()

    private var _binding: FragmentNoteBinding? = null
    private val binding get() = _binding!!

    private val args: NoteFragmentArgs by navArgs()

    private var currNote = Note("", "", 0L, 0L)

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding = FragmentNoteBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setUpObservables()
        viewModel.getNote(args.id)

        binding.checkButton.setOnClickListener {
            saveNote()
        }
    }

    private fun saveNote() {
        with(binding) {
            if (titleView.text.toString() != "" || contentView.text.toString() != "") {
                currNote.title = titleView.text.toString()
                currNote.content = contentView.text.toString()
                val time = System.currentTimeMillis()
                currNote.updateTime = time
                if (currNote.id == 0L) {
                    currNote.creationTime = time
                }
                viewModel.saveNote(currNote)
            }
        }
    }

    private fun setUpObservables() {
        with(viewModel) {
            noteSaved.observe(viewLifecycleOwner, {
                if (it) {
                    hideKeyboard()
                    Toast.makeText(context, "Done", Toast.LENGTH_SHORT).show()
                    findNavController().popBackStack()
                } else {
                    Toast.makeText(context, "Something went wrong, please try again", Toast.LENGTH_SHORT).show()
                }
            })
            currentNote.observe(viewLifecycleOwner, { note ->
                note?.let {
                    currNote = it
                    binding.titleView.setText(it.title, TextView.BufferType.EDITABLE)
                    binding.contentView.setText(it.content, TextView.BufferType.EDITABLE)
                }
            })
        }
    }

    private fun hideKeyboard() {
        val imm: InputMethodManager = context?.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(binding.titleView.windowToken, 0)
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}