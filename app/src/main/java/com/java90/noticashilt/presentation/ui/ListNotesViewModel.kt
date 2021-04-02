package com.java90.noticashilt.presentation.ui

import androidx.lifecycle.ViewModel
import com.java90.core.usecases.GetAllNotesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ListNotesViewModel @Inject constructor(
    private val getAllNotesUseCase: GetAllNotesUseCase
) : ViewModel() {


}