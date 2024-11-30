package com.example.notesapps.model.data.repository

import android.app.Application
import com.example.notesapps.model.Note
import com.example.notesapps.model.data.dao.NoteDao
import com.example.notesapps.model.data.database.NoteDatabase

//class NoteRepository(application: Application) {
//
//    private var noteDao : NoteDao
//    private val database = NoteDatabase.getInstance(application)
//
//    init {
//        noteDao = database.noteDao()
//    }
//
//    suspend fun insert(note: Note) {
//        noteDao.insert(note)
//    }
//
//    suspend fun update(note: Note) {
//        noteDao.update(note)
//    }
//
//    suspend fun delete(note: Note) {
//        noteDao.delete(note)
//    }
//}

import androidx.lifecycle.LiveData

class NoteRepository(private val noteDao: NoteDao) {

    // LiveData to observe the list of all notes
    val allNotes: LiveData<List<Note>> = noteDao.getAllNotes()

    // Function to insert a note
    fun insert(note: Note) {
        noteDao.insert(note)
    }

    fun update(note: Note) {
        noteDao.update(note)
    }

    fun delete(note: Note) {
        noteDao.delete(note)
    }


}
