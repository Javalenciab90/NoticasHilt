package com.java90.noticashilt.presentation.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.java90.core.domain.models.Note
import com.java90.core.usecases.AddNoteUseCase
import com.java90.core.usecases.GetNoteUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NoteViewModel @Inject constructor(
    private val addNoteUseCase: AddNoteUseCase,
    private val getNoteUseCase: GetNoteUseCase
) : ViewModel() {

    private val _noteSaved = MutableLiveData<Boolean>()
    val noteSaved : LiveData<Boolean> = _noteSaved

    private val _currentNote = MutableLiveData<Note>()
    val currentNote : LiveData<Note> = _currentNote

    fun saveNote(note: Note) {
        viewModelScope.launch {
            addNoteUseCase.invoke(note)
            _noteSaved.postValue(true)
        }
    }

    fun getNote(id: Long) {
        viewModelScope.launch {
            val note = getNoteUseCase.invoke(id)
            _currentNote.postValue(note)
        }
    }

}