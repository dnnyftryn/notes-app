package com.example.notesapps.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.notesapps.model.Note
import com.example.notesapps.model.data.repository.NoteRepository
import kotlinx.coroutines.launch

//class NoteViewModel(app: Application) : AndroidViewModel(app) {
//
//    private val repository = NoteRepository(app)
//
//    fun insert(note: Note) = viewModelScope.launch {
//        repository.insert(note)
//    }
//
//    fun update(note: Note) = viewModelScope.launch {
//        repository.update(note)
//    }
//
//    fun delete(note: Note) = viewModelScope.launch {
//        repository.delete(note)
//    }
//}

class NoteViewModel(private val repository: NoteRepository) : ViewModel() {

    val allNotes: LiveData<List<Note>> = repository.allNotes

    fun insert(note: Note) {
        repository.insert(note)
    }

    fun delete(note: Note) {
        repository.delete(note)
    }
}

