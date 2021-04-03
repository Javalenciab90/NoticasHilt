package com.java90.noticashilt.presentation.ui

import androidx.lifecycle.*
import com.java90.core.domain.models.Note
import com.java90.core.domain.util.DataState
import com.java90.core.usecases.GetAllNotesUseCase
import com.java90.core.usecases.GetWordsCount
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.lang.Exception
import javax.inject.Inject

@HiltViewModel
class ListNotesViewModel @Inject constructor(
    private val getAllNotesUseCase: GetAllNotesUseCase,
    private val getWordsCount: GetWordsCount
) : ViewModel() {

    private val _notes = MutableLiveData<DataState<List<Note>>>()
    val notes: LiveData<DataState<List<Note>>> = _notes

    fun getNotes() = viewModelScope.launch {
        _notes.postValue(DataState.loading())
        try {
            val noteList = getAllNotesUseCase.invoke()
            noteList.forEach { it.wordCount = getWordsCount.invoke(it) }
            _notes.postValue(DataState.success(noteList))
        } catch (e:Exception) {
            _notes.postValue(DataState.error(e.message.toString()))
        }
    }
}