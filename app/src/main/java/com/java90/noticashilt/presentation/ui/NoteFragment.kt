package com.java90.noticashilt.presentation.ui

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.java90.core.domain.models.Note
import com.java90.noticashilt.databinding.FragmentNoteBinding
import dagger.hilt.EntryPoint
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class NoteFragment : Fragment() {

    private val viewModel: NoteViewModel by viewModels()

    private var _binding: FragmentNoteBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding = FragmentNoteBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setUpObservables()

        binding.checkButton.setOnClickListener {
            saveNote()
        }
    }

    private fun saveNote() {
        with(binding) {
            if (titleView.text.toString() != "" || contentView.text.toString() != "") {
                val title = titleView.text.toString()
                val content = contentView.text.toString()
                val time = System.currentTimeMillis()
                viewModel.saveNote(Note(title, content, time, 0))
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